package frontend.statements;

import backend.Register;
import backend.TokSeq;

public class SkipNode extends StatNode {

	public SkipNode() {
	}

	public TokSeq assemblyCodeGenerating(Register register) {
		return new TokSeq();
	}

}
