package frontend.type;

import frontend.expressions.ExprNode;

public abstract class EqualBinaryOperator extends BinaryOperators {

	@Override
	public boolean check(ExprNode lhs, ExprNode rhs) {
		return true;
	}

	@Override
	public BaseType getType() {
		return BaseType.BOOL;
	}

}
