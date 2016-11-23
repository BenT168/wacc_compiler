package backend.tokens.load;


import backend.InstrToken;
import backend.Register;

public class LoadToken extends InstrToken {
	
	private Register dest;
	private String source;
	private String condition = "";

	
	public LoadToken(Register dest, String label) {
		this.dest = dest;
		this.addRegister(dest);
		this.source = "=" + label;
	}
	
	public LoadToken(String condition, Register dest, String label) {
		this(dest, label);
		this.condition = condition;
	}
	

	@Override 
	public String toString() {
		return "LDR" + condition + " " + dest.toString() + ", " + source;
	}
	
}
