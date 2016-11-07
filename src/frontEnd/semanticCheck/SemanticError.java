package frontEnd.semanticCheck;

public class SemanticError {


    public void semanticErrorCase(String name, String errorType) {
        switch(errorType) {
            case "notInitialised" :
                System.out.println("Semantic error: " + name + " has not been initialised.");
            case "exit" :
                System.out.println("Semantic Error: exit is not executed with the right type");
            case "add" :
                System.out.println("Semantic error: " + name + " has already been initialised.");
        }
        System.exit(200);
    }

    public void semanticType(String expectType, String actualType) {
        System.out.println("Semantic error: Incompatible Type (expected: " + expectType.toUpperCase() +
        " ,actual: " + actualType.toUpperCase() + ")");
        System.exit(200);
    }

}
