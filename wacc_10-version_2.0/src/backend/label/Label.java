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

    @Override
    public boolean equals(Object that) {
        if (!(that instanceof Label)) {
            return false;
        }
        Label obj = (Label) that;
        return this.hashCode() == obj.hashCode();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((labelType == null) ? 0 : labelType.hashCode());
        result = prime * result + labelSuffix.hashCode();
        return result;
    }
}
