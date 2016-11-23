package frontend.stat;

import backend.Register;
import backend.TokenSequence;

public class SkipStatNode extends StatNode {

	//Skip statement does nothing
	public SkipStatNode() {
	}
	
	public TokenSequence toAssembly(Register register) {
		return new TokenSequence();
	}

}
