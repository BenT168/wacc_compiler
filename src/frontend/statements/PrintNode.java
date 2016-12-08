package frontend.statements;

import backend.TokSeq;
import frontend.expressions.ExprNode;
import backend.Register;

/**
 * Class representing print statements
 * Rule: PRINT expressions
 */

public class PrintNode extends StatNode {

	private ExprNode expr;

	public PrintNode(ExprNode expr) {
		this.expr = expr;
	}

	@Override
	public TokSeq assemblyCodeGenerating(Register register) {
		TokSeq exprSeq = expr.assemblyCodeGenerating(register);
		TokSeq printSeq = expr.getType().printAssembly(register);
		return exprSeq.appendAll(printSeq);
	}
}
