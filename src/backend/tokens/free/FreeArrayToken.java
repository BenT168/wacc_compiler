package backend.tokens.free;

import backend.Token;
import backend.TokSeq;
import backend.system.SystemErrorHandler;
import backend.system.SystemFormatterHandler;
import backend.system.SystemFreeHandler;
import backend.system.SystemPrintHandler;

public class FreeArrayToken extends Token {

	public FreeArrayToken() {
	}

	@Override
	public TokSeq toAppend() {
		return new TokSeq(
				SystemFreeHandler.FREE_ARRAY,
				SystemErrorHandler.RUNTIME_ERROR,
				SystemPrintHandler.PRINT_STRING);
	}

	@Override
	public TokSeq toPrepend() {
		return new TokSeq(
				SystemErrorHandler.NULL_REFERENCE_MESSAGE,
				SystemFormatterHandler.STRING_FORMATTER);
	}

	@Override
	public String toString() {
		return "BL p_free_array";
	}

}
