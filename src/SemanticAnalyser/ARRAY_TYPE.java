package SemanticAnalyser;

public class ARRAY_TYPE extends EQUALITY{
	boolean prints = false;
	
	TYPE type;
	int elements;
	
	public ARRAY_TYPE(TYPE type) {
		this.type = type;
	}
	
	public TYPE TYPE(){
		return type;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o.getClass().equals(this.getClass())) {
		  ARRAY_TYPE a = (ARRAY_TYPE) o;
			

      if (prints) System.out.println("Arraytype this type : " + type.getClass().toString()) ;
      if (prints) System.out.println ("Arraytype a type : " + a.type.getClass().toString()) ;
		  
		  return a.TYPE().equals(type);
			
			
		}
		return false;
	}
}
