package backend;

import backend.data.Instruction;
import backend.label.Label;

import java.util.*;

/**
 * Information is added to this class during the code generation process.
 */
public class CodegenInfo {
    private Set<Label> predefinedLabelsReferenced;
    private Map<Label, List<Instruction>> dataSegment;

    public CodegenInfo() {
        this.predefinedLabelsReferenced = new HashSet<>();
        this.dataSegment = new HashMap<>();
    }

    public void addPredefLabelRef(Label label) {
        predefinedLabelsReferenced.add(label);
    }

    public Set<Label> getPredefinedLabelsReferenced() {
        return predefinedLabelsReferenced;
    }

    public void addToDataSegment(Label label, List<Instruction> instruction) {
        dataSegment.put(label, instruction);
    }

    public Map<Label, List<Instruction>> getDataSegment() {
        return dataSegment;
    }
}
