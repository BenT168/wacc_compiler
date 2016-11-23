package backend.tokens.general;

import backend.InstrToken;

public class EasyToken extends InstrToken {
	private String assemblyString;

	public EasyToken(String s) {
		this.assemblyString = s;
	}
	
	@Override
	public String toString() {
		return assemblyString;
	}
}
