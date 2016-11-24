package frontend.expr;

import backend.InstrToken;
import backend.Register;
import backend.TokenSequence;
import backend.tokens.move.MovImmToken;
import frontend.exception.ThrowException;
import frontend.type.WACCType;
import org.antlr.v4.runtime.ParserRuleContext;
import symboltable.SymbolTable;

/* Represents the value of a Bool
 * Constructed with a String (e.g "true")
 * Rule: 'true' | 'false'
 */

public class BoolLeaf extends ExprNode {
	
	private boolean value;
	private String stringValue;
	
	public BoolLeaf(String boolString) {
		this.stringValue = boolString;
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
				//Throw Semantic Error
				String msg = "Wrong argument types for BoolLeaf.\n" +
						"Expected types : BOOL\n"+
						"Actual type : STRING, " + stringValue;
				ThrowException.callSemanticException(line, column, msg);
				return false;
		}
	}

	@Override
	public WACCType getType() {
		return WACCType.BOOL;
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
		InstrToken tok;
		if(value) {
			tok = new MovImmToken(r, "1");
		} else {
			tok = new MovImmToken(r, "0");
		}
		return new TokenSequence(tok);
	}
	

}
