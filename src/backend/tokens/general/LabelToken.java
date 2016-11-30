package backend.tokens.general;

import backend.Token;

public class LabelToken extends Token {
	
	private String label;
	
	public LabelToken(String label) {
		this.label = label;
	}
	
	@Override
	public String toString() {
		return label + ": ";
	}
	
	@Override
	public boolean requiresTab() {
		return false;
	}

}
