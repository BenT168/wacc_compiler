package backend.tokens.free;

import backend.Token;
import backend.TokenSequence;
import backend.system.SystemErrorHandler;
import backend.system.SystemFormatterHandler;
import backend.system.SystemFreeHandler;
import backend.system.SystemPrintHandler;

public class FreeArrayToken extends Token {

	public FreeArrayToken() {
	}

	@Override
	public TokenSequence toAppend() {
		return new TokenSequence(
				SystemFreeHandler.FREE_ARRAY,
				SystemErrorHandler.RUNTIME_ERROR,
				SystemPrintHandler.PRINT_STRING);
	}

	@Override
	public TokenSequence toPrepend() {
		return new TokenSequence(
				SystemErrorHandler.NULL_REFERENCE_MESSAGE,
				SystemFormatterHandler.STRING_FORMATTER);
	}

	@Override
	public String toString() {
		return "BL p_free_array";
	}

}
