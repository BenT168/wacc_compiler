package frontend.type;

import frontend.expressions.ExprNode;

/* Contains specific behavior for Boolean Binary Expressions
 * See WACCBinOp.java for more
 * Rule: expressions binary-oper expressions
 * Where binary-oper is '&&' or '||'
 */

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
