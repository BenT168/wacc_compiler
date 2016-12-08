package backend.data;

import backend.label.Label;

import java.util.ArrayList;
import java.util.List;

import static util.Utils.matchesVarSyntax;

public class InstructionBuilder implements InstructionBuilderInterface {
    private List<Operand> operands;
    private Instruction instruction = new Instruction();
    private OpCode opCode = null;

    public InstructionBuilder() {
        this.operands = new ArrayList<>();
    }

    @Override
    public InstructionBuilderInterface insertLabel(Label label) {
        instruction.setLabel(label);
        return this;
    }

    @Override
    public InstructionBuilderInterface insertDirective(Directive directive) {
        instruction.setDirective(directive);
        return this;
    }

    @Override
    public InstructionBuilderInterface insertOpCode(OpCode opCode) {
        this.opCode = opCode;
        instruction.setOpCode(opCode);
        return this;
    }

    @Override
    public InstructionBuilderInterface insertOperand(Operand operand) {
        this.operands.add(operand);
        instruction.setOperands(this.operands);
        return this;
    }

    @Override
    public InstructionBuilderInterface insertOperands(List<Operand> operands) {
        this.operands.addAll(operands);
        instruction.setOperands(this.operands);
        return this;
    }

    @Override
    public Instruction build() {
        // Store in 'Instruction' state, operand information needed for register allocation.
        for (int i = 0; i < operands.size(); i++) {
            Operand operand = operands.get(i);
            // Need to strip away excess operand indicators: '{', '[', '#', '=', etc.
            String pureOperandString = operand.getPreBuildText();
            if (!matchesVarSyntax(pureOperandString)) {
                break;
            }
            Variable var = new Variable(pureOperandString);
            if (operand.getOperandType().equals(OperandType.DEFAULT_OPERAND)) {
                // STR instruction has the first operand as a source operand.
                // There is only ever one destination operand, and in all other instructions
                // this is the first operand.
                if (i == 0 && !(this.opCode.equals(OpCode.STR))) {
                    instruction.addToKillSet(var);
                } else {
                    if (instruction.getKillSet().contains(var))
                        instruction.removeFromKillSet(var);
                    instruction.addToGenSet(var);
                }
            } else if (operand.getOperandType().equals(OperandType.MEM_ADDR_OPERAND)) {
                instruction.addToGenSet(var);
            }
        }
        return instruction;
    }
}
