package backEnd;

import antlr.WACCParser;
import backEnd.utils.ExprContext;
import backEnd.utils.Utils;
import intermediate.symboltable.SymbolTableStack;
import org.antlr.v4.runtime.misc.NotNull;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.compiler.Bytecode;

import java.io.File;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static backEnd.OpCode.*;
import static backEnd.RegisterName.LR_REG;
import static backEnd.RegisterName.PC_REG;

public class CodeGenerator extends BackEnd implements InstructionGenerator {

    /*
    ---------------------- TOP LEVEL FIELDS & METHODS --------------------------
     */
    private PrintWriter outputFileWriter;
    private List<Instruction> instructions;
    private int instructionCount;
    private int tempVarCount;    // Count of temporary variables used
    private final String T0_VAR = "t0";
    private int labelCount;    // Count of labels used

    public CodeGenerator() {
        instructions = new ArrayList<>();
        this.instructionCount = 0;
        this.tempVarCount = 1; // t0 is reserved for register r0
    }

    private String newVar() {
        String result = "t" + Integer.toString(tempVarCount);
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
    public void process(SymbolTableStack symTabStack, WACCParser.ProgramContext ctx) throws Exception {
        this.symTabStack = symTabStack;
        String assemblyFileName = "tmp.s";

        // Open file in which to write Assembly instructions.
        outputFileWriter = new PrintWriter(
                                new PrintStream(
                                        new File(assemblyFileName)));

        // Generate code
        visitProgram(ctx);

        // Write to file
        instructions.forEach(this::emitInstruction);

        // Close file
        outputFileWriter.close();
    }

    private void emitInstruction(Instruction i) {
        OpCode opCode = i.getOpCode();
        String opCodeText;
        StringBuilder sb = new StringBuilder();
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
                    if (str != null)
                        sb.append("\t" + str);
                    if (j < instrOperands.size() - 1)
                        sb.append(",");
                }
                break;
            case DIV:
                opCodeText = "SDIV";
                sb.append("\t" + opCodeText);
                for (int j = 0; j < instrOperands.size(); j++) {
                    String str = instrOperands.get(j);
                    if (str != null)
                        sb.append("\t" + str);
                    if (j < instrOperands.size() - 1)
                        sb.append(",");
                }
                break;
            case MUL:
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
                sb.append(i.getStrOperand1());
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
            default: break;
        }
        outputFileWriter.println(sb.toString());
    }

    /*
    ----------------------- PROGRAM TRANSLATION --------------------------------
     */
    @Override
    public Object visitProgram(@NotNull WACCParser.ProgramContext ctx) {
        generateFunctions(ctx.func());
        generateMainFunction(ctx.stat());
        return null;
    }

    private void generateFunctions(List<WACCParser.FuncContext> ctxs) {
        ctxs.forEach(this::visitFunc);
    }

    private void generateMainFunction(WACCParser.StatContext ctx) {
        visit(ctx);
    }

    @Override
    public Object visitFunc(@NotNull WACCParser.FuncContext ctx) {
        Label label = new Label(ctx.ident().getText());
        generateInstruction(label);
        final String LR_REG = "LR";
        generateInstruction(PUSH, LR_REG);
        return null;
    }

    /*
     ------------------------ STATEMENT TRANSLATION ------------------------
     */

    @Override
    public Object visitDeclare(@NotNull WACCParser.DeclareContext ctx) {
        generateInstruction(SUB, "sp", "sp", 4);

        String var0 = newVar();
        String i = ctx.ident().getText();
        transAssignRHS(ctx.assignRHS(), var0);
        generateInstruction(LOAD_VAR, i, var0);
        return null;
    }

    @Override
    public Object visitAssign(@NotNull WACCParser.AssignContext ctx) {
        return super.visitAssign(ctx);
    }

    private void transAssignLHS(@NotNull WACCParser.AssignLHSContext ctx, String var) {
        switch (Utils.getAssignLHSContext(ctx)) {
            case PAIR_ELEM:
                generateInstruction(LOAD_IMM, T0_VAR, 8);
                generateInstruction(BR, "malloc");
                break;
            case IDENT:
                generateInstruction(LOAD_IMM, T0_VAR, 4);
                generateInstruction(BR, "malloc");
                break;
            case ARRAY_ELEM:
                break;
        }
    }

    private void transAssignRHS(@NotNull WACCParser.AssignRHSContext ctx, String var) {
        switch (Utils.getAssignRHSContext(ctx)) {
            case EXPR:
                transExpr(ctx.expr(0), var);
                break;
            case NEWPAIR:
                String var1 = newVar();
                String var2 = newVar();

                generateInstruction(LOAD_VAR, var1, T0_VAR);

                transExpr(ctx.expr(0), var2);
                generateInstruction(LOAD_IMM, T0_VAR, 4);
                generateInstruction(BR, "malloc");

                transExpr(ctx.expr(1), var2);
                generateInstruction(STR_ADDR, var1, 0);
                generateInstruction(STR_ADDR, var2, 4); // 4-byte offset
                generateInstruction(LOAD_ADDR, var, 0); // Load address, of pair on heap, into 'var'
                break;
            case CALL:
                List<String> args = new ArrayList<>();
                for (WACCParser.ExprContext exprContext : ctx.expr()) {
                    String var0 = newVar();
                    transExpr(exprContext, var0);
                    args.add(var0);
                }
                String funcName = ctx.ident().getText();
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
        return super.visitReturn(ctx);
    }

    @Override
    public Object visitExit(@NotNull WACCParser.ExitContext ctx) {
        generateInstruction(PUSH, LR_REG.toString());
        String var0 = newVar();
        int intOperand = Integer.parseInt(ctx.expr().intLiter().getText());
        generateInstruction(LOAD_IMM, var0, intOperand);
        generateInstruction(LOAD_VAR, T0_VAR, var0);
        generateInstruction(BR, "exit");
        generateInstruction(LOAD_IMM, T0_VAR, 0);
        generateInstruction(POP, PC_REG.toString());
        return null;
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
}
