package frontend.stat;

import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import frontend.expr.ExprNode;
import frontend.type.WACCType;
import WACCExceptions.InvalidTypeException;
import backend.InstrToken;
import backend.Register;
import backend.TokenSequence;
import backend.tokens.branch.BranchLinkToken;
import backend.tokens.move.MovRegToken;

/**
 * Class to represent exit statements for exiting a program
 * Rule: 'exit' expr
 *
 */

public class ExitStat extends StatNode {
	
	private ExprNode exitVal;
	
	public ExitStat(ExprNode exitVal) {
		this.exitVal = exitVal;
	}
	
	@Override
	public boolean check( SymbolTable st, ParserRuleContext ctx ) {
		if (exitVal.getType() == WACCType.INT) {
			return true;
		}
		new InvalidTypeException("Exit statements must have an int as the argument", ctx);
		return false;
	}
	
	@Override
	public TokenSequence toAssembly(Register dest) {
		TokenSequence ldr = exitVal.toAssembly(dest);
		InstrToken mov2 = new MovRegToken(Register.R0, dest);
		InstrToken branch = new BranchLinkToken("exit");
		
		ldr.appendAll(new TokenSequence(mov2, branch));
		return ldr;
	}

}
