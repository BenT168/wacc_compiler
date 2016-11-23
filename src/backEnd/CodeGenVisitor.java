package backEnd;

import antlr.WACCParser;
import antlr.WACCParserBaseVisitor;
import backEnd.helper.*;
import backEnd.stat.VisitDeclPairNode;
import frontEnd.SymbolTable;
import frontEnd.expr.BinaryExprNode;
import frontEnd.expr.UnaryExprNode;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.ArrayList;
import java.util.LinkedList;

public class CodeGenVisitor extends WACCParserBaseVisitor<LinkedList<String>> {

    private LinkedList<String> instructions = new LinkedList<>();
    private String[] reg = {"r0", "r1","r2","r3","r4","r5","r6","r7","r8",
                                  "r9","r10","r11","r12","r13","r14","r15","r16"};
    //Number of declarations to set initial store
    private int numberOfDeclare = 0;

    //SymbolTable made in TypeCheckVisitor
    private SymbolTable table;

    //For functions
    private boolean inFunction = false;
    private int posOfnewpair = 0;
    private int mainSeen = 0;
    private int PositionToPop = 0;
    private int countDeclarations = 0;

    // CONSTANTS
    private static final int MAX_BYTE_OFFSET = 1024;

    private ArrayList<ArrayList<String>> functionsCodeGen = new ArrayList<>();
    private ArrayList<Function> listOfFunctions;
    private ArrayList<String> stringsInProgram = new ArrayList<>();
    private ArrayList<Integer> beginOffsets;

    private Function currentFunction = null;
    private boolean seenFirstBegin = false;

    private Registers regs = new Registers();
    private Register resultReg = null;
    private Labeller labeller = new Labeller();
    private int beginCount = 0;
    private int variableCount = 0;

    private int messagesLoaded = 0;
    private String currentIdent = "";
    private int r11Count = 0;
    final String _false = "0";
    final String _true = "1";
    private boolean optimisationsTurnedOn;


    //................................PROGRAM.........................................
    @Override
    public LinkedList<String> visitProgram(@NotNull WACCParser.ProgramContext ctx) {
//        for (int i = 0; i < ctx.func().size(); i++) {
//            visitFunc(ctx.func().get(i));
//        }
//        initiateFunction("main", true);
        instructions.add(ARMInstructions.GLOBAL_MAIN_DIRECTIVE);
        visit(ctx.stat());

        return instructions;
    }

    //....................................FUNCTION......................................

    // Function identifiers and types should already be added to ftable by time we call this method.
    @Override
    public LinkedList<String> visitFunc(@NotNull WACCParser.FuncContext ctx) {
        inFunction = true;
        String functionName = ctx.ident().getText();
        initiateFunction(functionName, false);
        inFunction = false;
        return visitChildren(ctx);
    }

    /*
	 * Method: initiateFunction Usage: This helps calculate the stack offset at
	 * the beginning of the function and print the standard ARM machine code
	 * that goes along with the start of a new function.
	 */
    public void initiateFunction(String funcName, boolean isMain) {
        Function f = new Function(funcName);
        int indexOfFunc = listOfFunctions.indexOf(f);
        currentFunction = listOfFunctions.get(indexOfFunc);
        ArrayList<String> codeGen = new ArrayList<String>();
        if (isMain) {
            codeGen.add("\tmain:");
        } else {
            codeGen.add("\tf_" + currentFunction.getName() + ":");
        }
        codeGen.add(ARMInstructions.PUSH_LINK_REG);
        regs.resetAllRegisters();
        // RESET STACK POINTER
        int stackOffset = currentFunction.getTotalOffset() - calculateScopeOffset();
        if (stackOffset != 0) {
            if (stackOffset > MAX_BYTE_OFFSET) {
                codeGen.add(ARMInstructions.SUB.printWithOffset(Registers.sp.getName(), MAX_BYTE_OFFSET));
                codeGen.add(ARMInstructions.SUB.printWithOffset(Registers.sp.getName(), stackOffset - MAX_BYTE_OFFSET));
            } else {
                codeGen.add(ARMInstructions.SUB.printWithOffset(Registers.sp.getName(), stackOffset));
            }
        }
        functionsCodeGen.add(codeGen);
    }

