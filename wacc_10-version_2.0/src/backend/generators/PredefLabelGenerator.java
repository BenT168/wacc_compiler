package backend.generators;

import backend.data.*;
import backend.label.Label;
import backend.label.LabelType;
import backend.register.Register;

import java.util.ArrayList;
import java.util.List;

class PredefLabelGenerator extends CodeGenerator {

    PredefLabelGenerator(CodeGenerator parent) {
        super(parent);
    }

    void generateCheckArrayBounds() {
        emitLabel(pLabelFactory.createLabel(LabelType.CHECK_ARRAY_BOUNDS));
    }

    void generateThrowOverflowError() {
        emitLabel(pLabelFactory.createLabel(LabelType.THROW_OVERFLOW_ERROR));

        // Instruction 1
        Operand operand1 = new OperandBuilderImpl().
                insertStringInput(Register.R0_REG.toString()).build();
        Label msg_label1 = nLabelFactory.createLabel(LabelType.MESSAGE_LABEL);
        Operand operand2 = new OperandBuilderImpl().insertStringInput
                (msg_label1.toString()).insertType(OperandType.DATA_TRANSFER_OPERAND)
                .build();
        Instruction instruction = new InstructionBuilderImpl().insertOpCode(OpCode.LDR)
                .insertOperand(operand1).insertOperand(operand2).build();
        emit(instruction);

        // Instruction 2
        Label label = pLabelFactory.createLabel(LabelType.THROW_RUNTIME_ERROR);
        instruction = new InstructionBuilderImpl().insertOpCode(OpCode.BL).insertLabel
                (label).build();
        emit(instruction);
    }

    void generateCheckNullPointer() {
        emitLabel(pLabelFactory.createLabel(LabelType.CHECK_NULL_POINTER));

        // Instruction 1
        Register register = Register.LR_REG;
        Operand operand1 = new OperandBuilderImpl().
                insertStringInput(register.toString())
                .insertType(OperandType.STACK_OPERAND).build();
        Instruction instruction = new InstructionBuilderImpl().
                insertOpCode(OpCode.PUSH).
                insertOperand(operand1).build();
        emit(instruction);

        // Instruction 2
        register = Register.R0_REG;
        operand1 = new OperandBuilderImpl().
                insertStringInput(register.toString()).build();
        Operand operand2 = new OperandBuilderImpl().
                insertStringInput(String.valueOf(0)).
                insertType(OperandType.IMM_OPERAND).build();
        instruction = new InstructionBuilderImpl().
                insertOpCode(OpCode.CMP).
                insertOperand(operand1).
                insertOperand(operand2).build();
        emit(instruction);

        // Instruction 3
        Label msg_label1 = nLabelFactory.createLabel(LabelType.MESSAGE_LABEL);
        String asciiMessage = "NullReferenceError: attempt to dereference a null reference\n\0";
        int wordSize = 100;
        List<Instruction> msg_instructions = generateMsgLabelCode(wordSize, asciiMessage);
        codegenInfo.addToDataSegment(msg_label1, msg_instructions);

        operand2 = new OperandBuilderImpl().
                insertStringInput(msg_label1.toString()).
                insertType(OperandType.DATA_TRANSFER_OPERAND).build();
        instruction = new InstructionBuilderImpl().
                insertOpCode(OpCode.LDREQ).
                insertOperand(operand1).
                insertOperand(operand2).build();
        emit(instruction);

        // Instruction 4
        Label label = pLabelFactory.createLabel(LabelType.THROW_RUNTIME_ERROR);
        instruction = new InstructionBuilderImpl().
                insertOpCode(OpCode.BLEQ).insertLabel(label).build();
        emit(instruction);

        // Instruction 5
        generatePopPCCode();
    }

    private void generatePushLRCode() {
        String register = Register.LR_REG.toString();
        Operand operand1 = new OperandBuilderImpl().
                insertStringInput(register).
                insertType(OperandType.STACK_OPERAND).build();
        Instruction instruction = new InstructionBuilderImpl().
                insertOpCode(OpCode.PUSH).
                insertOperand(operand1).build();
        emit(instruction);
    }

