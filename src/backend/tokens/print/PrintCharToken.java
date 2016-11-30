package backend.tokens.print;

import backend.Token;
import backend.Register;

public class PrintCharToken extends Token {
	
	private Register r;
	
	public PrintCharToken(Register r) {
		this.r = r;
		this.addReg(r);
	}
	
	public String toString() {
		return "MOV r0, " + r.toString() + "\n"
				+"\t\tBL putchar";
	}

}
