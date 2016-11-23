package backend.tokens.print;

import backend.InstrToken;
import backend.Register;
import backend.TokenSequence;
import backend.system.SystemPrintTokens;

public class PrintStringToken extends InstrToken {
	
	private Register r;

	public PrintStringToken(Register r) {
		this.r = r;
		this.addRegister(r);
	}
	
	@Override
	public TokenSequence toPrepend() {
		return SystemPrintTokens.PRINT_STRING.toPrepend(); 
	}

	@Override
	public TokenSequence toAppend() {
		return new TokenSequence(SystemPrintTokens.PRINT_STRING);
	}
	
	@Override
	public String toString() {
		return "MOV r0, " + r.toString() + "\n"
				+ "\t\tBL p_print_string";
	}
	
}
