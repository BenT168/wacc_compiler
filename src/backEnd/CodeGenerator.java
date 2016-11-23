package backEnd;

import antlr.WACCParser;
import backEnd.stat.VisitDeclPairNode;
import backEnd.utils.ExprContext;
import backEnd.utils.Utils;
import frontEnd.SymbolTable;
import intermediate.symboltable.SymbolTableStack;
import org.antlr.v4.runtime.misc.NotNull;

import java.io.File;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static backEnd.OpCode.*;
import static backEnd.RegisterName.LR_REG;
import static backEnd.RegisterName.PC_REG;
import static backEnd.RegisterName.SP_REG;

public class CodeGenerator extends BackEnd implements InstructionGenerator {

    /*
    ---------------------- TOP LEVEL FIELDS & METHODS --------------------------
     */
    private PrintWriter outputFileWriter;
    private List<Instruction> instructions;
    private int instructionCount;
    private int tempVarCount;    // Count of temporary variables used
    private final String T0_VAR = "r0";
    private int labelCount;    // Count of labels used
    private SymbolTable symbolTable;

    public CodeGenerator() {
        instructions = new ArrayList<>();
        this.instructionCount = 0;
        this.tempVarCount = 1; // t0 is reserved for register r0
    }

    private String newVar() {
        String result = "r" + Integer.toString(tempVarCount);
        ++tempVarCount;
        return result;
    }

    public Label newLabel() {
        Label result = new Label("L" + Integer.toString(labelCount));
        ++labelCount;
        return result;
    }

    public List<Instruction> getInstructions() {
        return instructions;
    }

    @Override
    public void process(String file, SymbolTableStack symTabStack, WACCParser.ProgramContext ctx) throws Exception {
        this.symTabStack = symTabStack;
        String assemblyFileName = file;

        // Open file in which to write Assembly instructions.
        outputFileWriter = new PrintWriter(
                new PrintStream(
                        new File(assemblyFileName)));

        // Generate code
        visitProgram(ctx);

        // Write to file
        instructions.forEach(this::emitInstruction);
        //outputFileWriter.write(".ltorg");

        // Close file
        outputFileWriter.close();
    }


    private void emitInstruction(Instruction i) {
        OpCode opCode = i.getOpCode();
        StringBuilder sb = new StringBuilder();
        if (opCode == null) {
            //Instruction is either a label or a tag
            if (i.getLabel() != null) {
                String str = i.getLabel().toString();
                sb.append(str);
            } else {
                sb.append("\t" + i.getTag());
            }
        } else {
            String opCodeText;
            List<String> instrOperands = i.getAllOperands();

            switch (opCode) {
                case ADD:
                    opCodeText = opCode.toString();
                    sb.append("\t" + opCodeText);
                    for (int j = 0; j < instrOperands.size(); j++) {
                        String str = instrOperands.get(j);
                        if (str != null)
                            sb.append("\t" + str);
                        if (j < instrOperands.size() - 1)
                            sb.append(",");
                    }
                    break;
                case SUB:
                    opCodeText = opCode.toString();
                    sb.append("\t" + opCodeText);
                    for (int j = 0; j < instrOperands.size(); j++) {
                        String str = instrOperands.get(j);
                        if (str != null) {
                            if (isParsable(str)) {
                                sb.append(" #" + str);

                            } else {
                                sb.append(" " + str);
                            }
                        } else {
                            continue;
                        }
                        if (j < instrOperands.size() - 1)
                            sb.append(",");
                    }
                    break;
                case DIV:
                    String prefix = "\t";
                    opCodeText = "SDIV";
                    sb.append("\t" + opCodeText);
                    for (int j = 0; j < instrOperands.size(); j++) {
                        String str = instrOperands.get(j);
                        sb.append(prefix);
                        prefix = ", ";
                        sb.append(str);
                    }
                    break;
                case MUL:
                    prefix = "\t";
                    opCodeText = opCode.toString();
                    sb.append("\t" + opCodeText);
                    for (int j = 0; j < instrOperands.size(); j++) {
                        String str = instrOperands.get(j);
                        sb.append(prefix);
                        prefix = ", ";
                        sb.append(str);
                    }
                    break;
                case MOD:
                    break;
                case EQ:
                    opCodeText = "TEQ";
                    sb.append("\t" + opCodeText);
                    break;
                case NEQ:
                    break;
                case AND:
                    break;
                case OR:
                    break;
                case GT:
                    break;
                case GTE:
                    break;
                case LT:
                    break;
                case LTE:
                    break;
                case CALL:
                    break;
                case RETURN:
                    break;
                case LOAD_VAR:
                    opCodeText = "MOV";
                    sb.append("\t" + opCodeText);
                    sb.append("\t" + i.getDstOperand() + ", ");
                    if (i.getIntOperand1() == 0 && i.getStrOperand1() == null) {
                        sb.append("#" + i.getIntOperand1());
                    } else {
                        sb.append(i.getStrOperand1());
                    }
                    break;
                case LOAD_IMM:
                    opCodeText = "LDR";
                    sb.append("\t" + opCodeText);
                    sb.append("\t" + i.getDstOperand() + ", =");
                    sb.append(Integer.valueOf(i.getIntOperand1()).toString());
                    break;
                case LOAD_ADDR:
                    break;
                case STR_ADDR:
                    opCodeText = "STR";
                    sb.append("\t" + opCodeText);
                    sb.append("\t" + i.getDstOperand() + ", ");
                    sb.append("\t" + "[" + i.getStrOperand1());
                    if (i.isIntOper1()) {
                        sb.append(", #" + Integer.valueOf(i.getIntOperand1()).toString());
                    }
                    sb.append("]");
                    break;
                case JMP:
                    break;
                case BR:
                    opCodeText = "BL";
                    sb.append("\t" + opCodeText);
                    sb.append("\t" + i.getStrOperand1());
                    break;
                case CMP:
                    break;
                case PUSH:
                    opCodeText = opCode.toString();
                    sb.append("\t" + opCodeText);
                    sb.append("\t" + i.getStrOperand1());
                    break;
                case POP:
                    opCodeText = opCode.toString();
                    sb.append("\t" + opCodeText);
                    sb.append("\t" + i.getStrOperand1());
                    break;
                default:
                    break;
            }
        }
        outputFileWriter.println(sb.toString());
    }


