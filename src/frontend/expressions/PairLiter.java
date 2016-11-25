package frontend.expressions;

import frontend.type.BaseType;
import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import backend.Register;
import backend.TokenSequence;
import backend.tokens.load.LoadToken;

/**
 *	Rule: BaseType.NULL
 */
public class PairLiter extends ExprNode {

	@Override
	public boolean check(SymbolTable st, ParserRuleContext ctx) {
		return true;
	}

	@Override
	public BaseType getType() {
		return BaseType.NULL;
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
