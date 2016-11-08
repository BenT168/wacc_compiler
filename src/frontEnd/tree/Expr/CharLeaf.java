package frontEnd.tree.Expr;

import frontEnd.tree.Type.Type;

public class CharLeaf extends Expr {

    private String strChar;

    public BoolLeaf(String strChar) {
        this.strChar = strChar;
    }

    @Override
    public boolean check(Type ext){
        return ext.isChar();
    }

}
