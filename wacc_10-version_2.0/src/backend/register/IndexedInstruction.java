package backend.register;

import backend.data.Instruction;

/**
 * Created by MarkAduol on 04-Dec-16.
 */
public class IndexedInstruction {
    private int index;
    private Instruction instruction;

    public IndexedInstruction(int index, Instruction instruction) {
        this.index = index;
        this.instruction = instruction;
    }

    public int getIndex() {
        return index;
    }

}
