package frontend.stat;

import frontend.expr.ExprNode;
import backend.InstrToken;
import backend.Register;
import backend.TokenSequence;
import backend.tokens.print.PrintLnToken;

/**
 * Class to represent println statements
 * Rule: 'println' expr
 *
 */

public class PrintLnStat extends StatNode {

	private ExprNode expr;
	
	public PrintLnStat(ExprNode expr) {
		this.expr = expr;
	}
	
	@Override
	public TokenSequence toAssembly(Register register) {
		TokenSequence exprSeq = expr.toAssembly(register);
		TokenSequence printSeq = expr.getType().printAssembly(register);
		InstrToken println = new PrintLnToken();
		exprSeq.appendAll(printSeq);
		exprSeq.append(println);
		return exprSeq;
	}
}
