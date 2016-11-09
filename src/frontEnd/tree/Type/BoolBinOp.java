package frontEnd.tree.Type;

import frontEnd.tree.Expr.Expr;


public class BoolBinOp extends BinaryOp {

    @Override
    public boolean check(Expr lhs, Expr rhs){
        return lhs.getType().equals(Type.BOOL) && rhs.getType().equals(Type.BOOL);
    }

    @Override
    public Type getType() {
        return Type.BOOL;
    }
}
