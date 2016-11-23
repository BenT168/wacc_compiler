package backEnd;

public class Instruction {

    private OpCode opCode;
    private String dstOperand;
    private String strOperand1;
    private String strOperand2;
    private int intOperand1;
    private int intOperand2;
    private Label label;

    public Instruction(Label label) {
        this.label = label;
    }

    public Instruction(OpCode opCode, String dstOperand, String strOperand1, String strOperand2) {
        this.opCode = opCode;
        this.dstOperand = dstOperand;
        this.strOperand1 = strOperand1;
        this.strOperand2 = strOperand2;
    }

    public Instruction(OpCode opCode, String dstOperand, int intOperand1, int intOperand2) {
        this.opCode = opCode;
        this.dstOperand = dstOperand;
        this.intOperand1 = intOperand1;
        this.intOperand2 = intOperand2;
    }

    public Instruction(String dstOperand, int intOperand1) {
        this.dstOperand = dstOperand;
        this.intOperand1 = intOperand1;
    }

    public Instruction(String dstOperand, String strOperand1) {
        this.dstOperand = dstOperand;
        this.strOperand1 = strOperand1;
    }

    public Instruction(OpCode opCode, String dstOperand, int intOperand1) {
        this.opCode = opCode;
        this.dstOperand = dstOperand;
        this.intOperand1 = intOperand1;
    }

    public Instruction(OpCode opCode, String dstOperand, String strOperand1) {
        this.opCode = opCode;
        this.dstOperand = dstOperand;
        this.strOperand1 = strOperand1;
    }
}
