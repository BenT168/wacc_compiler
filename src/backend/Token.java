package backend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Token {
	private List<Register> regs;
	
	public Token() {
		regs = new ArrayList<>();
	}
	
	public void addReg(Register... rs) {
		regs.addAll(Arrays.asList(rs));
	}

	public TokSeq toPrepend() {
		return TokSeq.EMPTY_SEQUENCE;
	}

	public TokSeq toAppend() {
		return TokSeq.EMPTY_SEQUENCE;
	}

	public void setRegs(RegAlloc alloc) {
		for (Register r:regs) {
			alloc.allocate(r);
		}
	}
	
	public boolean requiresTab() {
		return true;
	}

}