    /*
	 * Method: calculateScopeOffset Usage: This method helps calculate the
	 * offset of SP when seeing another variable_stat based on the number of
	 * begins in the program.
	 */
    public int calculateScopeOffset() {
        if (beginOffsets.size() == 0) {
            return 0;
        }
        int result = 0;
        for (int i = beginCount; i < beginOffsets.size(); i++) {
            result += beginOffsets.get(i);
        }
        return result;
    }

	/*
	 * Method: addNumberingToOutput Usage: Helper method for printing the
	 * results to the format of the testsuite.
	 */
    public String addNumberingToOutput(String result, ArrayList<String> list, int count) {
        for (int i = 0; i < list.size(); i++) {
            result += "\n" + count + list.get(i);
            count++;
        }
        return result;
    }

    /*
     * Method: printResults Usage: This method prints out the ARM machine code
     * at the end of visiting the whole program.
     */
    public void printResults() {
        //TODO
    }

    /*
     * Method: printArrayListString Usage: Given an arraylist of strings, this
     * will print out each of the strings in the correct order.
     */
    public void printArrayListString(ArrayList<String> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }


    //...................................PARAMETER......................................

    @Override
    public LinkedList<String> visitParamList(@NotNull WACCParser.ParamListContext ctx) {
        return super.visitParamList(ctx);
    }

    @Override
    public LinkedList<String> visitParam(@NotNull WACCParser.ParamContext ctx) {
        return visitType(ctx.type());
    }


    //....................................STAT........................................

    /*SKIP*/
    @Override
    public LinkedList<String> visitSkip(@NotNull WACCParser.SkipContext ctx) {
        return null;
    }

    /* type IDENTITY EQUALS assignRHS */
    @Override
    public LinkedList<String> visitDeclare(@NotNull WACCParser.DeclareContext ctx) {
        //If not in funtion then return label main:
        checkMainSeen();

        //System.out.println(ctx.getText());

        //Get type in declaration
        String type = ctx.type().getText();

        //Check if pair type
        if(type.length() > 4) {
            //Check if pair
            if(type.substring(0, 4).compareTo("pair") == 0) {
                VisitDeclPairNode vpnode = new VisitDeclPairNode(this);
                countDeclarations++;
                if(numberOfDeclare == 1) {
                    visitDeclareNewpair(ctx);
                    //STR
                } else if(numberOfDeclare > 1) {
                    //Check that assignRHS is newpair
                    if(ctx.assignRHS().NEWPAIR() != null) {
                        visitDeclareNewpair(ctx);
                    } else {
                        String expr = ctx.assignRHS().expr(0).getText();
                        //Check if expression and if variable is in symboltable
                        if (expr != null && table.intotalVTable(expr)) { // assigned to an expression
                            instructions.add(ARMInstructions.STR.printWithAddr(reg[0], 4));
                            instructions.add(ARMInstructions.LDR.printWithAddr(reg[0], 4));
                        }
                    }
                }
                instructions.add(ARMInstructions.STR.printWithAddr(reg[0], 0));
                //Check at end of declarations
                if(countDeclarations == numberOfDeclare) {
                    vpnode.endMalloc(instructions);
                }
            }
        }
        return instructions;
    }


    private void visitDeclareNewpair(@NotNull WACCParser.DeclareContext ctx) {
        VisitDeclPairNode vpnode = new VisitDeclPairNode(this);

        //start popping registers at 1
        int posPop = 1;

        //Update PositionToPop
        PositionToPop = posPop;
        //Popr is first register to pop reg[1]
        String popr = reg[posPop];

        //Malloc the types in pair
        int[] mallocs = vpnode.spaceMalloc(ctx);
        for(int i = 0; i < 2; i++) {
            posOfnewpair = i;
            visit(ctx.assignRHS());
            instructions.add(ARMInstructions.MOV.printWithImm(reg[0], String.valueOf(mallocs[i])));
            instructions.add(ARMInstructions.BL.printWithString("malloc"));
            instructions.add(ARMInstructions.POP.printWithReg(popr));
            //malloc byte if necessary
            vpnode.mallocByte(mallocs, i, popr, instructions);
        }
        //malloc pair itself
        vpnode.mallocPair(instructions, popr, posPop);
    }

