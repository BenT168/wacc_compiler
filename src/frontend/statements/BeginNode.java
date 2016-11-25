package frontend.statements;

import backend.Register;
import backend.TokenSequence;

/**
 * Class to represent block statements
 * Rule: 'begin' statements 'end'
 *
 */

public class BeginNode extends StatNode {

	private StatNode statNode;

	public BeginNode(StatNode statNode) {
		this.statNode = statNode;
	}

	@Override
	public TokenSequence toAssembly(Register r) {
		return statNode.toAssembly(r);
	}
}
