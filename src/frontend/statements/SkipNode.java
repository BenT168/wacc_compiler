package frontend.statements;

import backend.Register;
import backend.TokenSequence;

public class SkipNode extends StatNode {

	//Skip statement does nothing
	public SkipNode() {
	}

	public TokenSequence toAssembly(Register register) {
		return new TokenSequence();
	}

}
