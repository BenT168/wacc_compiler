package SemanticAnalyser;

public class VARIABLE extends IDENTIFIER{
	TYPE type;

	public VARIABLE(TYPE t) {
		type = t;
	}
	
	public TYPE TYPE(){
		return type;
	}
}
