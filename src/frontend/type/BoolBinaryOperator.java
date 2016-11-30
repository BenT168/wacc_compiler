package frontend.type;

import frontend.expressions.ExprNode;

public abstract class BoolBinaryOperator extends BinaryOperators {

	@Override
	public boolean check(ExprNode lhs, ExprNode rhs) {
		return lhs.getType() == BaseType.BOOL && rhs.getType() ==  BaseType.BOOL;
	}

	@Override
	public BaseType getType() {
		return BaseType.BOOL;
	}

}
