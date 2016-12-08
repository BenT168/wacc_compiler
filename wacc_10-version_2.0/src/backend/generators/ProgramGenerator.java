package backend.generators;

import antlr.WACCParser;
import backend.data.*;
import backend.label.Label;
import backend.label.LabelType;
import backend.register.Register;
import backend.symtab.Attribute;

import java.util.*;

class ProgramGenerator extends CodeGenerator {

    ProgramGenerator(CodeGenerator parent) {
        super(parent);
    }

    public void generate(WACCParser.ProgramContext ctx) {
        Map<String, Attribute> initialSymTab = new HashMap<>();
        symTabStack.add(initialSymTab);

        generateFunctions(ctx);
        generateMainFunction(ctx);
        generateFooter(ctx);
        generateHeader(ctx);
    }

    private void generateHeader(WACCParser.ProgramContext ctx) {
        Map<Label, List<Instruction>> entries = codegenInfo.getDataSegment();
        prefix_emit(entries.entrySet());
    }

    private void generateFunctions(WACCParser.ProgramContext ctx) {
        FunctionGenerator functionGenerator = new FunctionGenerator(this);
        ctx.func().forEach(functionGenerator::generate);
    }

    private void generateMainFunction(WACCParser.ProgramContext ctx) {
        emitLabel(pLabelFactory.createLabel(LabelType.MAIN_FUNCTION_LABEL));

        // Instruction: PUSH {lr}
        Register lr = Register.LR_REG;
        Operand operand1 = buildOperand(lr.toString(), OperandType.STACK_OPERAND);
        Instruction i1   = buildInstruction(OpCode.PUSH, operand1);
        emit(i1);

        StatementGenerator statementGenerator = new StatementGenerator(this);
        statementGenerator.generate(ctx.stat());

        // Instruction: LDR r0, =0
        int exitCode     = 0;
        Register r0      = Register.R0_REG;
        operand1         = buildOperand(r0.toString());
        Operand operand2 = buildOperand(String.valueOf(exitCode));
        Instruction i2   = buildInstruction(OpCode.LDR, operand1, operand2);
        emit(i2);

        // Instruction: POP {pc}
        Register pc    = Register.PC_REG;
        operand1       = buildOperand(pc.toString(), OperandType.STACK_OPERAND);
        Instruction i3 = buildInstruction(OpCode.POP, operand1);
        emit(i3);

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
