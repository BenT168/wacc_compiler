package backend.data;

public interface OperandBuilder {
    /**
     * All operand builders should initialise operand types to 'OperandType.DEFAULT_OPERAND',
     * and only change the operand type when the following method is called with the given
     * parameter.
     */
    OperandBuilder insertType(OperandType type);

    OperandBuilder insertStringInput(String input);

    Operand build();
}
