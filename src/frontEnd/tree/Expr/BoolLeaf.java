package frontEnd.tree.Expr;

import frontEnd.tree.Type.Type;

public class BoolLeaf extends Expr {

    private String strVal;

    public BoolLeaf(String boolStr) {
        this.strVal = boolStr;
    }

    @Override
    public boolean check(Type ext){
        return ext.isBool();
    }

    @Override
    public Type getType(){
        return type;
    }

}