package frontEnd.visitor;

public abstract class Type {
    public abstract Type reduce();

    @Override
    public abstract String toString();

    public abstract boolean equals(Type type);

}
