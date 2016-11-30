package backend.tokens.general;

import backend.Token;
import backend.Register;

public class PopToken extends Token {
	
    private Register reg;
	
	public PopToken(Register reg) {
		this.reg = reg;
		this.addReg(reg);
	}
	
	@Override
	public String toString() {
		return "POP " + "{" + reg.toString() + "}";
	}

}
