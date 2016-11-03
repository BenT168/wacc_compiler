import java.util.ArrayList;
import java.util.List;

public class Assignments {

    private List<Variable> variableList;
    private SemanticError semanticError = new SemanticError();

    //Tells us whether we are in exit block in visitor class (0 if no, 1 if yes)
    int exitCount;


    public Assignments() {
        variableList = new ArrayList<>();
        exitCount = 0;
    }

    public void add(Variable variable) {
        variableList.add(variable);
    }

    public void printList() {
        for(Variable v : variableList) {
            System.out.println("NAME: " + v.getName());
            System.out.println("TYPE: " + v.getType().getType());
            switch(v.getType().getType()) {
                case "char" :
                    System.out.println("VALUE: " + v.getCharValue());
                    break;
                case "string" :
                    System.out.println("VALUE: " + v.getStringValue());
                    break;
                case "bool" :
                    System.out.println("VALUE: " + v.getBoolValue());
                    break;
                case "int" :
                    System.out.println("VALUE: " + v.getIntValue());
                    break;
            }
        }
    }


   //Use lookupInt when value is an int
   public int lookUpInt(String varName) {
       int result = 0;
       boolean varFound = false;
       for(Variable v : variableList) {
           if (v.getName().compareTo(varName) == 0) {
               result = v.getIntValue();
               varFound = true;
           }
       }
       System.out.println(varFound);

       //check if varname has been found
       if(!varFound) {
           if(exitCount == 1) {
               semanticError.semanticErrorCase(varName, "exit");
           }
           semanticError.semanticErrorCase(varName, "Variable");
       }
       return result;
   }

    //Use lookupBool when value is an boolean
    public boolean lookUpBool(String varName) {
        boolean result = false;
        boolean varFound = false;
        for(Variable v : variableList) {
            if (v.getName().compareTo(varName) == 0) {
                result = v.getBoolValue();
                varFound = true;
            }
        }

        //check if varname has been found
        if(!varFound) {
            semanticError.semanticErrorCase(varName, "Variable");
        }
        return result;
    }

    //Use lookUpType to check type of name in list if it exits
    public Type lookUpType(String name) {
        Type type = new Type("int");
        boolean varFound = false;
        for(Variable v : variableList) {
            if(v.getName().compareTo(name) == 0) {
                type = v.getType();
                varFound = true;
            }
        }
        if(!varFound) {
            semanticError.semanticErrorCase(name, "Variable");
        }
        return type;
    }
}
