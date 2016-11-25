package frontend.statements;

import frontend.exception.SemanticErrorException;
import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import frontend.expressions.ExprNode;
import frontend.type.BaseType;
import backend.Register;
import backend.StackAllocator;
import backend.TokenSequence;
import backend.tokens.move.MovRegToken;
import backend.tokens.general.PopToken;

/**
 * Class to represent return statements for functions
 * Rule: 'return' expressions
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
	public TokenSequence toAssembly(Register r) {
		TokenSequence seq = new TokenSequence(expr.toAssembly(r));
		seq.append(new MovRegToken(Register.R0, r));

		TokenSequence stackTerminationSeq = StackAllocator.stackAllocator.getTermination();
		stackTerminationSeq.append(new PopToken(Register.pc));

		return seq.appendAll(stackTerminationSeq);
	}

}