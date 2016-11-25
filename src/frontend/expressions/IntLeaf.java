package frontend.expressions;

import backend.Register;
import backend.TokenSequence;
import backend.tokens.load.LoadToken;
import frontend.exception.SyntaxErrorException;
import frontend.type.BaseType;
import org.antlr.v4.runtime.ParserRuleContext;
import symboltable.SymbolTable;

/* Represents the value of an Integer
 * Constructed with a StringLeaf (e.g "42")
 * Rule: int-sign? digit+
 */

public class IntLeaf extends ExprNode {

	private String value;

	public IntLeaf(String val) {
		this.value = trim(val);
	}

	public static String trim(String val) {
		String res = 	val.charAt(0) == '0'?
						val.replaceFirst("0*", "")
						: val;

		return (res.isEmpty()? "0" : res);
	}

	@Override
	public boolean check( SymbolTable st, ParserRuleContext ctx ) {
		long integer = Long.valueOf(value);
		if (integer < - (Math.pow(2, 31)) || integer > (Math.pow(2, 31) + 1)) {
			throw new SyntaxErrorException("The absolute value, " + value + " is too large", ctx);
		}
		return true;
	}

	@Override
	public String toString() {
		return value;
	}

	@Override
	public BaseType getType() {
		return BaseType.INT;
	}

	@Override
	public TokenSequence toAssembly(Register r) {
		return new TokenSequence( new LoadToken(r, value) );
	}

	@Override
	public int weight() {
		return 1;
	}


}