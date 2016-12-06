package backend.label;

import java.util.HashMap;
import java.util.Map;

public class NumberedLabelFactory implements LabelFactory {
    // If a label was created via the 'createNumberedLabel(LabelType)' method,
    // we keep track of how many times it has been created, using a map.
    private Map<LabelType, Integer> numberedLabelMap = new HashMap<>();

    public NumberedLabelFactory(){
    }

    @Override
    public Label createLabel(LabelType labelType) {
        int index = 0;
        if (numberedLabelMap.containsKey(labelType)) {
            index = numberedLabelMap.get(labelType);
        }
        String labelSuffix = String.format("%02d", index);
        numberedLabelMap.put(labelType, ++index);
        return new Label(labelType, labelSuffix);
    }
}
