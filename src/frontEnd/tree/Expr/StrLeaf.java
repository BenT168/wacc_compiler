package frontEnd.tree.Expr;

import frontEnd.tree.Type.Type;

public class StrLeaf extends Expr {

    private String str;

    public StrLeaf(String str) {
        this.str = str;
    }

    @Override
    public boolean check(Type ext){
        return ext.isString();
    }

}