    /*
    ----------------------- PROGRAM TRANSLATION --------------------------------
     */
    @Override
    public Object visitProgram(@NotNull WACCParser.ProgramContext ctx) {
        outputFileWriter.write(".text\n\n");
        outputFileWriter.write(".global main\n");
        generateFunctions(ctx.func());
        generateMainFunction(ctx.stat());

        return null;
    }

    private void generateFunctions(List<WACCParser.FuncContext> ctxs) {
        ctxs.forEach(this::visitFunc);
    }

    private void generateMainFunction(WACCParser.StatContext ctx) {
        Label label = new Label("main");
        generateInstruction(label);
        generateInstruction(PUSH, LR_REG.toString());
        visit(ctx);
        generateInstruction(LOAD_IMM, T0_VAR, 0);
        generateInstruction(POP, LR_REG.toString());
    }


    @Override
    public Object visitFunc(@NotNull WACCParser.FuncContext ctx) {
        Label label = new Label(ctx.ident().getText());
        generateInstruction(label);
        generateInstruction(PUSH, LR_REG.toString());
        visit(ctx.stat());
//        generateInstruction(LOAD_IMM, T0_VAR, 0);
//        generateInstruction(POP, LR_REG.toString());
        generateInstruction(".ltorg");
        return null;
    }

    /*
     ------------------------ STATEMENT TRANSLATION ------------------------
     */

    @Override
    public Object visitDeclare(@NotNull WACCParser.DeclareContext ctx) {
        String var0 = newVar();
        String i = ctx.ident().getText();
        transAssignRHS(ctx.assignRHS(), var0);
        generateInstruction(LOAD_VAR, i, var0);
        generateInstruction(STR_ADDR, i, SP_REG.toString());
        return null;
    }

    @Override
    public Object visitAssign(@NotNull WACCParser.AssignContext ctx) {
        return super.visitAssign(ctx);
    }

