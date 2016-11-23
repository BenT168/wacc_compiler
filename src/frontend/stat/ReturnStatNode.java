package frontend.stat;

import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import frontend.expr.ExprNode;
import frontend.type.WACCType;
import WACCExceptions.IncompatibleTypesException;
import backend.Register;
import backend.StackAllocator;
import backend.TokenSequence;
import backend.tokens.move.MovRegToken;
import backend.tokens.general.PopToken;

/**
 * Class to represent return statements for functions
 * Rule: 'return' expr
 */

public class ReturnStatNode extends StatNode {
	private ExprNode expr;

	public ReturnStatNode(ExprNode expr) {
		this.expr = expr;
	}

	@Override
	public boolean check(SymbolTable st, ParserRuleContext ctx) {
		WACCType returnType = expr.getType();
		if( !st.checkType(returnType) ) {
			new IncompatibleTypesException("A return of type " + returnType.toString() + " was not expected.", ctx);
			return false;
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
