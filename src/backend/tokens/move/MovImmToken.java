package backend.tokens.move;

import backend.Register;

public class MovImmToken extends MovToken {
	
	public MovImmToken(String condition, Register r, String s) {
		this.dest = r;
		this.immSource = s;
		this.condition = condition;
		this.addReg(r);
	}
	
	/**
	 * General constructor for MOV tokens with immediate values
	 * @param r The destination register
	 * @param string The immediate source value (e.g. "=12")
	 */
	public MovImmToken(Register r, String s) {
		this.dest = r;
		this.immSource = s;
		this.addReg(r);
	}
	
	@Override
	public String toString() {
		return "MOV" + condition + " " + dest.toString() + ", #" + immSource;
	}
	
}
