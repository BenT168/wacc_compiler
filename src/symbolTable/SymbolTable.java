package symbolTable;

import frontEnd.tree.IdentifierAST;
import frontEnd.tree.Type.BaseType;
import java.util.HashMap;
import java.util.Map;

public class SymbolTable {

    private SymbolTable encSymbolTable;
    private Map<String, IdentifierAST> dict;
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

    public void add(String name, IdentifierAST id) {
        dict.put(name, id);
    }

    public void replace(String name, IdentifierAST id) {
        dict.replace(name, id);
    }

    public IdentifierAST lookUp(String name) {
        if (!containsCurrent(name)) {
        	return encSymbolTable.lookUp(name);
	}
        return dict.get(name);
    }



    public IdentifierAST lookUpAll(String name) {
        SymbolTable s = this;
        while(s != null) {
            IdentifierAST t = s.lookUp(name);
            if(t != null) {
                return t;
            }
            s = s.encSymbolTable;
        }
        return null;
    }

    public void remove(String name) {
        if (!containsCurrent(name)) {
                encSymbolTable.remove(name);
        }
        dict.remove(name);
    }


    public SymbolTable getEncSymbolTable() {
        return encSymbolTable;
    }

    public Map<String, IdentifierAST> getDict() {
        return dict;
    }
    
    public boolean containsRecursive(String ident) {
	if(this.isTopSymbolTable){
		return containsCurrent(ident);
	}
	return containsCurrent(ident) || encSymbolTable.containsRecursive(ident);
    }

    public boolean containsAll(String ident) {
        return !(lookUpAll(ident) == null);
    }
    
    public boolean containsCurrent(String ident) {
	return dict.containsKey(ident);
    }
    
    public boolean checkType(BaseType returnType) {
	return expectation.checkType(returnType);
    }
}
