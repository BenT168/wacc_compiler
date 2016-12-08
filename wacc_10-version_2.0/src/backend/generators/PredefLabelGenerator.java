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
        Label label = pLabelFactory.createLabel(LabelType.CHECK_ARRAY_BOUNDS);
        emitLabel(label);

    }

    void generateThrowOverflowError() {
        Label label = pLabelFactory.createLabel(LabelType.THROW_OVERFLOW_ERROR);
        emitLabel(label);

        // Instruction 1
        Label msg_label1         = nLabelFactory.createLabel(LabelType.MESSAGE_LABEL);
        Operand operand1         = buildOperand(Register.R0_REG.toString());
        Operand operand2         = buildOperand(msg_label1.toString(), OperandType.DATA_TRANSFER_OPERAND);
        Instruction instruction1 = buildInstruction(OpCode.LDR, operand1, operand2);
        emit(instruction1);

        // Instruction 2
        Label label1              = pLabelFactory.createLabel(LabelType.THROW_RUNTIME_ERROR);
        Instruction instruction2  = buildInstruction(OpCode.BL, label1);
        emit(instruction2);
    }

    void generateCheckNullPointer() {
        Label label = pLabelFactory.createLabel(LabelType.CHECK_NULL_POINTER);
        emitLabel(label);

        // Instruction 1
        Register register = Register.LR_REG;
        Operand operand1 = buildOperand(register.toString(), OperandType.STACK_OPERAND);
        Instruction i1 = buildInstruction(OpCode.PUSH, operand1);
        emit(i1);

        // Instruction 2
        int systemCode   = 0;
        register         = Register.R0_REG;
        operand1         = buildOperand(register.toString());
        Operand operand2 = buildOperand(String.valueOf(systemCode), OperandType.IMM_OPERAND);
        Instruction i2   = buildInstruction(OpCode.CMP, operand1, operand2);
        emit(i2);

        // Instruction 3
        Label msg_label1    = nLabelFactory.createLabel(LabelType.MESSAGE_LABEL);
        String asciiMessage = "NullReferenceError: attempt to dereference a null reference\n\0";
        int wordSize        = asciiMessage.length();

        List<Instruction> msg_instructions = generateMsgLabelCode(wordSize, asciiMessage);
        codegenInfo.addToDataSegment(msg_label1, msg_instructions);

        operand2       = buildOperand(msg_label1.toString(), OperandType.DATA_TRANSFER_OPERAND);
        Instruction i3 = buildInstruction(OpCode.LDREQ, operand1, operand2);
        emit(i3);

        // Instruction 4
        Label label1    = pLabelFactory.createLabel(LabelType.THROW_RUNTIME_ERROR);
        Instruction i4  = buildInstruction(OpCode.BLEQ, label1);
        emit(i4);

        // Instruction 5
        generatePopPCCode();
    }

    private void generatePushLRCode() {
        Register register       = Register.LR_REG;
        Operand operand1        = buildOperand(register.toString(), OperandType.STACK_OPERAND);
        emit(buildInstruction(OpCode.PUSH, operand1));
    }

    private void generatePopPCCode() {
        Register register       = Register.PC_REG;
        Operand operand1        = buildOperand(register.toString(), OperandType.STACK_OPERAND);
        emit(buildInstruction(OpCode.POP, operand1));
    }

    private List<Instruction> generateMsgLabelCode(final int wordSize, final String asciiMessage) {
        List<Instruction> instructions = new ArrayList<>();

        Operand operand1 = buildOperand(String.valueOf(wordSize));
        Instruction i1   = buildInstruction(Directive.WORD, operand1);
        Operand operand2 = buildOperand(asciiMessage);
        Instruction i2   = buildInstruction(Directive.ASCII, operand2);
        instructions.add(i1);
        instructions.add(i2);

        return instructions;
    }

    void generateFreePair() {
        
    }

    void generatePrintString() {
        
    }

    void generatePrintLine() {
        
        Label label = pLabelFactory.createLabel(LabelType.PRINT_LINE);
        emitLabel(label);

        // Instruction 1
        generatePushLRCode();

        // Instruction 2
        Variable v1      = newVarFactory.createNewVar();
        Label msg_label1 = nLabelFactory.createLabel(LabelType.MESSAGE_LABEL);

        // Data for nul-byte terminator
        String asciiMessage = PredefData.NUL_BYTE.toString();
        int wordSize        = asciiMessage.length();

        List<Instruction> msg_instructions = generateMsgLabelCode(wordSize, asciiMessage);
        codegenInfo.addToDataSegment(msg_label1, msg_instructions);

        Operand operand1  = buildOperand(v1.toString());
        Operand operand2  = buildOperand(msg_label1.toString(), OperandType.DATA_TRANSFER_OPERAND);
        Instruction i2    = buildInstruction(OpCode.LDR, operand1, operand2);
        emit(i2);

        // Instruction 3
        int bytesCount   = 4;
        Operand operand3 = buildOperand(String.valueOf(bytesCount), OperandType.IMM_OPERAND);
        Instruction i3   = buildInstruction(OpCode.ADD, operand1, operand1, operand3);
        emit(i3);

        // Instruction 4
        Label branchLabel = pLabelFactory.createLabel(LabelType.PUTS);
        Instruction i4    = buildInstruction(OpCode.BL, branchLabel);
        emit(i4);

        // Instruction 5
        int systemCode = 0;
        operand2       = buildOperand(String.valueOf(systemCode), OperandType.IMM_OPERAND);
        Instruction i5 = buildInstruction(OpCode.MOV, operand1, operand2);
        emit(i5);

        // Instruction 6
        branchLabel    = pLabelFactory.createLabel(LabelType.F_FLUSH);
        Instruction i6 = buildInstruction(OpCode.BL, branchLabel);
        emit(i6);

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
