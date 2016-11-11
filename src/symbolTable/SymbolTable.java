package symbolTable;

import frontEnd.ErrorHandling.UnresolvedExpectationException;
import frontEnd.tree.ASTTree;
import frontEnd.tree.Type.BaseType;
import java.util.HashMap;

public class SymbolTable {

    SymbolTable encSymbolTable;
    private HashMap<String, ASTTree> dict;
    private Expectation expectation;
    private boolean isTopSymbolTable;

    /* Pass parent as argument. Pass null if top-level Symtab */
    public SymbolTable(SymbolTable parent, BaseType expected) {
        this.expectation = new Expectation(expected);
        this.dict = new HashMap<>();
	    this.encSymbolTable = parent;
        this.isTopSymbolTable = false;

    }

     public SymbolTable(SymbolTable current) {
        this.expectation = new Expectation();
        this.dict = new HashMap<>();
        this.encSymbolTable = current;
        this.isTopSymbolTable = false;
    }

     public SymbolTable() {
        this.expectation = new Expectation();
        this.dict = new HashMap<>();
        this.encSymbolTable = null;
        this.isTopSymbolTable = true;

    }

    public void add(String name, ASTTree id) {
        dict.put(name, id);
    }

    public ASTTree lookUp(String name) {
        if (!containsCurrent(name)) {
        	return encSymbolTable.lookUp(name);
	}
        return dict.get(name);
    }


    public void remove(String name) {
        if (!containsCurrent(name)) {
                encSymbolTable.remove(name);
        }
        dict.remove(name);
    }


    public SymbolTable getEncSymbolTable() {
        return this.encSymbolTable;
    }
    
    public boolean containsRecursive(String ident) {
	if(this.isTopSymbolTable){
		return containsCurrent(ident);
	}
	return containsCurrent(ident) || encSymbolTable.containsRecursive(ident);
    }
    
    public boolean containsCurrent(String ident) {
	return dict.containsKey(ident);
    }
    
    public boolean checkType(BaseType returnType) {
	return expectation.checkType(returnType);
    }

    public void finaliseScope(String funcName) {
        if(!expectation.isResolved()){
             new UnresolvedExpectationException("The code block is the expected one.");
        }
    }

    public void finaliseScope() {
        if(!expectation.isResolved()){
            new UnresolvedExpectationException("The code block is the expected one.");
        }
    }
}
