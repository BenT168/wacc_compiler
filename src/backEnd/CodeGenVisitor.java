package backEnd;

import antlr.WACCParser;
import antlr.WACCParserBaseVisitor;
import backEnd.helper.*;
import backEnd.helper.Function;
import backEnd.helper.Utils;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.ParseTree;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static backEnd.helper.Utils.isBinaryOper;
import static backEnd.helper.Utils.isIdent;
import static backEnd.helper.Utils.isLiter;

public class CodeGenVisitor extends WACCParserBaseVisitor<LinkedList<String>> {

    private List<ArrayList<ArrayList<String>>> INSTRUCTIONS = new LinkedList<>();

    // CONSTANTS
    private static final int MAX_BYTE_OFFSET = 1024;

    private ArrayList<ArrayList<String>> functionsCodeGen = new ArrayList<>();
    private ArrayList<Function> listOfFunctions = new ArrayList<>();
    private ArrayList<String> stringsInProgram = new ArrayList<>();
    private ArrayList<Integer> beginOffsets;


    public ArrayList<String> buffer = new ArrayList<>();

    private Function currentFunction = null;
    private boolean seenFirstBegin = false;

    private Registers regs = new Registers();
    private Register resultReg = null;
    private Register SAVE_REG = null;
    private Labeller labeller = new Labeller();
    private int beginCount = 0;
    private int variableCount = 0;

    private SystemReadTokens systemReadTokens = new SystemReadTokens();

    private int messagesLoaded = 0;
    private String currentIdent = "";
    private int r11Count = 0;
    final String _false = "0";
    final String _true = "1";
    //private boolean optimisationsTurnedOn;

    public String getLabel(String s) {
        return "msg_" + messagesLoaded++;
    }

    public List<ArrayList<ArrayList<String>>> getINSTRUCTIONS() {
        return INSTRUCTIONS;
    }


    public CodeGenVisitor(WACCParser.ProgramContext tree) {
        systemReadTokens.visit(tree);
        listOfFunctions = systemReadTokens.getListOfFunctions();
        stringsInProgram = systemReadTokens.getStringsInProgram();
        beginOffsets = systemReadTokens.getBeginOffsets();
        this.visit(tree);
        printResults(); //Function to update INSTRUCTIONS for writing to file in Main
    }


    //................................PROGRAM.........................................
    @Override
    public LinkedList<String> visitProgram(@NotNull WACCParser.ProgramContext ctx) {
        for (int i = 0; i < ctx.func().size(); i++) {
            visitFunc(ctx.func().get(i));
        }
        initiateFunction("main", true);
        visit(ctx.BEGIN());
        visit(ctx.stat());
        visitEnd();
        return null;
    }

    private void visitEnd() {
        ArrayList<String> codeGen = functionsCodeGen.get(functionsCodeGen.size() - 1);
        if (beginCount >= 1) {
            int offset = 0;
            if (beginOffsets.size() != 0) {
                offset = beginOffsets.get(beginCount);
                if (offset != 0)
                    codeGen.add(ARMInstructions.ADD.printWithOffset(Registers.sp.getName(), offset));
            }
        } else if (currentFunction.getTotalOffset() != 0) {
            // check function before to see if it ends on return
            if (!currentFunction.equals("main")) {
                if (currentFunction.getTotalOffset() > MAX_BYTE_OFFSET) {
                    codeGen.add((codeGen.size() - 1), ARMInstructions.ADD.printWithOffset(Registers.sp.getName(),
                            currentFunction.getTotalOffset() - MAX_BYTE_OFFSET));
                    codeGen.add((codeGen.size() - 1),
                            ARMInstructions.ADD.printWithOffset(Registers.sp.getName(), MAX_BYTE_OFFSET));

                } else {
                    codeGen.add((codeGen.size() - 1), ARMInstructions.ADD.printWithOffset(Registers.sp.getName(),
                            currentFunction.getTotalOffset()));
                }
            } else {
                if (currentFunction.getTotalOffset() > MAX_BYTE_OFFSET) {
                    codeGen.add(ARMInstructions.ADD.printWithOffset(Registers.sp.getName(), MAX_BYTE_OFFSET));
                    codeGen.add(ARMInstructions.ADD.printWithOffset(Registers.sp.getName(),
                            currentFunction.getTotalOffset() - MAX_BYTE_OFFSET));

                } else {
                    if (currentFunction.getTotalOffset() - calculateScopeOffset() != 0)
                        codeGen.add(ARMInstructions.ADD.printWithOffset(Registers.sp.getName(),
                                (currentFunction.getTotalOffset()) - calculateScopeOffset()));
                }
            }
        }
        if (beginCount == 0 && currentFunction.getName().equals("main")) {
            // Should regs.getReturnRegister be fixed to r0?
            codeGen.add(ARMInstructions.LDR.printWithImm("r0", Utils.DEFAULT_RETURN_REG_VALUE));
        }
        if (beginCount == 0) {
            codeGen.add(ARMInstructions.POP_PC);
            codeGen.add(ARMInstructions.LTORG_DIRECTIVE);
        }
        if (currentFunction.getName().equals("main")) {
            currentFunction.resetScopeCounts(beginCount);
            beginCount = 0;
        }
    }




