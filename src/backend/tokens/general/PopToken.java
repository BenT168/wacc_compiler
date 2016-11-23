package backend.tokens.general;

import backend.InstrToken;
import backend.Register;

public class PopToken extends InstrToken {
	
    private Register reg;
	
	public PopToken(Register reg) {
		this.reg = reg;
		this.addRegister(reg);
	}
	
	@Override
	public String toString() {
		return "POP " + "{" + reg.toString() + "}";
	}

}
