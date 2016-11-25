package frontend.type;

import frontend.expressions.ExprNode;

/* Contains specific behavior for Equals and Not Equals Binary Expressions
 * See WACCBinOp.java for more
 * Rule: expressions binary-oper expressions
 * Where binary-oper is '!=' | '=='
 */

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
