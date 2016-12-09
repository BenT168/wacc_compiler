package backend.data;

public class OperandBuilder implements OperandBuilderInterface {
    private Operand operand = new Operand();
    private String operandText;
    private OperandType operandType;
    private int shiftValue;
    private String preBuildOperandText;
    private int offset;

    public OperandBuilder() {
        // Set operand type to default
        operand.setOperandType(OperandType.DEFAULT_OPERAND);
        this.operandType = OperandType.DEFAULT_OPERAND;
    }

    @Override
    public OperandBuilderInterface insertType(OperandType type) {
        this.operandType = type;
        operand.setOperandType(type);
        return this;
    }

    @Override
    public OperandBuilderInterface insertStringInput(String input) {
        operandText = input;
        return this;
    }

    @Override
    public OperandBuilderInterface insertShiftValue(int shiftValue) {
        this.shiftValue = shiftValue;
        operand.setShiftValue(shiftValue);
        return this;
    }

    public OperandBuilderInterface insertOffset(int offset) {
        this.offset = offset;
        operand.setOffset(offset);
        return this;
    }

    @Override
    public Operand build() {
        this.preBuildOperandText = operandText;
        switch (this.operandType) {
            case IMM_OPERAND:
                operandText = "#" + operandText;
                break;
            case MEM_ADDR_OPERAND:
                operandText = "[" + operandText + "]";
                break;
            case STACK_OPERAND:
                operandText = "{" + operandText + "}";
                break;
            case DATA_TRANSFER_OPERAND:
                operandText = "=" + operandText;
                break;
            case SHIFT_OPERAND:
                operandText += ", LSL #" + String.valueOf(shiftValue);
                break;
            case MEM_ADDR_WITH_OFFSET_OPERAND:
                operandText = "[" + operandText + ", #" + String.valueOf(offset) + "]";
                break;
            case CALL_OPERAND:
                operandText = "[" + operandText + ", #" + String.valueOf(offset) + "]!";
                break;
            case DEFAULT_OPERAND:
                default:
                break;
        }
        operand.setPreBuildText(preBuildOperandText);
        operand.setOperandText(operandText);
        return operand;
    }
}
