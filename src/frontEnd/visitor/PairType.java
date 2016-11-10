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

    @Override
    public String toString() {
        return "PAIR";
    }

    @Override
    public boolean equals(Type type) {
        if(!(type instanceof PairType)) {
            return false;
        }
        return type2.equals(type2);
    }
}
