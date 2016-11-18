package frontEnd.exception;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.misc.ParseCancellationException;

public class MyErrorListener extends BaseErrorListener {

    public static MyErrorListener INSTANCE = new MyErrorListener();

    /* Printing the Syntax errors caught by parser */
    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                            int line, int charPositionInLine,
                            String msg, RecognitionException e) throws ParseCancellationException {
        System.err.print("Syntax Error: line " + line + ":" + charPositionInLine + " " + msg);
    }
}




