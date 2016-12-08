package frontend.statements;

import backend.Register;
import backend.TokSeq;

/**
 * Class representing skips statements
 * Rule: SKIPS
 */


public class SkipNode extends StatNode {

	public SkipNode() {
	}

	public TokSeq assemblyCodeGenerating(Register register) {
		return new TokSeq();
	}

}
