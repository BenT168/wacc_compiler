package backend.tokens.general;

import backend.Token;
import backend.Register;

public class PushToken extends Token {
	
	private Register reg;
	
	public PushToken(Register reg) {
		this.reg = reg;
		this.addReg(reg);
	}
	
	@Override
	public String toString() {
		return "PUSH " + "{" +reg.toString() + "}";
	}

}
