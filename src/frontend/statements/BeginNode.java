package frontend.statements;

import backend.Register;
import backend.TokSeq;


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