    public LinkedList<String> getInstructions() {
        return instructions;
    }

    public void setNumberOfDeclare(int numberOfDeclare) {
        this.numberOfDeclare = numberOfDeclare;
    }

    public int getNumberOfDeclare() {
        return numberOfDeclare;
    }

    public void setTable(SymbolTable table) {
        this.table = table;
    }


    /*Function to determine when to have label main: */
    private void checkMainSeen() {
        if(mainSeen == 0 && !inFunction) {
            instructions.add("main:");
            mainSeen++;

            //push{lr}
            instructions.add(ARMInstructions.PUSH_LINK_REG);

            //move base pointer -> SUB sp sp #offset
            int offset = 4 * numberOfDeclare; //calculates size of offset
            instructions.add(ARMInstructions.SUBS.printWithReg("sp", "sp", String.valueOf(offset)));
        }
    }

    /* assignLHS EQUALS assignRHS  */
    @Override
    public LinkedList<String> visitAssign(@NotNull WACCParser.AssignContext ctx) {
        return instructions;
    }

    /*PRINT expr*/
    @Override
    public LinkedList<String> visitPrint(@NotNull WACCParser.PrintContext ctx) {
        visitChildren(ctx);
        visitPrintHelper(ctx.expr(), false, false);
        return null;
    }

    /*PRINTLN expr*/
    @Override
    public LinkedList<String> visitPrintln(@NotNull WACCParser.PrintlnContext ctx) {
        visitChildren(ctx);
        visitPrintHelper(ctx.expr(), true, false);
        return null;
    }

    /*
	 * Method: visitPrintHelper Usage: This method is recursive and given an
	 * expression will print the right branch machine code instruction.
	 */
    public void visitPrintHelper(WACCParser.ExprContext ctx, boolean isprintln, boolean recurse) {
        ArrayList<String> codeGen = functionsCodeGen.get(functionsCodeGen.size() - 1);
        if (!recurse) {
            regs.freeAllRegisters();
            codeGen.add(ARMInstructions.MOV.printWithReg("r0", regs.getNonReturnRegister().getName()));
        }
        if (ctx.boolLiter() != null || ctx.OR() != null || ctx.expr() instanceof BinaryExprNode) {
            codeGen.add(ARMInstructions.BL.printWithString("p_print_bool"));
            regs.freeAllRegisters();
        } else if (ctx.intLiter() != null || ctx.DIV() != null || ctx.PLUS() != null|| ctx.MINUS() != null) {
            codeGen.add(ARMInstructions.BL.printWithString("p_print_int"));
            regs.freeAllRegisters();
        } else if (ctx.AND() != null|| ctx.OR() != null) {
            visitPrintHelper((WACCParser.ExprContext) ctx.getChild(0), false, true);
        } else if (ctx.ident() != null ) {
            Variable v = currentFunction.getVariable(ctx.getText());
            visitPrintHelperType(v.getType());
        } else if (ctx.CHR() != null) {
            codeGen.add(ARMInstructions.BL.printWithString("putchar"));
            regs.freeAllRegisters();
        } else if (ctx.stringLiter() != null) {
            codeGen.add(ARMInstructions.BL.printWithString("p_print_string"));
            regs.freeAllRegisters();
        } else if (ctx.pairLiter() != null) {
            codeGen.add(ARMInstructions.BL.printWithString("p_print_reference"));
            regs.freeAllRegisters();
        } else if (ctx.expr() instanceof UnaryExprNode) {
            if (ctx.ORD().equals(ctx.getChild(0))
                    || ctx.MINUS().equals(ctx.getChild(0))
                    || ctx.LEN().equals(ctx.getChild(0))) {
                codeGen.add(ARMInstructions.BL.printWithString("p_print_int"));
                regs.freeAllRegisters();
            } else if (ctx.NOT().equals(ctx.getChild(0))) {
                codeGen.add(ARMInstructions.BL.printWithString("p_print_bool"));
                regs.freeAllRegisters();
            } else if (ctx.CHR().equals(ctx.getChild(0))) {
                codeGen.add(ARMInstructions.BL.printWithString("putchar"));
                regs.freeAllRegisters();
            }
        } else if (ctx.arrayElem() != null) {
            Variable v = currentFunction.getVariable(ctx.getChild(0).getChild(0).getText());
            visitPrintArrayElemHelper(v.getType());
        } else {
            codeGen.add("This is a println that is not a bool or int");
        }
        if (isprintln) {
            codeGen.add(ARMInstructions.BL.printWithString("p_print_ln"));
            regs.freeAllRegisters();
        }
    }