    private void generatePopPCCode() {
        Register register = Register.PC_REG;
        Operand operand1 = new OperandBuilderImpl().
                insertStringInput(register.toString()).insertType(OperandType.STACK_OPERAND).build();
        Instruction instruction = new InstructionBuilderImpl().insertOpCode(OpCode.POP).
                insertOperand(operand1).build();
        emit(instruction);
    }

    private List<Instruction> generateMsgLabelCode(final int wordSize, final String asciiMessage) {
        List<Instruction> instructions = new ArrayList<>();
        Operand operand1 = new OperandBuilderImpl().
                insertStringInput(String.valueOf(wordSize)).build();
        Instruction i1 = new InstructionBuilderImpl().
                insertDirective(Directive.WORD).
                insertOperand(operand1).build();
        instructions.add(i1);
        operand1 = new OperandBuilderImpl().insertStringInput(asciiMessage).build();
        i1 = new InstructionBuilderImpl().
                insertDirective(Directive.ASCII).
                insertOperand(operand1).build();
        instructions.add(i1);
        return instructions;
    }

    void generateFreePair() {

    }

    void generatePrintString() {

    }

    void generatePrintLine() {
        emitLabel(pLabelFactory.createLabel(LabelType.PRINT_LINE));

        // Instruction 1
        generatePushLRCode();

        // Instruction 2
        Variable v1 = newVarFactory.createNewVar();
        Operand operand1 = new OperandBuilderImpl().
                        insertStringInput(v1.toString()).build();
        Label msg_label1 = nLabelFactory.createLabel(LabelType.MESSAGE_LABEL);
        // Instructions for nul-byte terminator
        String asciiMessage = "\"\\0\"";
        int wordSize = 1;
        List<Instruction> msg_instructions = generateMsgLabelCode(wordSize, asciiMessage);
        codegenInfo.addToDataSegment(msg_label1, msg_instructions);

        Operand operand2 = new OperandBuilderImpl().
                insertStringInput(msg_label1.toString()).
                insertType(OperandType.DATA_TRANSFER_OPERAND).build();
        Instruction instruction = new InstructionBuilderImpl().
                        insertOpCode(OpCode.LDR).
                        insertOperand(operand1).insertOperand(operand2).build();
        emit(instruction);

        // Instruction 3
        Operand operand3 = new OperandBuilderImpl().
                insertStringInput(String.valueOf(4)).
                insertType(OperandType.IMM_OPERAND).build();
        instruction = new InstructionBuilderImpl().insertOpCode(OpCode.ADD).
                        insertOperand(operand1).insertOperand(operand1).
                        insertOperand(operand3).build();
        emit(instruction);

        // Instruction 4
        operand1 = new OperandBuilderImpl().insertStringInput("puts").build();
        instruction = new InstructionBuilderImpl().insertOpCode(OpCode.BL).
                insertOperand(operand1).build();
        emit(instruction);

        // Instruction 5
        operand1 = new OperandBuilderImpl().insertStringInput(v1.toString()).build();
        operand2 = new OperandBuilderImpl().
                insertStringInput(String.valueOf(0)).
                insertType(OperandType.IMM_OPERAND).build();
        instruction = new InstructionBuilderImpl().insertOpCode(OpCode.MOV).
                insertOperand(operand1).insertOperand(operand2).build();
        emit(instruction);

        // Instruction 6
        operand1 = new OperandBuilderImpl().insertStringInput("fflush").build();
        instruction = new InstructionBuilderImpl().
                insertOpCode(OpCode.BL).insertOperand(operand1).build();
        emit(instruction);

        // Instruction 7
        generatePopPCCode();
    }

    void generateCheckDivideByZero() {

    }

    void generatePrintInt() {

    }

    void generateReadChar() {

    }

    void generateReadInt() {

    }

    void generateThrowRuntimeError() {

    }
}
