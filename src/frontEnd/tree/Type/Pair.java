package frontEnd.tree.Type;

public class Pair extends Type {

    private Type fst;
    private Type snd;
    private static Pair NULL_PAIR = new Pair(Type.NULL, Type.NULL);

    public Pair(Type fst, Type snd) {
        this.fst = (fst instanceof Pair) ? NULL_PAIR : fst;
        this.snd = (snd instanceof Pair) ? NULL_PAIR : snd;
    }

    public Type getFst() {
        return fst;
    }

    public Type getSnd() {
        return snd;
    }

    @Override
    public boolean isCompatible(Type type) {
        if(type == Type.NULL) {
            return true;
        }

        if (!(type instanceof Pair)){
            return false;
        }

        Pair other = (Pair) type;

        return checkType(this.getFst(),other.getFst()) &&
                checkType(this.getSnd(), other.getSnd());
    }

    private boolean checkType(Type fst, Type snd) {
        boolean compatible = fst == Type.NULL|| fst.isCompatible((snd));
        if(!compatible){
            new IncompatibleTypeException("The types " + fst.toString() + " and " + snd.toString() + " are not compatible.");
        }
    }

    @Override
    public String toString() {
        return "pair(" + fst.toString() + ", " + snd.toString() + ")";
    }
}
