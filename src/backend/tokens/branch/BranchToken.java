package backend.tokens.branch;

import backend.Token;

public class BranchToken extends Token {

	private String label;
	private String condition;
	
	public BranchToken(String condition, String label) {
		this.label = label;
		this.condition = condition;
	}
	
	public BranchToken(String label) {
		this.label = label;
		this.condition = "";
	}
	
	@Override
	public String toString() {
		return "B" + condition +  " " + label;
	}
}
