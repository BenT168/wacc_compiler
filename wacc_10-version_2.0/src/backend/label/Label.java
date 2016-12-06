package backend.label;

public final class Label {

    private LabelType labelType;
    private String labelSuffix;

    // Package-private to avoid classes outside package from directly calling
    // this constructor. They should use the factory methods in the 'LabelFactory'
    // class instead.
    Label(LabelType labelType) {
        this.labelType = labelType;
        this.labelSuffix = "";
    }

    Label(LabelType labelType, String labelSuffix) {
        this.labelType = labelType;
        this.labelSuffix = labelSuffix;
    }

    public LabelType getLabelType() {
        return labelType;
    }

    @Override
    public String toString() {
        return this.labelType.toString() + labelSuffix;
    }
}
