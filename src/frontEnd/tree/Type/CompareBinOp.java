package frontEnd.tree.Type;

import frontEnd.tree.Expr.Expr;


public class CompareBinOp extends BinaryOp {

    @Override
    public boolean check(Expr lhs, Expr rhs){
        if (lhs.getType().equals(Type.INT)) {
            return rhs.getType().equals(Type.INT);
        } else if (lhs.getType().equals(Type.CHAR)) {
            return rhs.getType().equals(Type.CHAR);
        }
        return false;
    }

    @Override
    public Type getType() {
        return Type.BOOL;
    }
}
