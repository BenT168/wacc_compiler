package symbolTable;

import frontEnd.semanticCheck.SemanticError;
import frontEnd.tree.Type.Variable;

import java.util.ArrayList;
import java.util.List;

public class SymbolTable implements SymbolTableInterface {

    private List<Variable> variableList;
    private SemanticError semanticError = new SemanticError();
    private SymbolTable parentSymtab;

    //Tells us whether we are in exit block in visitor class (0 if no, 1 if yes)
    private int exitCount;

    //Use this constructor only for top-level symbol table (has no parent)
    public SymbolTable() {
        variableList = new ArrayList<>();
        parentSymtab = null;
        exitCount = 0;
    }

    /* Use this constructor to initialise any child symbol table.
     * Pass parent as argument. */
    public SymbolTable(SymbolTable parentSymtab) {
        variableList = new ArrayList<>();
        this.parentSymtab = parentSymtab;
        exitCount = 0;
    }

    //Add a variable to list
    public void add(Variable variable) {
        checkInList(variable);
        variableList.add(variable);
    }

    //Check if Variable is in List, if not return; if so, throw semantic error
    private void checkInList(Variable variable) {
        for(Variable v : variableList) {
            if (!(lookUp(variable.getName()) == null)) {
                //Variable in list so has already been initialised
                semanticError.semanticErrorCase(variable.getName(), "add");
            }
        }
        //Variable not in list so we return;
    }


    //For testing purposes to see if variables are added to list
    public void printList() {
        for(Variable v : variableList) {
            System.out.println("NAME: " + v.getName());
            System.out.println("TYPE: " + v.getType().getType());
            System.out.println("VALUE: " + v.getValue());
        }
    }

/*
   //Use lookupInt when value of variable in list is an int
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
           semanticError.semanticErrorCase(varName, "notInitialised");
       }
       return result;
   }

    //Use lookupBool when value of variable in list is a boolean
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
            semanticError.semanticErrorCase(varName, "notInitialised");
        }
        return result;
    }

    //Use lookupString when value of variable in list is a string
    public String lookUpString(String varName) {
        String result = "";
        boolean varFound = false;
        for(Variable v : variableList) {
            if (v.getName().compareTo(varName) == 0) {
                result = v.getStringValue();
                varFound = true;
            }
        }

        //check if varname has been found
        if(!varFound) {
            semanticError.semanticErrorCase(varName, "notInitialised");
        }
        return result;
    }

    //Use lookupChar when value of variable in list is a char
    public char lookUpChar(String varName) {
        char result = '0';
        boolean varFound = false;
        for(Variable v : variableList) {
            if (v.getName().compareTo(varName) == 0) {
                result = v.getCharValue();
                varFound = true;
            }
        }

        //check if varname has been found
        if(!varFound) {
            semanticError.semanticErrorCase(varName, "notInitialised");
        }
        return result;
    } */

    //Use lookUpType to check type of name in list if it exits
    public Variable lookUp(String name) {
        boolean varFound = false;
        for(Variable v : variableList) {
            if(v.getName().compareTo(name) == 0) {
                return v;
            }
        }
        //Need to generate error elsewhere otherwise this can't be used for recursive lookup
        //semanticError.semanticErrorCase(name, "notInitialised");
        return null;
    }

    //Removes all items from symbol table.
    public void clear() {
        variableList.clear();
    }
}