    //....................................FUNCTION......................................

    // Function identifiers and types should already be added to ftable by time we call this method.
    @Override
    public LinkedList<String> visitFunc(@NotNull WACCParser.FuncContext ctx) {
        String functionName = ctx.ident().getText();
        initiateFunction(functionName, false);
        visit(ctx.stat());
        return null;
    }

    /*BEGIN backEnd.stat END*/
    @Override
    public LinkedList<String> visitBegin(@NotNull WACCParser.BeginContext ctx) {
        ArrayList<String> codeGen = functionsCodeGen.get(functionsCodeGen.size() - 1);
        int offset = 0;
        if (beginOffsets.size() != 0) {
            offset = beginOffsets.get(beginCount);
            codeGen.add(ARMInstructions.SUB.printWithOffset(Registers.sp.getName(), offset));
        }
        beginCount++;
        visitChildren(ctx);
        return null;
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
        ArrayList<String> codeGen = new ArrayList<>();
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
            ///result += beginOffsets.get(i);
            result+= beginCount;
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
        ArrayList<String> messages = systemReadTokens.getMessages();
        buffer = messages;
        ArrayList<String> first = functionsCodeGen.get(0);

        first.add(0, ARMInstructions.GLOBAL_MAIN_DIRECTIVE);
        first.add(0, "");
        first.add(0, ARMInstructions.TEXT_DIRECTIVE);

        //Loop through funcCodeGen and p_print to load into buffer for writing in main
        ArrayList<String> funcs = new ArrayList<>();
        for (int i = 0; i < functionsCodeGen.size(); i++) {
            //printArrayListString(functionsCodeGen.get(i));
            funcs = writeListString(functionsCodeGen.get(i));
        }

        ArrayList<ArrayList<String>> p_prints = systemReadTokens.getP_Prints();
        ArrayList<String> codes = new ArrayList<>();


        //Loop through p_print to add to codes
        for(int i = 0; i < p_prints.size(); i++) {
            for(int j = 0; j < p_prints.get(i).size(); j++) {
                codes.add(p_prints.get(i).get(j));
            }
        }


        /*for(int i = 0; i < codes.size(); i++) {
            System.out.println(codes.get(i));
        }*/

        //Fill up buffer with elements from both lists

        int start = buffer.size();
        int totalSize = start + funcs.size() + codes.size();

        int count = start;
        int funcPos = 0;
        int codesPos = 0;
        for(int i = start; i < totalSize; i++) {
            if(count < funcs.size() + start) {
                buffer.add(i, funcs.get(funcPos));
                funcPos++;
            } else {
                buffer.add(i, codes.get(codesPos));
                codesPos++;
            }
            count++;
        }
    }



