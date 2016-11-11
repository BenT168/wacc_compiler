package SemanticAnalyser;

import java.util.HashMap;
import java.util.Map;


public class Dictionary {

	Map<String, IDENTIFIER> map = new HashMap<String, IDENTIFIER>();
	
	public void add(String name, IDENTIFIER obj) {
		map.put(name, obj);
	}
	
	public IDENTIFIER get(String name) {
		return map.get(name);
	}
}
