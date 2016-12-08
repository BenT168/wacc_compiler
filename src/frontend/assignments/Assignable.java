package frontend.assignments;

import backend.Register;
import backend.TokSeq;
import backend.tokens.branch.BranchLinkToken;
import backend.tokens.load.LoadToken;
import frontend.Tree;

public abstract class Assignable extends Tree {

	/*
	Method returns the token sequence of the instructions of loading and mallocing
	 */
	 TokSeq mallocSequence(int size, int varSize) {
		LoadToken loadT = new LoadToken(Register.R0, Integer.toString(size*varSize));
		BranchLinkToken bl = new BranchLinkToken("malloc");
		return new TokSeq(loadT, bl);
	}
}