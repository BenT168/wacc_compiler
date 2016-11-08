package symbolTable;

import frontEnd.semanticCheck.SemanticError;
import frontEnd.tree.Type.Identifier;
import frontEnd.tree.Type.Variable;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

public class SymbolTable implements SymbolTableInterface {

    private List<Variable> variableList;
    private SemanticError semanticError = new SemanticError();
    private SymbolTable parentSymtab;

    //Tells us whether we are in exit block in visitor class (0 if no, 1 if yes)
    private int exitCount;
    public SymbolTable encSymbolTable;

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

    public Identifier lookUpAll(String varName) {
    }

    public SymbolTable getEncSymbolTable() {
        return encSymbolTable;
    }
}

/* new */

public class SymbolTable {

    private SymbolTable encSymbolTable;
    private Dictionary<String, Identifier> dict;

    public SymbolTable(SymbolTable st) {
        dict = null ;
        encSymbolTable = st;
    }

    public void add(String name, Identifier id) {
        dict.put(name, id);
    }

    public Identifier lookUp(String name) {
        return dict.get(name);
    }

    public Identifier lookUpAll(String name) {
        SymbolTable s = this;
        while(s != null) {
            Identifier t = s.lookUp(name);
            if(t != null) {
                return t;
            }
            s = s.encSymbolTable;
        }
        return null;
    }

    public SymbolTable getEncSymbolTable() {
        return encSymbolTable;
    }

    public Dictionary<String, Identifier> getDict() {
        return dict;
    }
}

