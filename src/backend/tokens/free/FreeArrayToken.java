package backend.tokens.free;

import backend.InstrToken;
import backend.TokenSequence;
import backend.system.SystemErrorTokens;
import backend.system.SystemFormatterTokens;
import backend.system.SystemFreeTokens;
import backend.system.SystemPrintTokens;

public class FreeArrayToken extends InstrToken {
	
	public FreeArrayToken() {
	}
	
	@Override
	public TokenSequence toAppend() {
		return new TokenSequence(
				SystemFreeTokens.FREE_ARRAY, 
				SystemErrorTokens.RUNTIME_ERROR, 
				SystemPrintTokens.PRINT_STRING);
	}
	
	@Override
	public TokenSequence toPrepend() {
		return new TokenSequence(
				SystemErrorTokens.NULL_REFERENCE_MESSAGE, 
				SystemFormatterTokens.STRING_FORMATTER);		
	}
	
	@Override
	public String toString() {
		return "BL p_free_array";
	}

}
