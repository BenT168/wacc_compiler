package backend.tokens.general;

import backend.Token;

public class EasyToken extends Token {
	private String assemblyString;

	public EasyToken(String s) {
		this.assemblyString = s;
	}
	
	@Override
	public String toString() {
		return assemblyString;
	}
}
