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
            case "unknownType" :
                System.out.println("Semantic Error: unknown type " + name);
            case "notAType"  :
                System.out.println("Semantic Error:" + name + "is not a Type");
            case "alreadyDeclared" :
                System.out.println("Semantic Error:" + name + "is already declared");
            case "unknownVar" :
                System.out.println("Semantic Error: unknown variable " + name);
            case "notAVar" :
                System.out.println("Semantic Error:" + name + "is not a Variable");
            case "notAFunction" :
                System.out.println("Semantic Error:" + name + "is not a Function");
            case "unknownFuntion" :
                System.out.println("Semantic Error: unknown function " + name);
            case "paramNumber" :
                System.out.println("Semantic Error: Wrong Number of Parameters");
            case "functionCallType" :
                System.out.println("Semantic Error: Type of Function parameter is incompatible with " +
                        "declaration");


        }
        System.exit(200);
    }

    public void semanticType(String expectType, String actualType) {
        System.out.println("Semantic error: Incompatible Type (expected: " + expectType.toUpperCase() +
                " ,actual: " + actualType.toUpperCase() + ")");
        System.exit(200);
    }
}

