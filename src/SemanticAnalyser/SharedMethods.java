package SemanticAnalyser;

import org.antlr.v4.runtime.tree.ParseTree;


public class SharedMethods {

	SymbolTable CurrentSymbolTable;
	
	public boolean scalartype(TYPE type, String name) {
		return type == CurrentSymbolTable.lookup(name);
	}
	
	



	public static boolean assignCompat(TYPE lhs, TYPE rhs) {

	  
	  
		
//		System.out.println("AssignCompat lhs: " + lhs.getClass().toString());
//		System.out.println("AssignCompat rhs: " + rhs.getClass().toString());
		if (rhs instanceof NULL || lhs instanceof NULL) {
			return true;
		}
		if (lhs == null) {
			System.out.println("lhs is null");
			return false;
		}
		if (rhs == null) {
			System.out.println("rhs is null");
			return false;
		}

		return lhs.equals(rhs);
		
	}
	
	public void detectFunc(ParseTree tree, SymbolTable table) {
		
	}
}
