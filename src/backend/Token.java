package backend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Token {
	private List<Register> regs;
	
	public Token() {
		regs = new ArrayList<>();
	}
	
	public void addRegister(Register... rs) {
		regs.addAll(Arrays.asList(rs));
	}

	/**
	 * @return the TokenSequence containing the data that this Token needs
	 */
	public TokenSequence toPrepend() {
		// normally, there's nothing to prepend
		return TokenSequence.EMPTY_SEQUENCE;
	}

	/** This is used to signal that this token has some kind of dependencies, such as system method calls.
	 * @return the TokenSequence containing the Token this method depends on.
	 */
	public TokenSequence toAppend() {
		// normally, there's nothing to append
		return TokenSequence.EMPTY_SEQUENCE;
	}
	
	/**
	 * 	This method is used to concretise the abstract representation of Registers.
	 * @param a
	 * 	The RegisterAllocator that will provide the register number (int).
	 * 	Currently this returns 4, 5, 6, etc.
	 */
	public void setRegisters(RegisterAllocator alloc) {
		for (Register r:regs) {
			alloc.allocate(r);
		}
	}
	
	public boolean requiresTab() {
		return true;
	}

}
