package frontend.statements;

import frontend.expressions.ExprNode;
import backend.Token;
import backend.Register;
import backend.TokenSequence;
import backend.tokens.print.PrintLnToken;

/**
 * Class to represent println statements
 * Rule: 'println' expressions
 *
 */

public class PrintLnNode extends StatNode {

	private ExprNode expr;

	public PrintLnNode(ExprNode expr) {
		this.expr = expr;
	}

	@Override
	public TokenSequence toAssembly(Register register) {
		TokenSequence exprSeq = expr.toAssembly(register);
		TokenSequence printSeq = expr.getType().printAssembly(register);
		Token println = new PrintLnToken();
		exprSeq.appendAll(printSeq);
		exprSeq.append(println);
		return exprSeq;
	}
}
