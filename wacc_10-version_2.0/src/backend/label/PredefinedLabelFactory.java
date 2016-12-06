package backend.label;

public class PredefinedLabelFactory implements LabelFactory {

    public PredefinedLabelFactory(){};

    @Override
    public Label createLabel(LabelType labelType) {
        return new Label(labelType);
    }
}
