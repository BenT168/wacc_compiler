package frontend.exception;

import org.antlr.v4.runtime.ParserRuleContext;

/*
 *  Used when types in variable assignments etc. are not compatible
 */

@SuppressWarnings("serial")
public class SemanticErrorException extends Exception {
	
	public SemanticErrorException(String exceptionMessage, ParserRuleContext ctx) {
		super(exceptionMessage, ctx);
	}

	public SemanticErrorException(String message) {
		super(message);
	}

}
