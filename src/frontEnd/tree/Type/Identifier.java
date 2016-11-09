package frontEnd.tree.Type;

import frontEnd.tree.Type.Type;

public abstract class Identifier {

    protected String name;

    public String getName() {
        return name;
    }

    public abstract void check();

   /* public Character[] getFormals() {
        Character[] formals;
        return formals;
    }

    */

   // public abstract void setFormals(Object param, int i);
}
