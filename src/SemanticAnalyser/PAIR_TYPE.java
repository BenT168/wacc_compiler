package SemanticAnalyser;


public class PAIR_TYPE extends EQUALITY {
	
	boolean prints = false;

	TYPE t1;
	TYPE t2;
	
	public PAIR_TYPE() {}
	
	public PAIR_TYPE(TYPE t1, TYPE t2){
		this.t1 = t1;
		this.t2 = t2;
	}
	
	public TYPE firstType(){
		return t1;
	}
	
	public TYPE secondType(){
		return t2;
	}
	
	@Override
	public boolean equals(Object o) {
	  
		if (o instanceof NULL) {
			return true;
		}
	  
		if (o.getClass().equals(this.getClass())) {
		  
			
			PAIR_TYPE p = (PAIR_TYPE) o;
			
			if (prints) System.out.println("Pairtype: this fisrt type: " + this.firstType().getClass().toString());
			if (prints) System.out.println("Pairtype: this second type: " + this.secondType().getClass().toString());
			
			if (prints) System.out.println("Pairtype: o fisrt type: " + p.firstType().getClass().toString());
      if (prints) System.out.println("Pairtype: o second type: " + p.secondType().getClass().toString());
			
      return SharedMethods.assignCompat(this.firstType(), p.firstType()) && SharedMethods.assignCompat(this.secondType(), p.secondType());
      
		//	return (p.firstType().equals(this.firstType()) && 
			//		p.secondType().equals(this.secondType()));
		}
		return false;
	}
}
