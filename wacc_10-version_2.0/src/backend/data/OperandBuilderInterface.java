package backend.data;

public interface OperandBuilderInterface {
    /**
     * All operand builders should initialise operand types to 'OperandType.DEFAULT_OPERAND',
     * and only change the operand type when the following method is called with the given
     * parameter.
     */
    OperandBuilderInterface insertType(OperandType type);

    OperandBuilderInterface insertStringInput(String input);

    OperandBuilderInterface insertShiftValue(int shiftValue);

    OperandBuilderInterface insertOffset(int offset);

    Operand build();
}
