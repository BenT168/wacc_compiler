package backend.tokens.print;

import backend.InstrToken;
import backend.Register;
import backend.TokenSequence;
import backend.system.SystemPrintTokens;

public class PrintIntToken extends InstrToken {
	
	private Register r;
	
	public PrintIntToken(Register r) {
		this.r = r;
		this.addRegister(r);
	}
	
	@Override
	public TokenSequence toPrepend() {
		return SystemPrintTokens.PRINT_INT.toPrepend();
	}

	@Override
	public TokenSequence toAppend() {
		return new TokenSequence(SystemPrintTokens.PRINT_INT);
	}
	
	
	
	public String toString() {
		return "MOV r0, " + r.toString() + "\n"
				+"\t\tBL p_print_int";
	}


}
