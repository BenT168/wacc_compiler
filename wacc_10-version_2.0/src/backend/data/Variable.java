package backend.data;

public class Variable {
    private String varString;

    Variable(String s) {
        this.varString = s;
    }

    @Override
    public String toString() {
        return varString;
    }

    @Override
    public boolean equals(Object that) {
        if (!(that instanceof Variable))
            return false;
        Variable obj = (Variable) that;
        return this.varString.equals(obj.toString());
    }

    @Override
    public int hashCode() {
        return this.varString.hashCode();
    }
}
