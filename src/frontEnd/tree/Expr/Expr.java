package frontEnd.tree.Expr;

import frontEnd.tree.AST.AST;
import frontEnd.tree.Type.Type;

public class Expr extends AST {

    private Type type;

    public abstract boolean check(Type ext);

    public abstract Type getType();
    }

}


