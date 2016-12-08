package backend;

import backend.tokens.load.LoadAddressToken;
import backend.tokens.store.StoreToken;

public class StackPos {
	private static final int WORD_SIZE = 4;

	private int pos;
	private Register sourceReg;

	/* constructor */
	public StackPos(int i) {
		this.pos = i;
		this.sourceReg = Register.sp;
	}

	/* constructor with explicit source register */
	public StackPos(int i, Register r) {
		this.pos = i;
		this.sourceReg = r;
	}

	/* returns new token sequence new load address token */
	public TokSeq toLoadAddrAssembly(final Register r) {
		return new TokSeq().append(
				new LoadAddressToken(r, sourceReg, pos *WORD_SIZE));
	}

	/* returns new token sequence including new store token */
	public TokSeq toStoreAssembly(final Register r) {
		return new TokSeq().append(
				new StoreToken(sourceReg, r, pos *WORD_SIZE));
	}

	/* returns output assembly instruction */
	@Override
	public String toString() {
		return pos == 0 ? "[" + sourceReg.toString() + "]"
				: "[" + sourceReg.toString() + ", #" + pos + "]";
	}

	/* returns current stack index */
	public int getStackIndex() {
		return pos * WORD_SIZE;
	}

	/* base register getter */
	public Register getBaseReg() {
		return sourceReg;
	}
}
