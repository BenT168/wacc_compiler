package backend.tokens.operator;

import backend.Token;
import backend.Register;

public class CompareToken extends Token {

	private Register op1;
	private Register op2;
	//the shift string is used to shift one of the registers
	private String i;
	private String shift = "";
	private boolean shiftSet = false;

	public CompareToken(Register op1, Register op2) {
		this.op1 = op1;
		this.op2 = op2;
		this.addReg(op1, op2);
	}

	public CompareToken(Register op1, Register op2, String shift) {
		this.op1 = op1;
		this.op2 = op2;
		this.shift = shift;
		shiftSet = true;
		this.addReg(op1, op2);
	}

	public CompareToken(Register op1, String i) {
		this.op1 = op1;
		this.i = i;
	}

	@Override
	public String toString() {
		if (op2 != null) {
			return shiftSet? "CMP " + op1.toString() + ", " + op2.toString() + ", " + shift:
				"CMP " + op1.toString() + ", " + op2.toString();
		} else {
			return "CMP " + op1.toString() + ", " + i;
		}
	}
}
