package backend;

import backend.tokens.load.LoadAddressToken;
import backend.tokens.store.StoreToken;

public class StackPos {
	private static final int WORD_SIZE = 4;

	private int pos;
	private Register sourceReg;

	public StackPos(int i) {
		this.pos = i;
		this.sourceReg = Register.sp;
	}

	public StackPos(int i, Register r) {
		this.pos = i;
		this.sourceReg = r;
	}

	public TokSeq toAssembly(final Register r) {
		return new TokSeq().append(
				new LoadAddressToken(r, sourceReg, pos *WORD_SIZE));
	}

	public TokSeq toStoreAssembly(final Register r) {
		return new TokSeq().append(
				new StoreToken(sourceReg, r, pos *WORD_SIZE));
	}

	@Override
	public String toString() {
		return pos == 0 ? "[" + sourceReg.toString() + "]"
				: "[" + sourceReg.toString() + ", #" + pos + "]";
	}

	public int getStackIndex() {
		return pos * WORD_SIZE;
	}

	public Register getBaseReg() {
		return sourceReg;
	}
}
