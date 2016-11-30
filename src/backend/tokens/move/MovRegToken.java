package backend.tokens.move;

import backend.Register;

public class MovRegToken extends MovToken {
	
	public MovRegToken(String condition, Register rDest, Register rSrc){
		this.dest = rDest;
		this.regSource = rSrc;
		this.condition = condition;
		this.addReg(rDest, rSrc);
	}
	
	/**
	 * Constructor for register to register copy.
	 * @param rDest The destination register
	 * @param rSrc  The source register
	 */
	public MovRegToken(Register rDest, Register rSrc){
		this.dest = rDest;
		this.regSource = rSrc;
		this.addReg(rDest, rSrc);
	}
	
	@Override
	public String toString() {
		return "MOV" + condition + " " + dest.toString() + ", " + regSource.toString();
	}
	
}
