package frontEnd.exception;

public class ThrowException {

    /*Method for throwing semantic exceptions with line, column and error message*/
    public static void callSemanticException(int line, int column, String message) {
        throw new SemanticException("Semantic Error: line "+line+":"+column+" "+message);
    }

    /*Method for throwing synatax exceptions (that weren't caught in the parser) with line, column and error message*/
    public static void callSyntaxException(int line, int column, String message) {
        throw new SyntaxException("Syntax Error: line "+line+":"+column+" "+message);
    }

}
