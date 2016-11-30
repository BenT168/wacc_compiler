package backend.tokens.operator;

import backend.Token;
import backend.Register;

public class SubToken extends Token {
	
	private Register dest;
	private Register op1;
	private Object op2;
	private String condition = "";
	
	public SubToken(Register dest, Register op1, int i) {
		this.dest = dest;
		this.op1 = op1;
		this.op2 = "#" + i;
		this.addReg(dest, op1);
	}
	
	public SubToken(Register dest, Register op1, Register op2) {
		this.dest = dest;
		this.op1 = op1;
		this.op2 = op2;
		this.addReg(dest, op1, op2);
	}
	
	public SubToken(String condition, Register dest, Register op1, int i) {
		this(dest, op1, i);
		this.condition = condition;
	}
	
	public SubToken(String condition, Register dest, Register op1, Register op2) {
		this(dest, op1, op2);
		this.condition = condition;
	}
	
	@Override
	public String toString() {
		return "SUB" + condition + " " + dest.toString() + ", " + op1.toString() + ", " + op2.toString();
	}

}
