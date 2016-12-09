package backend.generators;

import antlr.WACCParser;
import backend.data.*;
import backend.label.Label;
import org.antlr.v4.runtime.misc.NotNull;

import java.io.File;

public interface CodeGeneratorInterface {
    void process(@NotNull WACCParser.ProgramContext ctx, File outputFile);

    Operand buildOperand(String input);

    /**
     * Method used to construct with and offset operands. Implementatoins will
     * typically use an implementation of the {@link OperandBuilderInterface}
     * interface.
     * @param input The operand input as a string.
     * @param offset The offset, either for a shifted operand or as an offset
     *               in a memory address operand.
     * @param isOffset True if the offset is for a memory address operand.
     *                 False if it is for a shifted, immediate operand.
     * @return
     */
    Operand buildOperand(String input, int offset, OperandType operandType);

    Operand buildOperand(String input, OperandType type);

    Instruction buildInstruction(OpCode opCode, Label label);

    Instruction buildInstruction(OpCode opCode, Operand... operands);

    Instruction buildInstruction(Directive directive, Operand operand);

    Instruction buildInstruction(Directive directive);
}
