package frontEnd.ErrorHandling;

import org.antlr.v4.runtime.ParserRuleContext;

public class UndeclaredIdentifierException extends Exception {

	public UndeclaredIdentifierException(String s, ParserRuleContext ctx) {
		super(s, ctx);
	}

}
