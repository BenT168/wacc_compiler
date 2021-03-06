package backend.tokens.operator;

import backend.Token;
import backend.Register;

public class MultiplyToken extends Token {
	
	private Register dest;
	private Register op1;
	private Register op2;
	private String condition = "";
	
	public MultiplyToken(Register dest,  Register op1, Register op2) {
		this.dest = dest;
		this.op1 = op1;
		this.op2 = op2;
		this.addReg(dest, op1, op2);
	}
	
	public MultiplyToken(String condition, Register dest,  Register op1, Register op2) {
		this(dest, op1, op2);
		this.condition = condition;
	}

	@Override
	public String toString() {
		return "MUL" + condition + " " + dest.toString() + ", " + op1.toString() + ", " + op2.toString();
	}
	
}
