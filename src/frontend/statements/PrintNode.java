package frontend.statements;

import frontend.expressions.ExprNode;
import backend.Register;
import backend.TokenSequence;


/**
 * Class to represent print statements
 * Rule: 'print' expressions
 *
 */

public class PrintNode extends StatNode {

	private ExprNode expr;

	public PrintNode(ExprNode expr) {
		this.expr = expr;
	}

	@Override
	public TokenSequence toAssembly(Register register) {
		TokenSequence exprSeq = expr.toAssembly(register);
		TokenSequence printSeq = expr.getType().printAssembly(register);
		return exprSeq.appendAll(printSeq);
	}
}