    /*
        * Method: writeListString Usage: Given an arraylist of strings, this
         * will store the strings in an ArrayList  in the correct order.
         */
    public ArrayList<String> writeListString(ArrayList<String> list) {
        ArrayList<String> bf = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            bf.add(i, list.get(i));
        }
        return bf;
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
        variableCount++;
        visit(ctx.assignRHS()); // LOAD OR MOVE INSTRUCTION
        storeInstructionForVariable(ctx.type(), ctx.ident().getText());
        regs.freeAllRegisters();
        return null;
    }

    public void storeInstructionForVariable(WACCParser.TypeContext ctx, String ident) {
        int varIndex = currentFunction.getCorrectVariable(ident);
        int offset = 0;
        if (beginCount > 0) {
            currentFunction.setScopeValueIfIdentPresent(variableCount, ident, beginCount);
            varIndex = currentFunction.getCorrectVariable(ident);
            ctx = currentFunction.getVariableUsingIndex(varIndex).getType();
        }
        offset = currentFunction.getCumulativeVariableOffset(varIndex) - calculateScopeOffset();
        ArrayList<String> codeGen = functionsCodeGen.get(functionsCodeGen.size() - 1);
        int bytes = Utils.getNumOfBytesForType(ctx);
        if (bytes == 1) {
            codeGen.add(ARMInstructions.STRB.printWithAddr(resultReg.getName(), offset));
        } else {
            codeGen.add(ARMInstructions.STR.printWithAddr(resultReg.getName(), offset));
        }
    }



    /* assignLHS EQUALS assignRHS  */
    @Override
    public LinkedList<String> visitAssign(@NotNull WACCParser.AssignContext ctx) {
        ArrayList<String> codeGen = functionsCodeGen.get(functionsCodeGen.size() - 1);
        visit(ctx.assignRHS());
        if (ctx.assignLHS().getChild(0) instanceof WACCParser.IdentContext) {
            String identName = ctx.assignLHS().getChild(0).getText();
            storeInstructionForVariable(currentFunction.getVariable(identName).getType(), identName);
        } else if (ctx.assignLHS().getChild(0) instanceof WACCParser.ArrayElemContext) {
            visit(ctx.assignLHS().getChild(0));
            Variable v = currentFunction.getVariable(ctx.assignLHS().getChild(0).getChild(0).getText());
            int numOfBytes = Utils.getNumOfBytesForType(v.getType());
            regs.freeLastTwoNonReturnRegistersInUse();
            regs.freeLastRegister(); // Slightly dodgy, but freeing three
            // registers to get r4 and r5.
            if (numOfBytes == 1 || v.getType().getChild(0).getText().equals("string")) {
                if (ctx.assignRHS().getChild(0) instanceof WACCParser.ArrayLiterContext) {
                    codeGen.add(ARMInstructions.STRB.printWithAddrReg(regs.getLastDisabled().getName(),
                            resultReg.getName(), 0));
                } else {
                    codeGen.add(ARMInstructions.STRB.printWithAddrReg(regs.getNonReturnRegister().getName(),
                            regs.getNonReturnRegister().getName(), 0));
                }
            } else {
                if (ctx.assignRHS().getChild(0) instanceof WACCParser.ArrayLiterContext) {
                    codeGen.add(ARMInstructions.STR.printWithAddrReg(regs.getLastDisabled().getName(),
                            resultReg.getName(), 0));
                } else {
                    codeGen.add(ARMInstructions.STR.printWithAddrReg(regs.getNonReturnRegister().getName(),
                            regs.getNonReturnRegister().getName(), 0));
                }
            }
            resultReg.setRegisterArrayAccessed();
        } else if ((ctx.assignLHS().getChild(0)) instanceof WACCParser.PairElemContext) {
            Register temp = regs.getNonReturnRegister();
            int offset = currentFunction.getCumulativeVariableOffset(
                    currentFunction.getCorrectVariable(ctx.assignLHS().getChild(0).getChild(1).getText())
                            - calculateScopeOffset());
            codeGen.add(ARMInstructions.LDR.printWithAddr(temp.getName(), offset));
            codeGen.add(ARMInstructions.MOV.printWithReg("r0", temp.getName()));
            codeGen.add(ARMInstructions.BL.printWithString("p_check_null_pointer"));
            int sndfst = ctx.assignLHS().getChild(0).getChild(0).getText().equals("fst") ? 0 : 4;
            codeGen.add(ARMInstructions.LDR.printWithAddrReg(temp.getName(), temp.getName(), sndfst));
            WACCParser.TypeContext type = currentFunction.getVariable(ctx.getChild(0).getChild(0).getChild(1).getText()).getType();
            if (sndfst == 0) {
                sndfst = 2;
            }
            int numOfBytes = Utils.getNumOfBytesForType(type.getChild(0).getChild(sndfst));
            if (numOfBytes == 1) {
                codeGen.add(ARMInstructions.STRB.printWithAddrReg(resultReg.getName(), temp.getName(), 0));
            } else {
                codeGen.add(ARMInstructions.STR.printWithAddrReg(resultReg.getName(), temp.getName(), 0));
            }
        }

        regs.freeLastTwoNonReturnRegistersInUse();
        return null;    }

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
        if(ctx.boolLiter() != null || ctx.AND() != null || ctx.OR() != null) {
            //|| ctx instanceof WACCParser.Binary_oper_other_exprContext) {
            codeGen.add(ARMInstructions.BL.printWithString("p_print_bool"));
            regs.freeAllRegisters();
        } else if(ctx.intLiter() != null || ctx.MOD() != null || ctx.DIV() != null || ctx.MUL() != null ||
                ctx.PLUS() != null || ctx.MINUS() != null) {
            codeGen.add(ARMInstructions.BL.printWithString("p_print_int"));
            regs.freeAllRegisters();
        } else if (ctx.AND() != null || ctx.OR() != null) {
            visitPrintHelper((WACCParser.ExprContext) ctx.getChild(0), false, true);
        } else if (ctx.getChild(0) instanceof WACCParser.IdentContext) {
            Variable v = currentFunction.getVariable(ctx.getText());
            visitPrintHelperType(v.getType());
        } else if (ctx.getChild(0) instanceof WACCParser.CharLiterContext) {
            codeGen.add(ARMInstructions.BL.printWithString("putchar"));
            regs.freeAllRegisters();
        } else if (ctx.getChild(0) instanceof WACCParser.StringLiterContext) {
            codeGen.add(ARMInstructions.BL.printWithString("p_print_string"));
            regs.freeAllRegisters();
        } else if (ctx.getChild(0) instanceof WACCParser.PairLiterContext) {
            codeGen.add(ARMInstructions.BL.printWithString("p_print_reference"));
            regs.freeAllRegisters();
        } else if (ctx.NOT() != null ||  ctx.MINUS() != null || ctx.LEN() != null || ctx.ORD() != null
                ||ctx.CHR() != null) {
            if (ctx.ORD() != null || ctx.MINUS() != null || ctx.LEN() != null) {
                codeGen.add(ARMInstructions.BL.printWithString("p_print_int"));
                regs.freeAllRegisters();
            } else if (ctx.NOT() != null) {
                codeGen.add(ARMInstructions.BL.printWithString("p_print_bool"));
                regs.freeAllRegisters();
            } else if (ctx.CHR() != null) {
                codeGen.add(ARMInstructions.BL.printWithString("putchar"));
                regs.freeAllRegisters();
            }
        } else if (ctx.getChild(0) instanceof WACCParser.ArrayElemContext) {
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
	 * Method: visitPrintArrayElemHelper Usage: This is a backEnd.helper method to take
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
     * Method: visitPrintHelperType Usage: This is a backEnd.helper method to take care
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
        ArrayList<String> codeGen = functionsCodeGen.get(functionsCodeGen.size() - 1);
        if (ctx.getChild(1) instanceof WACCParser.IdentContext) {
            String name = ctx.getChild(1).getText();
            WACCParser.TypeContext type = currentFunction.getVariable(name).getType();
            visit(ctx.getChild(1));
            codeGen.add(ARMInstructions.MOV.printWithReg("r0", "r4"));
            if (type.getChild(0) instanceof WACCParser.PairTypeContext) {
                codeGen.add(ARMInstructions.BL.printWithString("p_free_pair"));
                regs.freeAllRegisters();
            } else if (!(type.getChild(0) instanceof WACCParser.BaseTypeContext)) {
                codeGen.add(ARMInstructions.BL.printWithString("p_free_array"));
                regs.freeAllRegisters();
            }
        }
        return null;
    }

    /*EXIT expr */
    @Override
    public LinkedList<String> visitExit(@NotNull WACCParser.ExitContext ctx) {
        ArrayList<String> toAddto = returnOrExit(ctx.expr());
        toAddto.add(ARMInstructions.BL.printWithString("exit"));
        regs.resetAllRegisters();
        return null;
    }




    /*RETURN expr*/
    @Override
    public LinkedList<String> visitReturn(@NotNull WACCParser.ReturnContext ctx) {
        ArrayList<String> toAddto = returnOrExit(ctx.expr());
        toAddto.add(ARMInstructions.POP.printWithReg(regs.pc.getName()));
        return null;
    }

    public ArrayList<String> returnOrExit(WACCParser.ExprContext ctx) {
        ArrayList<String> toAddto = functionsCodeGen.get(functionsCodeGen.size() - 1);
        visit(ctx);
        toAddto.add(ARMInstructions.MOV.printWithReg("r0", resultReg.getName()));
        regs.freeAllRegisters();
        return toAddto;
    }

    /*IF ELSE backEnd.stat*/
    @Override
    public LinkedList<String> visitIfElse(@NotNull WACCParser.IfElseContext ctx) {
        ArrayList<String> codeGen = functionsCodeGen.get(functionsCodeGen.size() - 1);

        // making branches...
        String branchName1 = labeller.getNewLabel();
        String branchName2 = labeller.getNewLabel();

        // visiting expressions...
        visit(ctx.getChild(1));

        // getting registers...
		/*
		 * reg1 and reg2 should be the registers assigned by visiting the
		 * expressions, "randomReg1" and "RandomReg2" used for now.
		 */

        codeGen.add(ARMInstructions.CMP.printWithImm(resultReg.getName(), "0"));
        codeGen.add(ARMInstructions.BEQ.printWithString(branchName1));
        regs.freeAllRegisters();
        visit(ctx.getChild(3));
        codeGen.add(ARMInstructions.B.printWithString(branchName2));

        codeGen.add("\t" + branchName1 + ":");
        visit(ctx.getChild(5));
        codeGen.add("\t" + branchName2 + ":");

        return null;
    }

    /*WHILE backEnd.stat*/
    @Override
    public LinkedList<String> visitWhile(@NotNull WACCParser.WhileContext ctx) {
        ArrayList<String> codeGen = functionsCodeGen.get(functionsCodeGen.size() - 1);

        String beginWhile = labeller.getNewLabel();
        String endWhile = labeller.getNewLabel();

        codeGen.add(ARMInstructions.B.printWithString(beginWhile));
        codeGen.add("\t" + endWhile + ":");

        if (ctx.stat() != null) {
            visit(ctx.stat());
        }

        codeGen.add("\t" + beginWhile + ":");
        visit(ctx.expr());
        codeGen.add(ARMInstructions.CMP.printWithImm(resultReg.getName(), _true));

        codeGen.add(ARMInstructions.BEQ.printWithString(endWhile));
        regs.freeAllRegisters();
        return null;
    }



    /*backEnd.stat ; backEnd.stat*/
    @Override
    public LinkedList<String> visitMultipleStat(@NotNull WACCParser.MultipleStatContext ctx) {
        for(WACCParser.StatContext stat : ctx.stat()) {
            visit(stat);
        }
        return null;
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
        if(ctx.NEWPAIR() != null) {
            visitNewpair_assignrhs(ctx);
        } else if(ctx.pairElem() != null) {
            visitPair_elem_assignrhs(ctx.pairElem());
        } else if(ctx.CALL() != null) {
            visitCall(ctx);
        } else if(ctx.arrayLiter() != null) {
            visitArrayLiter(ctx.arrayLiter());
        } else {
            visit(ctx.expr().get(0));
        }
        return null;
    }

    public Void visitCall(WACCParser.AssignRHSContext ctx) {

        // ASSUMING PARENT IS VARIBALE STAT
        ArrayList<String> codeGen = functionsCodeGen.get(functionsCodeGen.size() - 1);
        int totalOffsetForParameters = 0;

        if (ctx.argList() != null) {
            for (int i = ctx.argList().getChildCount() - 1; i >= 0; i -= 2) {
                visit(ctx.argList().getChild(i));
                int bytesForParameter = Utils.getNumOfBytesForExpr(ctx.argList().getChild(i),
                        currentFunction);
                storeInstructionForCall(bytesForParameter);
                regs.freeAllRegisters();
                totalOffsetForParameters += bytesForParameter;
                currentFunction.setOffsetFromParameters(totalOffsetForParameters);
            }

        }
        codeGen.add(ARMInstructions.BL.printWithString("f_" + ctx.ident().getText()));
        regs.resetAllRegisters();
        if (totalOffsetForParameters != 0) {
            codeGen.add(ARMInstructions.ADD.printWithOffset(Registers.sp.getName(), totalOffsetForParameters));
            currentFunction.setOffsetFromParameters(0);
        }
        resultReg = regs.getNonReturnRegister();
        codeGen.add(ARMInstructions.MOV.printWithReg(resultReg.getName(), "r0"));
        return null;
    }

    public void storeInstructionForCall(int numOfBytes) {

        ArrayList<String> codeGen = functionsCodeGen.get(functionsCodeGen.size() - 1);
        if (numOfBytes == 1) {
            codeGen.add(ARMInstructions.STRB.printCalledFunc(resultReg.getName()));
        } else {
            codeGen.add(ARMInstructions.STR.printCalledFunc(resultReg.getName()));
        }

    }



    public Void visitPair_elem_assignrhs(WACCParser.PairElemContext ctx) {
        ArrayList<String> codeGen = functionsCodeGen.get(functionsCodeGen.size() - 1);
        visit(ctx.getChild(0).getChild(1)); //
        codeGen.add(ARMInstructions.MOV.printWithReg("r0", resultReg.getName()));
        codeGen.add(ARMInstructions.BL.printWithString("p_check_null_pointer"));
        int offset = ctx.getChild(0).getChild(0).getText().equals("fst") ? 0 : 4;
        codeGen.add(ARMInstructions.LDR.printWithAddrReg(resultReg.getName(), resultReg.getName(), offset));
        WACCParser.TypeContext type = currentFunction.getVariable(ctx.getChild(0).getChild(1).getText()).getType(); // pair
        // type
        int fstOrSnd = ctx.getChild(0).getChild(0).getText().equals("fst") ? 2 : 4;
        int numOfBytes = Utils.getNumOfBytesForType(type.getChild(0).getChild(fstOrSnd));
        if (numOfBytes == 1) {
            codeGen.add(ARMInstructions.LDRSB.printWithAddrReg(resultReg.getName(), resultReg.getName(), 0));
        } else {
            codeGen.add(ARMInstructions.LDR.printWithAddrReg(resultReg.getName(), resultReg.getName(), 0));
        }
        return null;
    }

    public Void visitNewpair_assignrhs(WACCParser.AssignRHSContext ctx) {
        ArrayList<String> codeGen = functionsCodeGen.get(functionsCodeGen.size() - 1);
        codeGen.add(ARMInstructions.LDR.printWithImm("r0", "8"));
        codeGen.add(ARMInstructions.BL.printWithString("malloc"));
        Register temp = regs.getNonReturnRegister();
        codeGen.add(ARMInstructions.MOV.printWithReg(temp.getName(), "r0"));
        for (int i = 2; i < 5; i += 2) {
            visit(ctx.getChild(i));
            int sizeOfType = Utils.getNumOfBytesForExpr(ctx.getChild(i), currentFunction);
            codeGen.add(ARMInstructions.LDR.printWithImm("r0", "" + sizeOfType));
            codeGen.add(ARMInstructions.BL.printWithString("malloc"));
            int offset = i == 2 ? 0 : 4;
            if (sizeOfType == 1) {
                codeGen.add(ARMInstructions.STRB.printWithAddrReg(resultReg.getName(), "r0", 0));
            } else {
                codeGen.add(ARMInstructions.STR.printWithAddrReg(resultReg.getName(), "r0", 0));
            }
            codeGen.add(ARMInstructions.STR.printWithAddrReg("r0", temp.getName(), offset));
            regs.freeRegister(resultReg);
        }
        resultReg = temp;
        return null;
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
        if(Utils.isUnaryOper(ctx)) {
            if(ctx.CHR() != null || ctx.ORD() != null) {
                visit(ctx);
            } else if(ctx.LEN() != null) {
                ArrayList<String> codeGen = functionsCodeGen.get(functionsCodeGen.size() - 1);
                visit(ctx);
                codeGen.add(ARMInstructions.LDR.printWithAddrReg(resultReg.getName(), resultReg.getName(), 0));
            } else if(ctx.MINUS() != null) {
                visitMinus(ctx);
            }
        } else if(ctx.arrayElem() != null) {
            visitArrayElem_expr(ctx);
        } else if(Utils.isBinaryOper(ctx)) {
            if(ctx.expr(0).ident() != null) {
                visitIdent(ctx.expr(0).ident());
            }
            if(ctx.expr(1).ident() != null) {
                visitIdent(ctx.expr(1).ident());
            }
            //visit(ctx.expr(0));
            //visit(ctx.expr(1));
            ArrayList<String> codeGen = functionsCodeGen.get(functionsCodeGen.size() - 1);
                visitBinaryOper(ctx);
            if(ctx.expr(0).ident() == null && ctx.expr(1).ident() == null) {
                codeGen.add(ARMInstructions.EOR.printWithOffset(resultReg.getName(), 1));
            }
        } else {
            if(ctx.intLiter() != null) {
                visitIntLiter(ctx.intLiter());
            }
            if(ctx.stringLiter() != null) {
                visitStringLiter(ctx.stringLiter());
            }
            if(ctx.pairLiter() != null) {
                visitPairLiter(ctx.pairLiter());
            }
            if(ctx.boolLiter() != null) {
                visitBoolLiter(ctx.boolLiter());
            } /*if(ctx.ident() != null) {
                visitIdent((ctx.ident()));
            }*/

        }
        return null;
    }

    private void visitArrayElem_expr(WACCParser.ExprContext ctx) {
        visitChildren(ctx);
        regs.freeLastRegister();
    }




    private void visitMinus(WACCParser.ExprContext ctx) {
        ArrayList<String> codeGen = functionsCodeGen.get(functionsCodeGen.size() - 1);
        if (ctx.expr(0).intLiter() != null) {
            String intLiter = ctx.expr(0).getText();
            resultReg = regs.getNonReturnRegister();
            codeGen.add(ARMInstructions.LDR.printWithImm(resultReg.getName(), "-" + intLiter));
        } else {
            codeGen.add(ARMInstructions.RSBS.printWithOffset(resultReg.getName(), 0));
            if (ctx.ident() != null) {
                codeGen.add(ARMInstructions.BLVS.printWithString("p_throw_overflow_error"));
                regs.resetAllRegisters();
            }
        }
    }

    private void visitBinaryOper(WACCParser.ExprContext ctx) {
        Register temp = regs.getNonReturnRegister();
        //Register t = new Register("r4");
        //regs.freeRegister(temp);
        visitBinary(ctx, temp, false);
        //regs.freeRegister(temp);
    }

    private void visitBinary(ParseTree parseTree, Register nonReturnRegister, boolean recurse) {

        if (!recurse) {
            regs.freeAllRegisters();
            recurse = true;
        }
        WACCParser.ExprContext ctx = (WACCParser.ExprContext) parseTree;
        if ((isLiter(ctx.expr(0)) || isIdent(ctx.expr(0))) && (isLiter(ctx.expr(1)) || isIdent(ctx.expr(1)))) {
            // base case both sides bool liter
            //regs.freeLastRegister();
            visit(ctx.expr(0));
            visit(ctx.expr(1));
        } else if (isBinaryOper(ctx.getChild(0)) && isLiter(ctx.expr(1))) {
            visitBinary(ctx.getChild(0), nonReturnRegister, recurse);
            visit(ctx.getChild(2));
        } else if (isLiter(ctx.expr(0)) && isBinaryOper(ctx.expr(1))) {
            regs.freeLastRegister();
            visit(ctx.getChild(0));
            visitBinary(ctx.expr(1), regs.getNonReturnRegister(), recurse);
        } else if (isBinaryOper(ctx.getChild(0)) && isBinaryOper(ctx.getChild(2))) {
            visitBinary(ctx.getChild(0), nonReturnRegister, recurse);
            visitBinary(ctx.getChild(1), regs.getNonReturnRegister(), recurse);
        } else if (ctx.getChild(0) instanceof WACCParser.ExprContext && isLiter(ctx.expr(1))) {
            visitBinary(ctx.getChild(0).getChild(1), nonReturnRegister, recurse);
            visit(ctx.getChild(2));
        } else if (isLiter(ctx.expr(0)) && ctx.expr(1) != null) {
            regs.freeLastRegister();
            visit(ctx.getChild(0));
            visitBinary(ctx.getChild(2).getChild(1), regs.getNonReturnRegister(), recurse);
        } else if (ctx.getChild(0) instanceof WACCParser.ExprContext && ctx.getChild(1) instanceof WACCParser.ExprContext) {
            visitBinary(ctx.getChild(0).getChild(1), nonReturnRegister, recurse);
            visitBinary(ctx.getChild(1).getChild(0), regs.getNonReturnRegister(), recurse);
        }
            printBinaryInstruction(ctx, nonReturnRegister);
    }


    private void printBinaryInstruction(ParseTree parseTree, Register nonReturnRegister) {
        WACCParser.ExprContext ctx = (WACCParser.ExprContext) parseTree;
        ArrayList<String> codeGen = functionsCodeGen.get(functionsCodeGen.size() - 1);
        if (ctx.AND() != null) {
            //Register temp = resultReg;
           /* //System.out.println(nonReturnRegister.getName());
            regs.freeRegister(nonReturnRegister);
            nonReturnRegister = regs.getNonReturnRegister();
            if(nonReturnRegister.getName().compareTo(resultReg.getName()) == 0) {
                resultReg = regs.getNonReturnRegister();
            }*/
            codeGen.add(ARMInstructions.AND.printWithReg(nonReturnRegister.getName(), nonReturnRegister.getName(),
                    resultReg.getName()));
        } else if (ctx.OR() != null) {
            codeGen.add(ARMInstructions.ORR.printWithReg(nonReturnRegister.getName(), nonReturnRegister.getName(),
                    resultReg.getName()));
        } else if (ctx.PLUS() != null) {
            if (r11Count > 0) {
                codeGen.add(ARMInstructions.POP.printWithReg("r11"));
                codeGen.add(ARMInstructions.ADDS.printWithReg("r10", "r11", "r10"));
                r11Count--;
            } else {
                codeGen.add(ARMInstructions.ADDS.printWithReg(nonReturnRegister.getName(),
                        nonReturnRegister.getName(), resultReg.getName()));
            }
            codeGen.add(ARMInstructions.BLVS.printWithString("p_throw_overflow_error"));
        } else if (ctx.MINUS() != null) {
            codeGen.add(ARMInstructions.SUBS.printWithReg(nonReturnRegister.getName(), nonReturnRegister.getName(),
                    resultReg.getName()));
            codeGen.add(ARMInstructions.BLVS.printWithString("p_throw_overflow_error"));
        } else if (ctx.MUL() != null) {
            codeGen.add(ARMInstructions.SMULL.printWith4Regs(nonReturnRegister.getName(), resultReg.getName()));
            codeGen.add(
                    ARMInstructions.CMP.printWithReg(resultReg.getName(), nonReturnRegister.getName(), "ASR #31"));
            codeGen.add(ARMInstructions.BLNE.printWithString("p_throw_overflow_error"));
        } else if (ctx.MOD() != null) {
            codeGen.add(ARMInstructions.MOV.printWithReg("r0", nonReturnRegister.getName()));
            codeGen.add(ARMInstructions.MOV.printWithReg("r1", resultReg.getName()));
            codeGen.add(ARMInstructions.BL.printWithString("p_check_divide_by_zero"));
            codeGen.add(ARMInstructions.BL.printWithString("__aeabi_idivmod"));
            codeGen.add(ARMInstructions.MOV.printWithReg(nonReturnRegister.getName(), "r1"));
        } else if (ctx.DIV() != null) {
            codeGen.add(ARMInstructions.MOV.printWithReg("r0", nonReturnRegister.getName()));
            codeGen.add(ARMInstructions.MOV.printWithReg("r1", resultReg.getName()));
            codeGen.add(ARMInstructions.BL.printWithString("p_check_divide_by_zero"));
            codeGen.add(ARMInstructions.BL.printWithString("__aeabi_idiv"));
            codeGen.add(ARMInstructions.MOV.printWithReg(nonReturnRegister.getName(), "r0"));
        } else if (ctx.GT() != null) {
            codeGen.add(ARMInstructions.CMP.printWithReg(nonReturnRegister.getName(), resultReg.getName()));
            codeGen.add(ARMInstructions.MOVGT.printWithImm(nonReturnRegister.getName(), "1"));
            codeGen.add(ARMInstructions.MOVLE.printWithImm(nonReturnRegister.getName(), "0"));
        } else if (ctx.GTE() != null) {
            codeGen.add(ARMInstructions.CMP.printWithReg(nonReturnRegister.getName(), resultReg.getName()));
            codeGen.add(ARMInstructions.MOVGE.printWithImm(nonReturnRegister.getName(), "1"));
            codeGen.add(ARMInstructions.MOVLT.printWithImm(nonReturnRegister.getName(), "0"));
        } else if (ctx.LTE() != null) {
            codeGen.add(ARMInstructions.CMP.printWithReg(nonReturnRegister.getName(), resultReg.getName()));
            codeGen.add(ARMInstructions.MOVLE.printWithImm(nonReturnRegister.getName(), "1"));
            codeGen.add(ARMInstructions.MOVGT.printWithImm(nonReturnRegister.getName(), "0"));
        } else if (ctx.LT() != null) {
            codeGen.add(ARMInstructions.CMP.printWithReg(nonReturnRegister.getName(), resultReg.getName()));
            codeGen.add(ARMInstructions.MOVLT.printWithImm(nonReturnRegister.getName(), "1"));
            codeGen.add(ARMInstructions.MOVGE.printWithImm(nonReturnRegister.getName(), "0"));
        } else if (ctx.EQ() != null) {
            codeGen.add(ARMInstructions.CMP.printWithReg(nonReturnRegister.getName(), resultReg.getName()));
            codeGen.add(ARMInstructions.MOVEQ.printWithImm(nonReturnRegister.getName(), "1"));
            codeGen.add(ARMInstructions.MOVNE.printWithImm(nonReturnRegister.getName(), "0"));
        } else if (ctx.NEQ() != null) {
            codeGen.add(ARMInstructions.CMP.printWithReg(nonReturnRegister.getName(), resultReg.getName()));
            codeGen.add(ARMInstructions.MOVNE.printWithImm(nonReturnRegister.getName(), "1"));
            codeGen.add(ARMInstructions.MOVEQ.printWithImm(nonReturnRegister.getName(), "0"));
        }
        regs.freeRegister(resultReg);
        resultReg = nonReturnRegister;
    }


    @Override
    public LinkedList<String> visitIdent(WACCParser.IdentContext ctx) {
        ArrayList<String> codeGen = functionsCodeGen.get(functionsCodeGen.size() - 1);
        int indexVar = currentFunction.getCorrectVariable(ctx.getText());
        int offset = currentFunction.getCumulativeVariableOffset(indexVar) - calculateScopeOffset();

        resultReg = regs.getNonReturnRegister();
        if (currentFunction.getVariable(ctx.getText()).getOffset() == 1) {
            codeGen.add(ARMInstructions.LDRSB.printWithAddr(resultReg.getName(), offset));
            System.out.println(resultReg.getName());
            //regs.freeRegister();
        } else {
            codeGen.add(ARMInstructions.LDR.printWithAddr(resultReg.getName(), offset));
        }
        return null;
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
        ArrayList<String> codeGen = functionsCodeGen.get(functionsCodeGen.size() - 1);
        String bool = ctx.getText().equals("true") ? "1" : "0";
        resultReg = regs.getNonReturnRegister();
        codeGen.add(ARMInstructions.MOV.printWithImm(resultReg.getName(), bool));
        regs.freeAllRegisters();
        return null;
    }

    @Override
    public LinkedList<String> visitCharLiter(@NotNull WACCParser.CharLiterContext ctx) {
        ArrayList<String> codeGen = functionsCodeGen.get(functionsCodeGen.size() - 1);
        resultReg = regs.getNonReturnRegister();
        String s = ctx.getText();

        if (s.equals("'\\0'")) {
            codeGen.add(ARMInstructions.MOV.printWithImm(resultReg.getName(), 0 + ""));
        } else {
            s = s.replaceAll("(\\\\|\\\\n|)", "");
            s = s.replaceAll("\\\"", "\"");
            codeGen.add(ARMInstructions.MOV.printWithImm(resultReg.getName(), s));
        }
        return null;
    }

    @Override
    public LinkedList<String> visitStringLiter(@NotNull WACCParser.StringLiterContext ctx) {
        ArrayList<String> codeGen = functionsCodeGen.get(functionsCodeGen.size() - 1);
        String messageLabel = getLabel(ctx.getText());
        resultReg = regs.getNonReturnRegister();
        codeGen.add(ARMInstructions.LDR.printWithImm(resultReg.getName(), messageLabel));
        return null;
    }

    @Override
    public LinkedList<String> visitIntLiter(@NotNull WACCParser.IntLiterContext ctx) {
        ArrayList<String> codeGen = functionsCodeGen.get(functionsCodeGen.size() - 1);
        resultReg = regs.getNonReturnRegister();
        if (resultReg.getName().equals("r11")) {
            regs.freeLastTwoNonReturnRegistersInUse();
            resultReg = regs.getNonReturnRegister();
            codeGen.add(ARMInstructions.PUSH.printWithReg("r10"));
            r11Count++;
        }
        String s = ctx.getText();
        while (s.charAt(0) == '0') {
            if (s.length() == 1) {
                break;
            }
            s = s.substring(1);
        }
        codeGen.add(ARMInstructions.LDR.printWithImm(resultReg.getName(), s));
        return null;
    }

    @Override
    public LinkedList<String> visitArrayLiter(@NotNull WACCParser.ArrayLiterContext ctx) {
        ArrayList<String> codeGen = functionsCodeGen.get(functionsCodeGen.size() - 1);
        int numOfBytesType = 0;
        if (ctx.getParent().getParent() instanceof WACCParser.DeclareContext) {
            numOfBytesType = Utils
                    .getNumOfBytesForTypeArrayLiter(((WACCParser.DeclareContext) ctx.getParent().getParent()).type());
        } else { // assign left otherwise

            WACCParser.AssignContext stat = (WACCParser.AssignContext) ctx.getParent()
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
        ArrayList<String> codeGen = functionsCodeGen.get(functionsCodeGen.size() - 1);
        Register temp = regs.getNonReturnRegister();
        codeGen.add(ARMInstructions.LDR.printWithImm(temp.getName(), "0"));
        resultReg = temp;
        return null;
    }
}



