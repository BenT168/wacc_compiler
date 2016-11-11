package frontEnd.tree.Type;

import frontEnd.tree.Expr.Expr;


public class CompareBinOp extends BinaryOp {

    @Override
    public boolean check(Expr lhs, Expr rhs){
        if (lhs.getType().equals(BaseType.INT)) {
            return rhs.getType().equals(BaseType.INT);
        } else if (lhs.getType().equals(BaseType.CHAR)) {
            return rhs.getType().equals(BaseType.CHAR);
        }
        return false;
    }

    @Override
    public BaseType getType() {
        return BaseType.BOOL;
    }
}
