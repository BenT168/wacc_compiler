package frontend.assignments;

import backend.Register;
import backend.TokenSequence;
import backend.tokens.branch.BranchLinkToken;
import backend.tokens.load.LoadToken;
import frontend.WACCTree;

public abstract class Assignable extends WACCTree {
	
	//forms allocation token sequence (refactors)
	TokenSequence mallocSequence(int size, int varSize) {
		LoadToken loadT = new LoadToken(Register.R0, Integer.toString(size*varSize));
		BranchLinkToken bl = new BranchLinkToken("malloc");
		return new TokenSequence(loadT, bl);
	}
}