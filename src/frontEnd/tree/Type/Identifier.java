package frontEnd.tree.Type;

import frontEnd.tree.Type.Type;

public abstract class Identifier {


    public Type getType() {
        return type;
    }

    public Character[] getFormals() {
        Character[] formals;
        return formals;
    }

    public abstract void check();

    public abstract void setFormals(Object param, int i);
}