    /*
	 * Method: visitPrintArrayElemHelper Usage: This is a helper method to take
	 * care of printing the right branch instruction for the type of the array
	 * -> digs into the ACTUAL type of the array, if the array is nested.
	 */
    public void visitPrintArrayElemHelper(WACCParser.TypeContext type) {
        if (type.getChild(0) instanceof WACCParser.BaseTypeContext
                || type.getChild(0) instanceof WACCParser.PairTypeContext) {
            visitPrintHelperType(type);
        } else {
            visitPrintArrayElemHelper((WACCParser.TypeContext) type.getChild(0));
        }
    }

    /*
     * Method: visitPrintHelperType Usage: This is a helper method to take care
     * of adding the right ARM machine code instruction, given the type of the
     * expression.
     */
    public void visitPrintHelperType(WACCParser.TypeContext type) {
        ArrayList<String> codeGen = functionsCodeGen.get(functionsCodeGen.size() - 1);
        if (type.getChild(0) instanceof WACCParser.BaseTypeContext) {
            String baseType = type.getChild(0).getText();
            if (baseType.equals(Utils.INT)) {
                codeGen.add(ARMInstructions.BL.printWithString("p_print_int"));
                regs.freeAllRegisters();
            } else if (baseType.equals(Utils.BOOL)) {
                codeGen.add(ARMInstructions.BL.printWithString("p_print_bool"));
                regs.freeAllRegisters();
            } else if (baseType.equals(Utils.CHAR)) {
                codeGen.add(ARMInstructions.BL.printWithString("putchar"));
                regs.freeAllRegisters();
            } else if (baseType.equals(Utils.STRING)) {
                codeGen.add(ARMInstructions.BL.printWithString("p_print_string"));
                regs.freeAllRegisters();
            } else {
                codeGen.add("visitPrintHelperType is having an issue with its type");
            }
        } else if (type.getChild(0) instanceof WACCParser.PairTypeContext) {
            codeGen.add(ARMInstructions.BL.printWithString("p_print_reference"));
            regs.resetAllRegisters();
        } else {
            // array type
            codeGen.add(ARMInstructions.BL.printWithString("p_print_reference"));
            regs.freeAllRegisters();
        }
    }


