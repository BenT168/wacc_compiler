package frontend.expressions;

import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import frontend.type.BaseType;
import backend.Register;
import backend.TokenSequence;
import backend.tokens.load.LoadStringToken;

/* Represents the value of a StringLeaf
 * Constructed with a StringLeaf (e.g "hello")
 * Rule: '"' character* '"'
 */

public class StringLeaf extends ExprNode {

	private java.lang.String text;

	public StringLeaf(java.lang.String text) {
		this.text = text;
	}

	@Override
	public boolean check(SymbolTable st, ParserRuleContext ctx) {
		return true;
	}

	@Override
	public BaseType getType() {
		return BaseType.STRING;
	}

	@Override
	public int weight() {
		return 1;
	}

	@Override
	public java.lang.String toString() {
		return text;
	}

	public TokenSequence toAssembly(Register r) {
		return new TokenSequence(new LoadStringToken(r, text));
	}


}
