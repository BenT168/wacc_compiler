/**
 * Created by aa14415 on 03/11/16.
 */
public class SemanticError {


    public void semanticErrorCase(String name, String errorType) {
        switch(errorType) {
            case "Variable" :
                System.out.println("Semantic error: " + name + " has not been initialised.");
            case "exit" :
                System.out.println("Semantic Error: exit is not executed with the right type");
        }
        System.exit(200);
    }

    public void semanticType(String expectType, String actualType) {
        System.out.println("Semantic error: Incompatible Type (expected: " + expectType.toUpperCase() +
        " ,actual: " + actualType.toUpperCase() + ")");
        System.exit(200);
    }

}
