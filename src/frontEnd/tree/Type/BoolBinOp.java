package frontEnd.tree.Type;

import frontEnd.tree.Expr.Expr;


public class BoolBinOp extends BinaryOp {

    @Override
    public boolean check(Expr lhs, Expr rhs){
        return lhs.getType().equals(BaseType.BOOL) && rhs.getType().equals(BaseType.BOOL);
    }

    @Override
    public BaseType getType() {
        return BaseType.BOOL;
    }
}
