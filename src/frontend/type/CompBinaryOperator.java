package frontend.type;

import frontend.expressions.ExprNode;

/* Contains specific behavior for Comparative Binary Expressions
 * See WACCBinOp.java for more
 * Rule: expressions binary-oper expressions
 * Where binary-oper is '>' | '>=' | '<' | '<='
 */

public abstract class CompBinaryOperator extends BinaryOperators {

	@Override
	public boolean check(ExprNode lhs, ExprNode rhs) {
		if (lhs.getType() == BaseType.INT)
			return rhs.getType() == BaseType.INT;
		else if (lhs.getType() == BaseType.CHAR)
			return rhs.getType() == BaseType.CHAR;
		else
			return false;
	}

	@Override
	public BaseType getType() {
		return BaseType.BOOL;
	}

}
