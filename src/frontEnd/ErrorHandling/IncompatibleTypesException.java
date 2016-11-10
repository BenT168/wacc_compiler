package frontEnd.ErrorHandling;

import org.antlr.v4.runtime.ParserRuleContext;

public class IncompatibleTypesException extends Exception {
	
	public IncompatibleTypesException(String exceptionMessage, ParserRuleContext ctx) {
		super(exceptionMessage, ctx);
	}

	public IncompatibleTypesException(String message) {
		super(message);
	}

}
