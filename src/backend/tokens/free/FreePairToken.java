package backend.tokens.free;

import backend.InstrToken;
import backend.TokenSequence;
import backend.system.SystemErrorTokens;
import backend.system.SystemFormatterTokens;
import backend.system.SystemFreeTokens;
import backend.system.SystemPrintTokens;

public class FreePairToken extends InstrToken {
	
	public FreePairToken() {
	}
	
	@Override
	public TokenSequence toAppend() {
		return new TokenSequence(
				SystemFreeTokens.FREE_PAIR, 
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
		return "BL p_free_pair";
	}

}