    /*READ assignLHS */
    @Override
    public LinkedList<String> visitRead(@NotNull WACCParser.ReadContext ctx) {
        ArrayList<String> codeGen = functionsCodeGen.get(functionsCodeGen.size() - 1);

        // making branch labels...
        String branchName;
        String s = ctx.getChild(1).getText();
        s = s.replaceAll("fst", "");
        s = s.replaceAll("snd", "");
        if (currentFunction.getVariable(s).getType().getText() == "char") {
            branchName = labeller.getReadCharLabel();
        } else {
            branchName = labeller.getReadIntLabel();
        }
        Register temp = regs.getNonReturnRegister();
        int offset = 0;
        if (ctx.assignLHS().getChild(0) instanceof WACCParser.IdentContext) {
            int varindex = currentFunction.getCorrectVariable(ctx.assignLHS().getChild(0).getText());
            offset = currentFunction.getCumulativeVariableOffset(varindex) - calculateScopeOffset();
            codeGen.add(ARMInstructions.ADD.printWithReg(temp.getName(), regs.sp.getName(), "#" + offset + ""));
            // MOV RO R4
            // BL p_read_int
            codeGen.add(ARMInstructions.MOV.printWithReg("r0", temp.getName()));
            Variable v = currentFunction.getVariable(ctx.assignLHS().getChild(0).getText());
            if (v.getType().getChild(0).getText().equals("int")) {
                codeGen.add(ARMInstructions.BL.printWithString("p_read_int"));
            } else {
                codeGen.add(ARMInstructions.BL.printWithString("p_read_char"));
            }
            regs.freeRegister(temp);
        } else if (ctx.assignLHS().getChild(0) instanceof WACCParser.PairElemContext) {
            int varindex = currentFunction.getCorrectVariable(ctx.assignLHS().getChild(0).getChild(1).getText());
            offset = currentFunction.getCumulativeVariableOffset(varindex) - calculateScopeOffset();
            codeGen.add(ARMInstructions.LDR.printWithAddr(temp.getName(), offset));
            codeGen.add(ARMInstructions.MOV.printWithReg("r0", temp.getName()));
            codeGen.add(ARMInstructions.BL.printWithString("p_check_null_pointer"));
            offset = ctx.assignLHS().getChild(0).getChild(0).getText().equals("fst") ? 0 : 4;
            codeGen.add(ARMInstructions.LDR.printWithAddrReg(temp.getName(), temp.getName(), offset));
            codeGen.add(ARMInstructions.MOV.printWithReg("r0", temp.getName()));
            Variable v = currentFunction.getVariableUsingIndex(varindex);
            WACCParser.TypeContext type = v.getType(); // pairtype
            int fstOrSnd = ctx.assignLHS().getChild(0).getChild(0).getText().equals("fst") ? 2 : 4;
            String typeString = v.getType().getChild(0).getChild(fstOrSnd).getText();
            if (typeString.equals("int")) {
                codeGen.add(ARMInstructions.BL.printWithString("p_read_int"));
            } else if (typeString.equals("char")) {
                codeGen.add(ARMInstructions.BL.printWithString("p_read_char"));
            }
        }
        return null;
    }

    /*FREE expr */
    @Override
    public LinkedList<String> visitFree(@NotNull WACCParser.FreeContext ctx) {
        instructions.add(ARMInstructions.LDR.printWithAddr(reg[0], 0));
        instructions.add(ARMInstructions.BL.printWithString("p_free_pair"));
        return visitChildren(ctx);
    }

    /*EXIT expr */
    @Override
    public LinkedList<String> visitExit(@NotNull WACCParser.ExitContext ctx) {
        return null;
    }

    /*RETURN expr*/
    @Override
    public LinkedList<String> visitReturn(@NotNull WACCParser.ReturnContext ctx) {
        return null;
    }

    /*IF ELSE stat*/
    @Override
    public LinkedList<String> visitIfElse(@NotNull WACCParser.IfElseContext ctx) {
        return null;
    }

    /*WHILE stat*/
    @Override
    public LinkedList<String> visitWhile(@NotNull WACCParser.WhileContext ctx) {
        return null;
    }

    /*BEGIN stat END*/
    @Override
    public LinkedList<String> visitBegin(@NotNull WACCParser.BeginContext ctx) {
        return null;
    }

    /*stat ; stat*/
    @Override
    public LinkedList<String> visitMultipleStat(@NotNull WACCParser.MultipleStatContext ctx) {
        for(WACCParser.StatContext stat : ctx.stat()) {
            visit(stat);
        }
        return instructions;
    }


//..................................ASSIGNMENT......................................

    /*assign-lhs*/
    @Override
    public LinkedList<String> visitAssignLHS(@NotNull WACCParser.AssignLHSContext ctx) {
        return null;
    }

