package frontend.expressions;

import backend.Token;
import backend.Register;
import backend.TokenSequence;
import backend.tokens.move.MovImmToken;
import frontend.exception.SemanticErrorException;
import frontend.type.BaseType;
import org.antlr.v4.runtime.ParserRuleContext;
import symboltable.SymbolTable;

/* Represents the value of a BoolLeaf
 * Constructed with a StringLeaf (e.g "true")
 * Rule: 'true' | 'false'
 */

public class BoolLeaf extends ExprNode {

	private boolean value;
	private String stringValue;

	public BoolLeaf(String boolStringLeaf) {
		this.stringValue = boolStringLeaf;
	}

	@Override
	public boolean check(SymbolTable st, ParserRuleContext ctx) {
		//Getting line and column number for error message
		int line = ctx.start.getLine();
		int column = ctx.start.getCharPositionInLine();

		switch (this.stringValue) {
			case "true":
				this.value = true;
				return true;
			case "false":
				this.value = false;
				return true;
			default:
				throw new SemanticErrorException("BoolLeaf can only be 'true' or 'false'," +
						" string given " + stringValue);

		}
	}

	@Override
	public BaseType getType() {
		return BaseType.BOOL;
	}

	@Override
	public int weight() {
		return 1;
	}

	public boolean getValue() {
		return value;
	}

	@Override
	public TokenSequence toAssembly(Register r) {
		Token tok;
		if(value) {
			tok = new MovImmToken(r, "1");
		} else {
			tok = new MovImmToken(r, "0");
		}
		return new TokenSequence(tok);
	}


}
