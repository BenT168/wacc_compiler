package backend.tokens.print;

import backend.InstrToken;
import backend.TokenSequence;
import backend.system.SystemPrintTokens;

public class PrintLnToken extends InstrToken {

	public PrintLnToken() {
	}
	
	@Override
	public TokenSequence toPrepend() {
		return SystemPrintTokens.PRINT_LN.toPrepend();
	}
	
	@Override 
	public TokenSequence toAppend() {
		return new TokenSequence(SystemPrintTokens.PRINT_LN);
	}
	
	@Override
	public String toString() {
		return "BL p_print_ln";
	}
	
}
