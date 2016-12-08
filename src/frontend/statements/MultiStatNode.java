package frontend.statements;

import backend.Register;
import backend.TokSeq;

/**
 * Class representing multiple statements
 * Rule: statement ; statement
 */

public class MultiStatNode extends StatNode {

	private StatNode lhs;
	private StatNode rhs;

	public MultiStatNode(StatNode lhs, StatNode rhs) {
		this.lhs = lhs;
		this.rhs = rhs;
	}

	public TokSeq assemblyCodeGenerating(Register register) {
		return lhs.assemblyCodeGenerating(register).appendAll(rhs.assemblyCodeGenerating(register));
	}
}