    /*assign-rhs*/
    @Override
    public LinkedList<String> visitAssignRHS(@NotNull WACCParser.AssignRHSContext ctx) {
        //Check if newpair
        if(ctx.NEWPAIR() !=  null) {
            VisitDeclPairNode vpnode = new VisitDeclPairNode(this);

            //Get elements of newpair (fst, snd)
            String[] newpairElems = vpnode.getElementsNewpair(ctx);

            //If element is an int - then ldr, otherwise use mov instruction
            if(ARMInstructions.isParsable(newpairElems[posOfnewpair].trim())) {
                //LDR
                instructions.add(ARMInstructions.LDR.printWithImm(reg[0], newpairElems[posOfnewpair]));
            } else {
                //MOV
                instructions.add(ARMInstructions.MOV.printWithImm(reg[0], newpairElems[posOfnewpair]));
            }
            //PUSH
            instructions.add(ARMInstructions.PUSH.printWithReg(reg[0]));
        }
        return instructions;
    }

    @Override
    public LinkedList<String> visitArgList(@NotNull WACCParser.ArgListContext ctx) {
        return super.visitArgList(ctx);
    }

    @Override
    public LinkedList<String> visitPairElem(@NotNull WACCParser.PairElemContext ctx) {
        return null;
    }

//..................................TYPE......................................

    @Override
    public LinkedList<String> visitType(@NotNull WACCParser.TypeContext ctx) {
        return null;
    }

    @Override
    public LinkedList<String> visitBaseType(@NotNull WACCParser.BaseTypeContext ctx) {
        return null;
    }

    @Override
    public LinkedList<String> visitArrayType(@NotNull WACCParser.ArrayTypeContext ctx) {
        return null;
    }

    @Override
    public LinkedList<String> visitPairType(@NotNull WACCParser.PairTypeContext ctx) {
        return null;
    }

    @Override
    public LinkedList<String> visitPairElemType(@NotNull WACCParser.PairElemTypeContext ctx) {
        return null;
    }



    //..................................EXPRESSION......................................

    @Override
    public LinkedList<String> visitExpr(@NotNull WACCParser.ExprContext ctx) {
        //TODO;
        return null;
    }

    @Override
    public LinkedList<String> visitIdent(@NotNull WACCParser.IdentContext ctx) {
        //TODO
        return instructions;
    }

    @Override
    public LinkedList<String> visitArrayElem(@NotNull WACCParser.ArrayElemContext ctx) {
        ArrayList<String> codeGen = functionsCodeGen.get(functionsCodeGen.size() - 1);
        Variable v = currentFunction.getVariable(ctx.getChild(0).getText());
        int indexOfVar = currentFunction.getCorrectVariable(v.getName());
        int offset = currentFunction.getCumulativeVariableOffset(indexOfVar) - calculateScopeOffset();
        Register temp = regs.getNonReturnRegister();
        codeGen.add(ARMInstructions.ADD.printWithString(temp.getName() + ", sp, #" + offset));
        for (int i = 2; i < ctx.getChildCount(); i += 3) {
            visit(ctx.getChild(i)); // visits the index
            regs.freeLastRegister();
            // Register temp = regs.getNonReturnRegister();
            codeGen.add(ARMInstructions.LDR.printWithAddrReg(temp.getName(), temp.getName(), 0));
            codeGen.add(ARMInstructions.MOV.printWithReg("r0", regs.getNonReturnRegister().getName()));
            codeGen.add(ARMInstructions.MOV.printWithReg("r1", temp.getName()));
            codeGen.add(ARMInstructions.BL.printWithString("p_check_array_bounds"));
            regs.freeLastTwoNonReturnRegistersInUse(); // free 5 and 6
            temp = regs.getNonReturnRegister();
            codeGen.add(ARMInstructions.ADD.printWithOffset(temp.getName(), 4)); // this
            Register toBeDisabled = regs.getNonReturnRegister();
            int numOfBytes = Utils.getNumOfBytesForArray(v.getType());
            if (numOfBytes >= 4) {
                codeGen.add(ARMInstructions.ADD.printWith4RegsX(temp.getName(), toBeDisabled.getName(),
                        "LSL #2"));
                // codeGen.add(ArmInstructions.LDR.printWithAddressRegister(temp.getName(),
                // temp.getName(), 0));
            } else {
                codeGen.add(
                        ARMInstructions.ADD.printWithReg(temp.getName(), temp.getName(), toBeDisabled.getName()));
            }
            regs.freeRegister(toBeDisabled);

            // codeGen.add(ArmInstructions.LDR.printWithAddressRegister(temp.getName(),
            // temp.getName(), 0));
            resultReg = temp; // r4
        }
        if (!(ctx.getParent() instanceof WACCParser.AssignLHSContext)) {
            codeGen.add(ARMInstructions.LDR.printWithAddrReg(resultReg.getName(), resultReg.getName(), 0));
        }
        return null;
    }

