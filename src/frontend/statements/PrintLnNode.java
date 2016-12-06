package frontend.statements;

import frontend.expressions.ExprNode;
import backend.Token;
import backend.Register;
import backend.TokSeq;
import backend.tokens.print.PrintLnToken;

public class PrintLnNode extends StatNode {

	private ExprNode expr;

	public PrintLnNode(ExprNode expr) {
		this.expr = expr;
	}

	@Override
	public TokSeq assemblyCodeGenerating(Register register) {
		TokSeq exprSeq = new TokSeq();
		if(!ex_ForLoopNode.isInLoopAssembler()) {
			exprSeq = expr.assemblyCodeGenerating(register);
		}
		TokSeq printSeq = expr.getType().printAssembly(register);
		Token println = new PrintLnToken();
		exprSeq.appendAll(printSeq);
		exprSeq.append(println);
		return exprSeq;
	}
}
