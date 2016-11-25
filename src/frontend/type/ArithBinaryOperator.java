package frontend.type;

import frontend.expressions.ExprNode;

/* Contains specific behavior for Arithmetic Binary Expressions
 * See WACCBinOp.java for more
 * Rule: expressions binary-oper expressions
 * Where binary-oper is '+' | '-' | '*' | '/' | '%'
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
