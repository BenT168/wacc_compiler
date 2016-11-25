package frontend.statements;

import backend.Register;
import backend.TokenSequence;

/**
 * Class to represent sequential statements
 * Rule: statements ; statements
 */

public class MultiStatNode extends StatNode {

	private StatNode lhs;
	private StatNode rhs;

	public MultiStatNode(StatNode lhs, StatNode rhs) {
		this.lhs = lhs;
		this.rhs = rhs;
	}

	public TokenSequence toAssembly(Register register) {
		return lhs.toAssembly(register).appendAll(rhs.toAssembly(register));
	}
}
