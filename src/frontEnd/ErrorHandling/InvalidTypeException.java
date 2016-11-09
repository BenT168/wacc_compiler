package frontEnd.ErrorHandling;

import org.antlr.v4.runtime.ParserRuleContext;

public class InvalidTypeException extends Exception{
    public InvalidTypeException(String s, ParserRuleContext ctx) {
	super(s, ctx);
    }

    public InvalidTypeException(String message) {
	super(message);
    }
}
