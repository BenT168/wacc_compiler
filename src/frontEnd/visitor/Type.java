package frontEnd.visitor;

public abstract class Type {
    public abstract Type reduce();

    @Override
    public abstract String toString();

    @Override
    public abstract boolean equals(Object o);
}
