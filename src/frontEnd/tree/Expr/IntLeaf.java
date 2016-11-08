package frontEnd.tree.Expr;

import frontEnd.tree.Type.Type;

public class IntLeaf extends Expr {

    private int value;

    public IntLeaf(String val){
        this.value = val;
    }

    @Override
    public boolean check(Type ext){
        return ext.isInt();
    }

}