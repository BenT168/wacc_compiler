package backend.tokens.check;

import backend.Token;
import backend.TokenSequence;
import backend.system.SystemErrorHandler;
import backend.system.SystemPrintHandler;

public class CheckArrayBoundsToken extends Token {

	public CheckArrayBoundsToken() {
	}

	@Override
	public TokenSequence toPrepend() {
		return SystemErrorHandler.CHECK_BOUNDS.toPrepend();
	}

	@Override
	public TokenSequence toAppend() {
		TokenSequence errors = new TokenSequence(
				SystemErrorHandler.CHECK_BOUNDS,
				SystemErrorHandler.RUNTIME_ERROR,
				SystemPrintHandler.PRINT_STRING);
		return errors;
	}

	@Override
	public String toString() {
		return "BL p_check_array_bounds";
	}
}
