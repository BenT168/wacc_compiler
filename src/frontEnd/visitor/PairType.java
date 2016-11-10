package frontEnd.visitor;

public class PairType extends Type {

    private Type type1, type2;

    public PairType(Type type1, Type type2) {
        this.type1 = type1;
        this.type2 = type2;
    }

    @Override
    public Type reduce() {
        // Not allowed nested pairs
        return this;
    }
}
