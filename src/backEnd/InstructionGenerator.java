package backEnd;

import java.util.List;

/**
 * Implementing classes generate intermediate code instructions from the
 * information given by the function parameters.
 */
public interface InstructionGenerator {

    public void generateInstruction(Label label);
    public void generateInstruction(OpCode opCode, String operand);
    public void generateInstruction(OpCode opCode, int operand);
    public void generateInstruction(OpCode opcode, String dstOperand, String operand);
    public void generateInstruction(OpCode opcode, String dstOperand, int operand);
    public void generateInstruction(OpCode opcode, String dstOperand, String operand1, String operand2);
    public void generateInstruction(OpCode opcode, String dstOperand, String strOperand1, int intOperand1);
    public void generateInstruction(OpCode opcode, String dstOperand, int operand1, int operand2);
    public void generateInstruction(OpCode opCode, String dstOperand, List<String> operands);
}
