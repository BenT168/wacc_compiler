package frontEnd.ErrorHandling;

import org.antlr.v4.runtime.ParserRuleContext;

public class IntOverflowException extends Exception {

	public IntOverflowException(String s, ParserRuleContext ctx) {
		super(s, ctx);
	}
	
}
