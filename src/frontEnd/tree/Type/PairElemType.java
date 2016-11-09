package frontEnd.tree.Type;

public class PairElemType extends BaseType {

    private BaseType elementType;

    public PairElemType(BaseType type) {
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
