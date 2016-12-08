package frontend.statements;

import backend.Register;
import backend.TokSeq;

/**
 * Class representing a general statements
 * Rule: BEGIN statements END
 */

public class BeginNode extends StatNode {

	private StatNode statNode;

	public BeginNode(StatNode statNode) {
		this.statNode = statNode;
	}

	@Override
	public TokSeq assemblyCodeGenerating(Register r) {
		return statNode.assemblyCodeGenerating(r);
	}
}
