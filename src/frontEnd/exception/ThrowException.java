package frontEnd.exception;

public class ThrowException {

    /*Check which exception thrown so that can exit with right code in main */
    public static boolean semanticExceptionThrown = false;
    public static boolean syntaxExceptionThrown = false;


    /*Method for throwing semantic exceptions with line, column and error message*/
    public static void callSemanticException(int line, int column, String message) {
        semanticExceptionThrown = true;
        System.err.println("Semantic Error: line "+line+":"+column+" "+message);
    }

    /*Method for throwing synatax exceptions (that weren't caught in the parser) with line, column and error message*/
    public static void callSyntaxException(int line, int column, String message) {
        syntaxExceptionThrown = true;
        throw new SyntaxException("Syntax Error: line "+line+":"+column+" "+message);
    }
}
