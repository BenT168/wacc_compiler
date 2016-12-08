package backend.data;

import backend.label.Label;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Instruction {
    private static final int MAX_OPERANDS_SIZE = 3;

    private Label label = null;
    private Directive directive = null;
    private OpCode opCode = null;
    private List<Operand> operands = new ArrayList<>();
    // Variables may or may not be present in the instruction, as variables are there to
    // represent registers before actual registers are assigned.
    // Usage: Keeps track of variables for which this instruction generates liveness.
    private Set<Variable> genSet = new HashSet<>();
    // Usage: Keeps track of variables for which this instruction kills liveness.
    private Set<Variable> killSet = new HashSet<>();

    Instruction(){};

    public void setLabel(Label label) {
        this.label = label;
    }

    public void setDirective(Directive directive) {
        this.directive = directive;
    }

    public void setOpCode(OpCode opCode) {
        this.opCode = opCode;
    }

    public void setOperands(List<Operand> operands) {
        this.operands = operands;
    }

    void addToKillSet(Variable var) {
        killSet.add(var);
    }

    void addToGenSet(Variable var) {
        genSet.add(var);
    }

    public Set<Variable> getGenSet() {
        return genSet;
    }

    public Set<Variable> getKillSet() {
        return killSet;
    }

    public boolean sanityCheck() {
        if (directive != null && opCode != null) {
            System.err.println("Cannot have directive:" + directive.toString() + " and" +
                    "OpCode: " + opCode.toString() + " in same instruction");
            return false;
        }  else if (operands.size() > MAX_OPERANDS_SIZE) {
            System.err.println("Cannot have more than: " + MAX_OPERANDS_SIZE + " operands" +
                    "in instruction.\nAcutal: " + String.valueOf(operands.size()));
            return false;
        }
        // O(n^2) algorithm checking set intersection is empty.
        for (Variable v : genSet) {
            if (killSet.contains(v))
                return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        String directiveText;
        String labelText;
        if (directive != null && label != null) {
            directiveText = directive.toString();
            labelText = label.toString();
            builder.append(directiveText).append(" ").append(labelText);
        } else if (directive == null && label != null) {
            if (opCode == null) {
                labelText = label.toString() + ":";
                builder.append(labelText);
            } else {
                labelText = label.toString();
                builder.append("\t").append(opCode.toString()).append(" ").append(labelText);
            }
        } else if (opCode != null) {
            builder.append("\t").append(opCode.toString());
        } else if (directive != null) {
            directiveText = directive.toString();
            builder.append(directiveText);
        }

        if (operands.size() > 0)
            builder.append(" ").append(operands.get(0).toString());

        for (int i = 1; i < operands.size(); i++) {
            builder.append(", ").append(operands.get(i).toString());
        }
        return builder.toString();
    }

    public OpCode getOpCode() {
        return this.opCode;
    }

    public Label getLabeL() {
        return this.label;
    }

    public List<Operand> getOperands() {
        return operands;
    }

    public void removeFromKillSet(Variable var) {
        killSet.remove(var);
    }
}
