package frontEnd.tree.Type;

public class PaireElemType extends BaseType {

    private BaseType elementType;

    public PaireElemType(BaseType type) {
        this.elementType = type;
    }

    public BaseType getType() {
        return BaseType.NULL;
    }

    @Override
    public boolean isCompatible(BaseType type) {
        return true;
    }

    @Override
    public String toString() {
        return elementType.toString();
    }

}
