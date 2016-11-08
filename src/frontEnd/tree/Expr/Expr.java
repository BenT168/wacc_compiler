package frontEnd.tree.Expr;

import frontEnd.tree.Type.Type;
import frontEnd.tree.Type.Variable;

public class Expr extends Variable {

    public abstract boolean check(Type ext);

    public abstract Type getType();
    }

}
