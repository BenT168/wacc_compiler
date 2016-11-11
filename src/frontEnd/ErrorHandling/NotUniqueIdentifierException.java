package frontEnd.ErrorHandling;

import org.antlr.v4.runtime.ParserRuleContext;

/*
 * Used when an identifier is used which is already in use by another variable
 */

@SuppressWarnings("serial")
public class NotUniqueIdentifierException extends Exception {
	
	public NotUniqueIdentifierException(String exceptionMessage, ParserRuleContext ctx) {
		super(exceptionMessage, ctx);
	}

}
