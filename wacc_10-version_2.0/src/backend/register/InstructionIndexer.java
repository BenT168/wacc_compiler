package backend.register;

import backend.data.Instruction;

/**
 * Created by MarkAduol on 04-Dec-16.
 */
public class InstructionIndexer {
    private int instructionCount;

    public InstructionIndexer() {
        this.instructionCount = 0;
    }

    public IndexedInstruction createIndexedInstuction(Instruction instruction) {
        IndexedInstruction i = new IndexedInstruction(instructionCount, instruction);
        instructionCount++;
        return i;
    }

    public void reset() {
        this.instructionCount = 0;
    }
}
