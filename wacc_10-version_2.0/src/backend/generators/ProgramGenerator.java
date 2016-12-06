package backend.generators;

import antlr.WACCParser;
import backend.data.*;
import backend.label.Label;
import backend.label.LabelType;
import backend.register.Register;

import java.util.List;
import java.util.Map;
import java.util.Set;

class ProgramGenerator extends CodeGenerator {

    ProgramGenerator(CodeGenerator parent) {
        super(parent);
    }

    void generate(WACCParser.ProgramContext ctx) {
        generateFunctions(ctx);
        generateMainFunction(ctx);
        generateFooter(ctx);
        generateHeader(ctx);
    }

    private void generateHeader(WACCParser.ProgramContext ctx) {
        // Data Segment code generation
        Map<Label, List<Instruction>> dataSegment = codegenInfo.getDataSegment();
        Set<Map.Entry<Label, List<Instruction>>> entries = dataSegment.entrySet();
        prefix_emit(entries);
    }

    private void generateFunctions(WACCParser.ProgramContext ctx) {
        FunctionGenerator functionGenerator = new FunctionGenerator(this);
        ctx.func().forEach(functionGenerator::generate);
    }

    private void generateMainFunction(WACCParser.ProgramContext ctx) {
        emitLabel(pLabelFactory.createLabel(LabelType.MAIN_FUNCTION_LABEL));

        // Instruction: PUSH {lr}
        Register lr = Register.LR_REG;
        Operand operand1 = new OperandBuilderImpl().
                insertStringInput(lr.toString()).
                insertType(OperandType.STACK_OPERAND).build();
        Instruction i1 = new InstructionBuilderImpl().
                insertOpCode(OpCode.PUSH).
                insertOperand(operand1).build();
        emit(i1);

        StatementGenerator statementGenerator = new StatementGenerator(this);
        statementGenerator.generate(ctx.stat());

        // Instruction: POP {pc}
        Register pc = Register.PC_REG;
        operand1 = new OperandBuilderImpl().
                insertStringInput(pc.toString()).
                insertType(OperandType.STACK_OPERAND).build();
        Instruction i2 = new InstructionBuilderImpl().
                insertOpCode(OpCode.POP).
                insertOperand(operand1).build();
        emit(i2);

        emitDirective(Directive.LTORG);
    }

    private void generateFooter(WACCParser.ProgramContext ctx) {
        // We generate the code for predefined labels in this method.
        // Each predefined label is associated with a list of instructions.
        // Each instruction can be viewed as an association between an opcode
        // and a list of operands.
        for (Label label : codegenInfo.getPredefinedLabelsReferenced()) {
            generatePredefLabelsCode(label);
        }
    }

    private void generatePredefLabelsCode(Label label) {
        PredefLabelGenerator pGenerator = new PredefLabelGenerator(this);
        switch (label.getLabelType()) {
            case CHECK_ARRAY_BOUNDS:
                pGenerator.generateCheckArrayBounds();
                break;
            case THROW_OVERFLOW_ERROR:
                pGenerator.generateThrowOverflowError();
                break;
            case CHECK_NULL_POINTER:
                pGenerator.generateCheckNullPointer();
                break;
            case FREE_PAIR:
                pGenerator.generateFreePair();
                break;
            case PRINT_STRING:
                pGenerator.generatePrintString();
                break;
            case PRINT_LINE:
                pGenerator.generatePrintLine();
                break;
            case CHECK_DIVIDE_BY_ZERO:
                pGenerator.generateCheckDivideByZero();
                break;
            case PRINT_INT:
                pGenerator.generatePrintInt();
                break;
            case READ_CHAR:
                pGenerator.generateReadChar();
                break;
            case READ_INT:
                pGenerator.generateReadInt();
                break;
            case THROW_RUNTIME_ERROR:
                pGenerator.generateThrowRuntimeError();
                break;
        }
    }
}
