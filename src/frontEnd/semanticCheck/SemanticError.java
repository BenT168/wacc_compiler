package frontEnd.semanticCheck;

public class SemanticError {

    private boolean testEnvironment = false;
    private boolean debuggingEnvironment = false;

    public void semanticErrorCase(String name, String errorType) {
        switch(errorType) {
            case "notInitialised" :
                System.out.println("Semantic error: " + name + " has not been initialised.");
                break;
            case "exit" :
                System.out.println("Semantic Error: exit is not executed with the right type");
                break;
            case "add" :
                System.out.println("Semantic error: " + name + " has already been initialised.");
                break;
            case "unknownType" :
                System.out.println("Semantic Error: unknown type " + name);
                break;
            case "notAType"  :
                System.out.println("Semantic Error:" + name + "is not a Type");
                break;
            case "alreadyDeclared" :
                System.out.println("Semantic Error:" + name + "is already declared");
                break;
            case "unknownVar" :
                System.out.println("Semantic Error: unknown variable " + name);
                break;
            case "notAVar" :
                System.out.println("Semantic Error:" + name + "is not a Variable");
                break;
            case "notAFunction" :
                System.out.println("Semantic Error:" + name + "is not a Function");
                break;
            case "unknownFuntion" :
                System.out.println("Semantic Error: unknown function " + name);
                break;
            case "paramNumber" :
                System.out.println("Semantic Error: Wrong Number of Parameters");
                break;
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


    public void printV(String s) {
		if(testEnvironment)
			System.out.println(s);
	}

	public void printD(String s) {
		if(debuggingEnvironment)
			System.out.println(s);
	}

	public void printD(int indentation, String text) {
		String res = "";
		for (int i = 0; i < indentation; i++) {
			res += '\t';
		}
		printD(res + text);
	}
}

