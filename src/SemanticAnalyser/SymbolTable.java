package SemanticAnalyser;

public class SymbolTable {

	public SymbolTable encSymTable;
	Dictionary Funcdict;
	Dictionary Vardict;
	
	public SymbolTable(SymbolTable encSymTable) {
		Funcdict = new Dictionary();
		Vardict = new Dictionary();
		this.encSymTable = encSymTable;
	}
	
	public void add(String name, IDENTIFIER obj) {
		Vardict.add(name, obj);
	}
	
	public void funcadd(String name, IDENTIFIER obj) {
		Funcdict.add(name, obj);
	}
	
	public IDENTIFIER lookup(String name) {
		return Vardict.get(name);
	}
	
	public IDENTIFIER lookupFunc(String name) {
		return Funcdict.get(name);
	}
	
	public void setEncTable(SymbolTable enc){
		encSymTable = enc;
	}
	
	public IDENTIFIER lookupAll(String name) {
		SymbolTable st = this;
		IDENTIFIER obj;

		while (st != null) {

			obj = st.lookup(name);
			if(obj != null) {
				return obj;
			}
			st = st.encSymTable;
		}
		return null;
	}
	
	public IDENTIFIER lookupAllFunc(String name) {
		SymbolTable st = this;
		IDENTIFIER obj;

		while (st != null) {

			obj = st.lookupFunc(name);
			if(obj != null) {
				return obj;
			}
			st = st.encSymTable;
		}
		return null;
	}
}