    private void transAssignRHS(@NotNull WACCParser.AssignRHSContext ctx, String var) {
        switch (Utils.getAssignRHSContext(ctx)) {
            case EXPR:
                transExpr(ctx.expr(0), var);
                break;
            case NEWPAIR:
                String var1 = newVar();

                generateInstruction(LOAD_VAR, var, T0_VAR);

                transExpr(ctx.expr(0), var1);
                generateInstruction(LOAD_IMM, T0_VAR, 4);
                generateInstruction(BR, "malloc");
                generateInstruction(STR_ADDR, var1, T0_VAR);
                generateInstruction(STR_ADDR, T0_VAR, var);

                transExpr(ctx.expr(1), var1);
                generateInstruction(LOAD_IMM, T0_VAR, 4);
                generateInstruction(BR, "malloc");
                generateInstruction(STR_ADDR, var1, T0_VAR);
                generateInstruction(STR_ADDR, T0_VAR, var, 4);

                generateInstruction(STR_ADDR, var, SP_REG.toString());
                break;
            case CALL:
                String funcName = ctx.ident().getText();
                generateInstruction(BR, funcName);
                List<String> args = new ArrayList<>();
                for (WACCParser.ExprContext exprContext : ctx.expr()) {
                    String var0 = newVar();
                    transExpr(exprContext, var0);
                    args.add(var0);
                }
                generateInstruction(CALL, funcName, args);
                break;
            case PAIR_ELEM:
                if (ctx.ident() == null) {
                    System.err.println("Identifier not passed to pairElem, in expression: " + ctx.pairElem().getText());
                    System.exit(-1);
                }
                String p_var1 = newVar();
                transExpr(ctx.pairElem().expr(), p_var1);
                if (ctx.pairElem().FST() != null) {
                    generateInstruction(LOAD_ADDR, var, p_var1);
                }
                break;
            case ARRAY_LITER:
                String a_var1 = newVar();
                transExpr(ctx.arrayLiter().expr(0), a_var1);
                generateInstruction(STR_ADDR, a_var1, 0);
                generateInstruction(LOAD_ADDR, var, 0);
                break;
            default: break;
        }

    }

    private void transCond(Label label1, Label label2, WACCParser.ExprContext ctx) {
        if (Utils.getExprContext(ctx) != ExprContext.BINARY_EXPR) {
            System.err.println("Invalid comparison expression: " + ctx.getText());
            System.exit(-1);
        }
        String var1 = newVar();
        String var2 = newVar();
        transExpr(ctx.expr(0), var1);
        transExpr(ctx.expr(1), var2);
        generateInstruction(CMP, var1, var2);
        generateInstruction(Utils.getBinaryOpCode(ctx), var1, var2);
        generateInstruction(JMP, label1.toString());
        generateInstruction(JMP, label2.toString());
    }

    @Override
    public Object visitWhile(@NotNull WACCParser.WhileContext ctx) {
        Label label1 = newLabel();
        Label label2 = newLabel();
        Label label3 = newLabel();
        generateInstruction(label1);
        transCond(label2, label3, ctx.expr());
        generateInstruction(label2);
        visit(ctx.stat());
        generateInstruction(JMP, label1.toString());
        generateInstruction(label3);
        return null;
    }

    public Object visitIfElse(@NotNull WACCParser.IfElseContext ctx) {
        Label label1 = newLabel();
        Label label2 = newLabel();
        Label label3 = newLabel();
        transCond(label1, label2, ctx.expr());
        generateInstruction(label1);
        visit(ctx.stat(0));
        generateInstruction(JMP, label3.toString());
        generateInstruction(label2);
        visit(ctx.stat(1));
        generateInstruction(label3);
        return null;
    }

    @Override
    public Object visitRead(@NotNull WACCParser.ReadContext ctx) {
        return null;
    }

    @Override
    public Object visitFree(@NotNull WACCParser.FreeContext ctx) {
        return super.visitFree(ctx);
    }

    @Override
    public Object visitReturn(@NotNull WACCParser.ReturnContext ctx) {
        if(ctx.expr().intLiter() != null) {
            String var0 = newVar();
            generateInstruction(LOAD_IMM, var0, 0);
            generateInstruction(LOAD_VAR, T0_VAR, var0);
            generateInstruction(POP, PC_REG.toString());
        }
        return super.visitReturn(ctx);
    }

    @Override
    public Object visitExit(@NotNull WACCParser.ExitContext ctx) {
        generateInstruction(PUSH, LR_REG.toString());
        String var0 = newVar();
        if(ctx.expr().MINUS() != null && ctx.expr().getChild(2) == null) {//negative number
            int intOperand = ifNumGetNum(ctx.expr().getText());
            generateInstruction(LOAD_IMM, var0, intOperand);
        } else if(ctx.expr().intLiter() != null) {
         int intOperand = Integer.parseInt(ctx.expr().intLiter().getText());
            generateInstruction(LOAD_IMM, var0, intOperand);
        } else if (ctx.expr() != null) {
            String var1 = newVar();
            transExpr(ctx.expr(), var1);
            generateInstruction(LOAD_VAR, var0, var1);
        } else {
            System.err.println("Cannot resolve exit expression: " + ctx.expr());
        }
        generateInstruction(LOAD_VAR, T0_VAR, var0);
        generateInstruction(BR, "exit");
        generateInstruction(LOAD_IMM, T0_VAR, 0);
        generateInstruction(POP, PC_REG.toString());
        generateInstruction(".ltorg");
        return null;
    }

