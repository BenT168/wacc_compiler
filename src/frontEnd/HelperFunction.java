package frontEnd;

import antlr.WACCParser;
import frontEnd.exception.SyntaxException;

public class HelperFunction {

    private boolean inFunction;
    private boolean isMultipleStat;

    public HelperFunction(boolean inFunction, boolean isMultipleStat) {
        this.inFunction = inFunction;
        this.isMultipleStat = isMultipleStat;
    }

    public void checksIfFunctionReturns(WACCParser.StatContext ctx) {
        if(inFunction && !isMultipleStat) {
            if(!ctx.getText().matches("return(.*)")) {
                throw new SyntaxException("The Function is missing a return statement.");
            }
        }
    }
}
