package backend.tokens.move;

import backend.InstrToken;
import backend.Register;


public abstract class MovToken extends InstrToken {
	protected Register dest;
	protected Register regSource;
	protected String immSource;
	protected String condition = "";
	
}
