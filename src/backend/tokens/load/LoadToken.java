package backend.tokens.load;


import backend.Token;
import backend.Register;

public class LoadToken extends Token {
	
	private Register dest;
	private String source;
	private String condition = "";

	
	public LoadToken(Register dest, String label) {
		this.dest = dest;
		this.addReg(dest);
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
