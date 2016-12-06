package backend.data;

import backend.label.Label;

import java.util.List;

/**
 * <h1>InstructionBuilder</h1>
 * <p>Used to build instructions.</p>
 */
public interface InstructionBuilder {

    InstructionBuilder insertLabel(Label label);

    InstructionBuilder insertDirective(Directive directive);

    InstructionBuilder insertOpCode(OpCode opCode);

    InstructionBuilder insertOperand(Operand operand);

    InstructionBuilder insertOperands(List<Operand> operands);

    Instruction build();
}
