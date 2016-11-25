package frontEnd.type;

public abstract class Type {
    public abstract Type reduce();

    @Override
    public abstract String toString();

    @Override
    public abstract boolean equals(Object o);

    @Override
    public abstract int hashCode();
}
