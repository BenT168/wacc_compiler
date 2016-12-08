package backend.data;

public final class Operand {

    private String operandText;
    private OperandType operandType;
    private int shiftValue;
    private String preBuildText;
    private int offset;

    Operand() {
    }

    public void setOperandText(String operandText) {
        this.operandText = operandText;
    }

    public void setOperandType(OperandType operandType) {
        this.operandType = operandType;
    }

    public OperandType getOperandType() {
        return operandType;
    }

    @Override
    public String toString() {
        return operandText;
    }

    public void setShiftValue(int shiftValue) {
        this.shiftValue = shiftValue;
    }

    public void setPreBuildText(String preBuildText) {
        this.preBuildText = preBuildText;
    }

    public String getPreBuildText() {
        return preBuildText;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}
