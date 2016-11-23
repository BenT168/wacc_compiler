package frontend.expr;

import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import frontend.type.WACCType;
import backend.Register;
import backend.TokenSequence;
import backend.tokens.load.LoadToken;

/**
 *	Rule: WACCType.NULL
 */
public class PairLiterNode extends ExprNode {

	@Override
	public boolean check(SymbolTable st, ParserRuleContext ctx) {
		return true;
	}

	@Override
	public WACCType getType() {
		return WACCType.NULL;
	}

	@Override
	public int weight() {
		return 1;
	}

	@Override
	public TokenSequence toAssembly(Register register) {
		return new TokenSequence(
				new LoadToken(register, "0"));
	}
	

}
