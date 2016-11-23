package backEnd;

import antlr.WACCParser;
import backEnd.utils.ExprContext;
import backEnd.utils.Utils;
import intermediate.symboltable.SymbolTableStack;
import org.antlr.v4.runtime.misc.NotNull;

import java.io.File;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static backEnd.OpCode.*;

public class CodeGenerator extends BackEnd implements InstructionGenerator {

    private PrintWriter outputFileWriter;
    private List<Instruction> instructions;
    private int instructionCount;
    private int tempVarCount;    // Count of temporary variables used
    private int labelCount;    // Count of labels used

    public CodeGenerator() {
        instructions = new ArrayList<>();
        this.instructionCount = 0;
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

        // Close file
        outputFileWriter.close();
    }

    @Override
    public Object visitProgram(@NotNull WACCParser.ProgramContext ctx) {
        generateFunctions(ctx.func());
        generateMainFunction(ctx.stat());
        return null;
    }

    private void generateFunctions(List<WACCParser.FuncContext> ctxs) {
        for (WACCParser.FuncContext funcContext : ctxs) {
            visitFunc(funcContext);
        }
    }

    private void generateMainFunction(WACCParser.StatContext ctx) {
        visit(ctx);
    }

    @Override
    public Object visitFunc(@NotNull WACCParser.FuncContext ctx) {
        Label label = new Label(ctx.ident().getText());
        generateInstruction(label);
        return null;
    }

    @Override
    public Object visitDeclare(@NotNull WACCParser.DeclareContext ctx) {
        String var0 = newVar();
        String i = ctx.ident().getText();
        transAssignRHS(ctx.assignRHS(), var0);
        generateInstruction(LOAD_VAR, i, var0);
        return null;
    }

    private void transAssignRHS(@NotNull WACCParser.AssignRHSContext ctx, String var) {
        switch (Utils.getAssignRHSContext(ctx)) {
            case EXPR:
                transExpr(ctx.expr(0), var);
                break;
            case NEWPAIR:
                break;
            case CALL:
                break;
            case PAIR_ELEM:
                break;
            case ARRAY_LITER:
                break;
            default: break;
        }

    }

    @Override
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
    public Object visitAssignLHS(@NotNull WACCParser.AssignLHSContext ctx) {
        return super.visitAssignLHS(ctx);
    }

    /*
     ------------------------ STATEMENT TRANSLATION ------------------------
     */

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
        return super.visitExit(ctx);
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
}
