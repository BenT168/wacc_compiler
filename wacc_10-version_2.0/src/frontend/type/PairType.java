package frontend.type;

import java.util.Objects;

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

    /* Returns type of first element of the pair */
    public Type getType1() {
        return type1;
    }

    /* Returns type of second element of the pair */
    public Type getType2() {
        return type2;
    }

    @Override
    public String toString() {
        return "PAIR " + type1.toString() + " " + type2.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof PairType))
            return false;
        if (obj == this)
            return true;
        PairType rhs = (PairType) obj;
        return this.type1.equals(rhs.type1) && this.type2.equals(rhs.type2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.type1, this.type2);

    }
}
