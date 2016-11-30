package symboltable;

import frontend.exception.SyntaxErrorException;
import frontend.Tree;
import frontend.type.BaseType;

import java.util.HashMap;

public class SymbolTable {

	HashMap<String, Tree> dictionary;
	SymbolTable parentTable;
	final boolean isTopSymbolTable;
	private Expectation expectation;



	public SymbolTable(SymbolTable parentTable, BaseType expectedReturnType){
		this.parentTable = parentTable;
		this.dictionary = new HashMap<>();
		this.isTopSymbolTable = false;
		this.expectation = new Expectation(expectedReturnType);
	}


	public SymbolTable() {
		this.dictionary = new HashMap<>();
		this.isTopSymbolTable = true;
		this.expectation = new Expectation();
		this.parentTable = null;
	}


	public SymbolTable(SymbolTable currentSymbolTable) {
		this.dictionary = new HashMap<>();
		this.isTopSymbolTable = false;
		this.expectation = new Expectation();
		this.parentTable = currentSymbolTable;
	}

	public boolean isRecursive(String identifier) {
		if(this.isTopSymbolTable){
			return isContained(identifier);
		}
		return isContained(identifier) || parentTable.isRecursive(identifier);
	}

	public boolean isContained(String identifier) {
		return dictionary.containsKey(identifier);
	}

	public void add(String name, Tree node) {
		dictionary.put(name, node);
	}

	public SymbolTable getParent() {
		return this.parentTable;
	}

	public Tree get(String key) {
		if (this.isTopSymbolTable) {
			return dictionary.get(key);
		}
		if (!isContained(key)) {
			return parentTable.get(key);
		}
		return dictionary.get(key);
	}



	public boolean checkType(BaseType returnType) {
		return expectation.checkType(returnType);
	}

	public void finaliseScope(String funcName) {
		if(!expectation.isResolved()) {
			new SyntaxErrorException("The expectations of the function " + funcName + " were not met.", null);
		}
	}

	public void finaliseScope() {
		if(!expectation.isResolved()) {
			 new SyntaxErrorException("The expectations of this code block were not met.", null);
		}
	}

}
