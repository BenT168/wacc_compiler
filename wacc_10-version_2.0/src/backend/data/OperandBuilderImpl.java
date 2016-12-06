package backend.data;

public class OperandBuilderImpl implements OperandBuilder {
    private Operand operand = new Operand();
    private String operandText;
    private OperandType operandType;

    public OperandBuilderImpl() {
        // Set operand type to default
        operand.setOperandType(OperandType.DEFAULT_OPERAND);
        this.operandType = OperandType.DEFAULT_OPERAND;
    }

    @Override
    public OperandBuilder insertType(OperandType type) {
        this.operandType = type;
        operand.setOperandType(type);
        return this;
    }

    @Override
    public OperandBuilder insertStringInput(String input) {
        operandText = input;
        return this;
    }

    @Override
    public Operand build() {
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
            case DEFAULT_OPERAND:
                default:
                break;
        }
        operand.setOperandText(operandText);
        return operand;
    }
}
