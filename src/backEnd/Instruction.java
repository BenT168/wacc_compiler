package backEnd;

import java.util.ArrayList;
import java.util.List;

public class Instruction {

    private OpCode opCode;
    private String dstOperand = null;
    private String strOperand1 = null;
    private String strOperand2 = null;
    private int intOperand1;
    private boolean intOper1 = false;
    private int intOperand2;
    private boolean intOper2 = false;
    private Label label;
    private String tag;
    private List<String> callOperands = new ArrayList<>();
    private List<String> allOperands = new ArrayList<>(); // Should not hold operands in 'callOperands'

    public static final String LTORG_DIRECTIVE = "\t \t.ltorg";
    public static final String TEXT_DIRECTIVE = "\t.text";
    public static final String DATA_DIRECTIVE = "\t.data";
    public static final String GLOBAL_MAIN_DIRECTIVE = ".global main";

    public Instruction(Label label) {
        this.label = label;
    }

    public Instruction(String tag) {
        this.tag = tag;
        this.intOper1 = false;
        this.intOper2 = false;
    }

    public Instruction(OpCode opCode, String operand) {
        this.opCode = opCode;
        this.strOperand1 = operand;
    }

    public Instruction(OpCode opCode, int operand) {
        this.opCode = opCode;
        this.intOperand1 = operand;
        this.intOper1 = true;
    }

    public Instruction(OpCode opCode, String dstOperand, String strOperand1, String strOperand2) {
        this.opCode = opCode;
        this.dstOperand = dstOperand;
        this.strOperand1 = strOperand1;
        this.strOperand2 = strOperand2;
    }

    public Instruction(OpCode opCode, String dstOperand, String strOperand1, int intOperand1) {
        this.opCode = opCode;
        this.dstOperand = dstOperand;
        this.strOperand1 = strOperand1;
        this.intOperand1 = intOperand1;
        this.intOper1 = true;
    }

    public Instruction(OpCode opCode, String dstOperand, int intOperand1, int intOperand2) {
        this.opCode = opCode;
        this.dstOperand = dstOperand;
        this.intOperand1 = intOperand1;
        this.intOperand2 = intOperand2;
        this.intOper1 = true;
        this.intOper2 = true;
    }

    public Instruction(OpCode opCode, String dstOperand, int intOperand1) {
        this.opCode = opCode;
        this.dstOperand = dstOperand;
        this.intOperand1 = intOperand1;
        this.intOper1 = true;
    }

    public Instruction(OpCode opCode, String dstOperand, String strOperand1) {
        this.opCode = opCode;
        this.dstOperand = dstOperand;
        this.strOperand1 = strOperand1;
    }

    public Instruction(OpCode opCode, String dstOperand, List<String> operands) {
        this.opCode = opCode;
        this.dstOperand = dstOperand;
        this.callOperands = operands;
    }

    public OpCode getOpCode() {
        return opCode;
    }

    public String getDstOperand() {
        return dstOperand;
    }

    public String getStrOperand1() {
        return strOperand1;
    }

    public String getStrOperand2() {
        return strOperand2;
    }

    public int getIntOperand1() {
        return intOperand1;
    }

    public boolean isIntOper1() {
        return intOper1;
    }

    public int getIntOperand2() {
        return intOperand2;
    }

    public boolean isIntOper2() {
        return intOper2;
    }

    public Label getLabel() {
        return label;
    }

    public String getTag() {
        return tag;
    }

    public List<String> getCallOperands() {
        return callOperands;
    }

    public List<String> getAllOperands() {
        // Does not hold 'callOperands'
        allOperands.add(dstOperand);
        allOperands.add(strOperand1);
        allOperands.add(strOperand2);
        if (intOper1)
            allOperands.add(Integer.valueOf(intOperand1).toString());
        if (intOper2)
            allOperands.add(Integer.valueOf(intOperand2).toString());
        return allOperands;
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
