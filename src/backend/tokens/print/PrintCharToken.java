package backend.tokens.print;

import backend.InstrToken;
import backend.Register;

public class PrintCharToken extends InstrToken {
	
	private Register r;
	
	public PrintCharToken(Register r) {
		this.r = r;
		this.addRegister(r);
	}
	
	public String toString() {
		return "MOV r0, " + r.toString() + "\n"
				+"\t\tBL putchar";
	}

}
