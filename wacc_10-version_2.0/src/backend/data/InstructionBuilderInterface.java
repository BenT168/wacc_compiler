package backend.data;

import backend.label.Label;

import java.util.List;

/**
 * <h1>InstructionBuilderInterface</h1>
 * <p>Used to build instructions.</p>
 */
public interface InstructionBuilderInterface {

    InstructionBuilderInterface insertLabel(Label label);

    InstructionBuilderInterface insertDirective(Directive directive);

    InstructionBuilderInterface insertOpCode(OpCode opCode);

    InstructionBuilderInterface insertOperand(Operand operand);

    InstructionBuilderInterface insertOperands(List<Operand> operands);

    Instruction build();
}
