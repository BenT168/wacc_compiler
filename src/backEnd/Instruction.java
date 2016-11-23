package backEnd;

import java.util.List;

public class Instruction {

    private OpCode opCode;
    private String dstOperand;
    private String strOperand1;
    private String strOperand2;
    private int intOperand1;
    private boolean intOper1;
    private int intOperand2;
    private boolean intOper2;
    private Label label;
    private List<String> operands;

    public Instruction(Label label) {
        this.label = label;
        this.intOper1 = false;
        this.intOper2 = false;
    }

    public Instruction(OpCode opCode, String operand) {
        this.opCode = opCode;
        this.strOperand1 = operand;
        this.intOper1 = false;
        this.intOper2 = false;
    }

    public Instruction(OpCode opCode, String dstOperand, String strOperand1, String strOperand2) {
        this.opCode = opCode;
        this.dstOperand = dstOperand;
        this.strOperand1 = strOperand1;
        this.strOperand2 = strOperand2;
        this.intOper1 = false;
        this.intOper2 = false;
    }

    public Instruction(OpCode opCode, String dstOperand, int intOperand1, int intOperand2) {
        this.opCode = opCode;
        this.dstOperand = dstOperand;
        this.intOperand1 = intOperand1;
        this.intOperand2 = intOperand2;
        this.intOper1 = true;
        this.intOper2 = true;
    }

    public Instruction(String dstOperand, int intOperand1) {
        this.dstOperand = dstOperand;
        this.intOperand1 = intOperand1;
        this.intOper1 = true;
        this.intOper2 = false;
    }

    public Instruction(String dstOperand, String strOperand1) {
        this.dstOperand = dstOperand;
        this.strOperand1 = strOperand1;
        this.intOper1 = false;
        this.intOper2 = false;
    }

    public Instruction(OpCode opCode, String dstOperand, int intOperand1) {
        this.opCode = opCode;
        this.dstOperand = dstOperand;
        this.intOperand1 = intOperand1;
        this.intOper1 = true;
        this.intOper2 = false;
    }

    public Instruction(OpCode opCode, String dstOperand, String strOperand1) {
        this.opCode = opCode;
        this.dstOperand = dstOperand;
        this.strOperand1 = strOperand1;
        this.intOper1 = false;
        this.intOper2 = false;
    }

    public Instruction(OpCode opCode, String dstOperand, List<String> operands) {
        this.opCode = opCode;
        this.dstOperand = dstOperand;
        this.operands = operands;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (opCode != null) sb.append(opCode + " ");
        if (dstOperand != null) sb.append(dstOperand + " ");
        if (strOperand1 != null) sb.append(strOperand1 + " ");
        if (strOperand2 != null) sb.append(strOperand2 + " ");
        if (intOper1) sb.append(Integer.toString(intOperand1) + " ");
        if (intOper2) sb.append(Integer.toString(intOperand2) + " ");
        return sb.toString();
    }
}
