package frontEnd.tree.Type;

import frontEnd.tree.Expr.Expr;

public class ArithBinOp extends BinaryOp{

    @Override
    public boolean check(Expr lhs, Expr rhs){
        return lhs.getType().equals(rhs.getType()) && rhs.getType().equals(Type.INT);
    }

    @Override
    public Type getType() {
        return Type.INT;
    }
}
