package frontend.type;

import frontend.expressions.ExprNode;


public abstract class DoubleBinaryOperator extends BinaryOperators {

    @Override
    public boolean check(ExprNode lhs, ExprNode rhs) {
        return lhs.getType() == BaseType.INT && rhs.getType() == BaseType.INT;
    }

    @Override
    public BaseType getType() {
        return BaseType.INT;
    }
}
