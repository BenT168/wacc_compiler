package frontend.type;

import frontend.expressions.ExprNode;


public abstract class DoubleBinaryOperator extends BinaryOperators {

    /*
    Method that checks that both sides are of type int
     */
    @Override
    public boolean check(ExprNode lhs, ExprNode rhs) {
        return lhs.getType() == BaseType.INT && rhs.getType() == BaseType.INT;
    }

    /*
    Method returns an int
     */
    @Override
    public BaseType getType() {
        return BaseType.INT;
    }
}
