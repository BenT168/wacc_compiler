package backend.tokens.move;

import backend.Token;
import backend.Register;


public abstract class MovToken extends Token {
	protected Register dest;
	protected Register regSource;
	protected String immSource;
	protected String condition = "";
	
}