    @Override
    public LinkedList<String> visitBoolLiter(@NotNull WACCParser.BoolLiterContext ctx) {
        //TODO
        return null;
    }

    @Override
    public LinkedList<String> visitCharLiter(@NotNull WACCParser.CharLiterContext ctx) {
        //TODO
        return null;
    }

    @Override
    public LinkedList<String> visitStringLiter(@NotNull WACCParser.StringLiterContext ctx) {
        //TODO
        return null;
    }

    @Override
    public LinkedList<String> visitIntLiter(@NotNull WACCParser.IntLiterContext ctx) {
         //TODO
        return null;
    }

    @Override
    public LinkedList<String> visitArrayLiter(@NotNull WACCParser.ArrayLiterContext ctx) {
        ArrayList<String> codeGen = functionsCodeGen.get(functionsCodeGen.size() - 1);
        int numOfBytesType = 0;
        if (ctx.getParent().getParent() instanceof WACCParser.DeclareContext) {
            numOfBytesType = Utils
                    .getNumOfBytesForTypeArrayLiter(((WACCParser.DeclareContext)
                            ctx.getParent().getParent()).type());
        } else { // assign left otherwise

            WACCParser.StatContext stat = (WACCParser.StatContext) ctx.getParent()
                    .getParent();
            numOfBytesType = Utils.getNumOfBytesForExpr(stat.getChild(0).getChild(0), currentFunction);
        }
        int mallocSize = 4;
        if (ctx.getChildCount() != 2) {
            mallocSize += numOfBytesType * (ctx.getChildCount() - 1) / 2;
        }
        Register nonReturnRegister = regs.getNonReturnRegister();
        codeGen.add(ARMInstructions.LDR.printWithImm("r0", Integer.toString(mallocSize)));
        codeGen.add(ARMInstructions.BL.printWithString("malloc"));
        codeGen.add(ARMInstructions.MOV.printWithReg(nonReturnRegister.getName(), "r0"));
        if (!(ctx.getParent().getParent() instanceof WACCParser.DeclareContext)) {
            nonReturnRegister.setRegisterArrayAccessed();
            resultReg = nonReturnRegister;
        }
        if (ctx.getChildCount() != 2) {
            for (int i = 1; i < ctx.getChildCount(); i += 2) {
                visit(ctx.getChild(i));
                if (numOfBytesType == 1) {
                    codeGen.add(ARMInstructions.STRB.printWithAddrReg(resultReg.getName(),
                            nonReturnRegister.getName(), (i - 1) / 2 * numOfBytesType + 4));
                } else {
                    codeGen.add(ARMInstructions.STR.printWithAddrReg(resultReg.getName(),
                            nonReturnRegister.getName(), (i - 1) / 2 * numOfBytesType + 4));
                }
                regs.freeLastRegister();
            }
        }
        if (ctx.getChildCount() == 2) {
            resultReg = regs.getNonReturnRegister();
        }
        codeGen.add(ARMInstructions.LDR.printWithImm(resultReg.getName(),
                Integer.toString((ctx.getChildCount() - 1) / 2)));
        codeGen.add(ARMInstructions.STR.printWithAddrReg(resultReg.getName(), nonReturnRegister.getName(), 0));
        resultReg = nonReturnRegister;
        regs.freeLastTwoNonReturnRegistersInUse();
        // regs.freeLastRegister();
        return null;
    }

    @Override
    public LinkedList<String> visitPairLiter(@NotNull WACCParser.PairLiterContext ctx) {
        //TODO
        return instructions;
    }
}



