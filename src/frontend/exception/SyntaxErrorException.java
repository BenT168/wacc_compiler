package frontend.exception;

import org.antlr.v4.runtime.ParserRuleContext;

public class SyntaxErrorException extends Exception {

	public SyntaxErrorException(String string, ParserRuleContext ctx) {
		super(string, ctx);
	}

}
