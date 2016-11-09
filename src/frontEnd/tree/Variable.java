package frontEnd.tree;

import frontEnd.tree.Type.BaseType;

public class Variable extends Identifier {

    private BaseType type;
    private String expr;


    //Unitialised Variable
    public Variable(BaseType type, String expr) {
        this.type = type;
        this.expr = expr;
    }

    public BaseType getType() {
        return type;
    }

    public String getExpr() {
        return expr;
    }
}
