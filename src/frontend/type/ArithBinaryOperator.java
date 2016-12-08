package frontend.type;

import frontend.expressions.ExprNode;

/**
 * Abstract class representing Arithmetic Binary Operator
 *
 */


public abstract class ArithBinaryOperator extends BinaryOperators {

	@Override
	public boolean check(ExprNode lhs, ExprNode rhs) {
		return lhs.getType() == rhs.getType() && lhs.getType() == BaseType.INT;
	}

	@Override
	public BaseType getType() {
		return BaseType.INT;
	}

}
