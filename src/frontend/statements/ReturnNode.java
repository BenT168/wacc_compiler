package frontend.statements;

import backend.TokSeq;
import frontend.exception.SemanticErrorException;
import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import frontend.expressions.ExprNode;
import frontend.type.BaseType;
import backend.Register;
import backend.StackAlloc;
import backend.tokens.move.MovRegToken;
import backend.tokens.general.PopToken;

/**
 * Class representing return statements
 * Rule: RETURN expression
 */


public class ReturnNode extends StatNode {
	private ExprNode expr;

	public ReturnNode(ExprNode expr) {
		this.expr = expr;
	}

	@Override
	public boolean check(SymbolTable st, ParserRuleContext ctx) {
		BaseType returnType = expr.getType();
		if( !st.checkType(returnType) ) {
			throw new SemanticErrorException("A return of type " + returnType.toString() + " was not expected.", ctx);
		}
		return true;
	}

	@Override
	public TokSeq assemblyCodeGenerating(Register r) {
		TokSeq seq = new TokSeq(expr.assemblyCodeGenerating(r));
		seq.append(new MovRegToken(Register.R0, r));

		TokSeq stackTerminationSeq = StackAlloc.STACK_ALLOC.getTerminated();
		stackTerminationSeq.append(new PopToken(Register.pc));

		return seq.appendAll(stackTerminationSeq);
	}

}