    private int ifNumGetNum(String expr) {
        if(expr.charAt(0) == '-') {
            //This is a negative number
            String str = expr.substring(1);
            int i = Integer.parseInt(str);
            return i * -1;
        }
        return Integer.parseInt(expr);
    }

    @Override
    public Object visitPrint(@NotNull WACCParser.PrintContext ctx) {
        return super.visitPrint(ctx);
    }

    @Override
    public Object visitPrintln(@NotNull WACCParser.PrintlnContext ctx) {
        return super.visitPrintln(ctx);
    }

    @Override
    public Object visitBegin(@NotNull WACCParser.BeginContext ctx) {
        return super.visitBegin(ctx);
    }

    @Override
    public Object visitSkip(@NotNull WACCParser.SkipContext ctx) {
        generateInstruction(PUSH, LR_REG.toString());
        generateInstruction(LOAD_VAR, T0_VAR, 0);
        generateInstruction(POP, PC_REG.toString());
        return super.visitSkip(ctx);
    }

    /*
    ----------------------- EXPRESSION TRANSLATION ----------------------------
     */

    private void transExpr(@NotNull WACCParser.ExprContext ctx, String var0) {
         switch (Utils.getExprContext(ctx)) {
             case INT_LITER:
                 int intVal = Integer.parseInt(ctx.intLiter().getText());
                 generateInstruction(LOAD_IMM, var0, intVal);
                 break;
             case BOOL_LITER:
                 int boolVal = ctx.boolLiter().TRUE() != null ? 1 : 0;
                 generateInstruction(LOAD_IMM, var0, boolVal);
                 break;
             case CHAR_LITER:
                 break;
             case STR_LITER:
                 break;
             case PAIR_LITER:
                 break;
             case UNARY_EXPR:
                 // TODO
                 String unaryVar1 = newVar();
                 transExpr(ctx.expr(0), unaryVar1);
                 OpCode u_opCode = Utils.getUnaryOpCode(ctx);
                 generateInstruction(u_opCode, var0, unaryVar1);
                 break;
             case BINARY_EXPR:
                 String binaryVar1 = newVar();
                 String binaryVar2 = newVar();
                 transExpr(ctx.expr(0), binaryVar1);
                 transExpr(ctx.expr(1), binaryVar2);
                 OpCode b_opCode = Utils.getBinaryOpCode(ctx);
                 generateInstruction(b_opCode, var0, binaryVar1, binaryVar2);
                 break;
         }
    }



    /*
    --------------------------- INSTRUCTION GENERATION -------------------------
    */

    @Override
    public void generateInstruction(String popTag) {
        Instruction i = new Instruction(popTag);
        instructions.add(i);
        ++instructionCount;
        }

    @Override
    public void generateInstruction(Label label) {
        Instruction i = new Instruction(label);
        instructions.add(i);
        ++instructionCount;
    }

    @Override
    public void generateInstruction(OpCode opCode, String operand) {
        Instruction i = new Instruction(opCode, operand);
        instructions.add(i);
        ++instructionCount;
    }

    @Override
    public void generateInstruction(OpCode opcode, String dstOperand, String operand) {
        Instruction i = new Instruction(opcode, dstOperand, operand);
        instructions.add(i);
        ++instructionCount;
    }

    @Override
    public void generateInstruction(OpCode opcode, String dstOperand, int operand) {
        Instruction i = new Instruction(opcode, dstOperand, operand);
        instructions.add(i);
        ++instructionCount;
    }

    @Override
    public void generateInstruction(OpCode opcode, String dstOperand, String operand1, String operand2) {
        Instruction i = new Instruction(opcode, dstOperand, operand1, operand2);
        instructions.add(i);
        ++instructionCount;
    }

    @Override
    public void generateInstruction(OpCode opcode, String dstOperand, int operand1, int operand2) {
        Instruction i = new Instruction(opcode, dstOperand, operand1, operand2);
        instructions.add(i);
        ++instructionCount;
    }

    @Override
    public void generateInstruction(OpCode opCode, String dstOperand, List<String> operands) {
        Instruction i = new Instruction(opCode, dstOperand, operands);
        instructions.add(i);
        ++instructionCount;
    }

    @Override
    public void generateInstruction(OpCode opcode, String dstOperand, String strOperand1, int intOperand1) {
        Instruction i = new Instruction(opcode, dstOperand, strOperand1, intOperand1);
        instructions.add(i);
        ++instructionCount;
    }

    @Override
    public void generateInstruction(OpCode opCode, int operand) {
        Instruction i = new Instruction(opCode, operand);
        instructions.add(i);
        ++instructionCount;
    }

    public void setSymbolTable(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    private boolean isParsable(String num) {
        try {
            Integer.parseInt(num);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

}
