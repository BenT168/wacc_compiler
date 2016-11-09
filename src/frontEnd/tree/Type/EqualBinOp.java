package frontEnd.tree.Type;

import frontEnd.tree.Expr.Expr;

public class EqualBinOp extends BinaryOp {

    @Override
    public boolean check(Expr lhs, Expr rhs){
        return true;
    }

    @Override
    public Type getType() {
        return Type.BOOL;
    }
}
