package backend.tokens.load;

import backend.Token;
import backend.Register;

public class LoadAddressToken extends Token {
	
	private Register dest;
	private Register source;
	private String condition = "";
	private boolean offsetSet;
	private int offset;

	public LoadAddressToken(Register dest, Register source) {
		this.dest = dest;
		this.source = source;
		offsetSet = false;
		this.addReg(dest, source);
	}
	
	public LoadAddressToken(Register dest, Register source, int offset) {
		this(dest, source);
		this.offset = offset;
		offsetSet = true;
	}
	
	public LoadAddressToken(String condition, Register dest, Register source) {
		this(dest, source);
		this.condition = condition;
	}
	
	public LoadAddressToken(String condition, Register dest, Register source, int offset) {
		this(dest, source, offset);
		this.condition = condition;
	}
	
	
	@Override
	public String toString() {
		return (offsetSet && offset != 0) ?
				"LDR" + condition +  " " + dest.toString() + ", " + "[" + source.toString() + ", #" + offset + "]" :
					"LDR" + condition +  " " + dest.toString() + ", " + "[" + source.toString() + "]";
	}
}
