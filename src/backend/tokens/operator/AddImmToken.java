package backend.tokens.operator;

import backend.Token;
import backend.Register;

public class AddImmToken extends Token {
	private Register dest;
	private Register op1;
	private String op2;
	private String condition = "";
	
	
	public AddImmToken(Register dest, Register op1, String op2) {
		this.dest = dest;
		this.op1 = op1;
		this.op2 = "#" + op2;
		this.addReg(dest, op1);
	}
	
	public AddImmToken(String condition, Register dest, Register op1, String op2) {
		this(dest, op1, op2);
		this.condition = condition;
	}
	
	public AddImmToken(Register sp, Register sp2, int current) {
		this(sp, sp2, String.valueOf(current));
	}

	@Override
	public String toString() {
		return "ADD" + condition + " " + dest.toString() + ", " + op1.toString() + ", " + op2;
	}
}
