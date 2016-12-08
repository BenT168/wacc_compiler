package backend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Token {
	private List<Register> regs;

	/* constructor */
	public Token() {
		regs = new ArrayList<>();
	}

	/* adds registers passed as argument */
	public void addReg(Register... rs) {
		regs.addAll(Arrays.asList(rs));
	}

	/* returns empty token sequence to prepend */
	public TokSeq toPrepend() {
		return TokSeq.EMPTY_SEQUENCE;
	}

	/* returns empty token sequence to append */
	public TokSeq toAppend() {
		return TokSeq.EMPTY_SEQUENCE;
	}

	/* allocates registers according to allocation passed as argument */
	public void setRegs(RegAlloc alloc) {
		for (Register r:regs) {
			alloc.allocate(r);
		}
	}

	/* returns true iff the token requires a tab */
	public boolean requiresTab() {
		return true;
	}

}
