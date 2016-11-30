package backend.tokens.check;

import backend.Token;
import backend.TokSeq;
import backend.system.SystemErrorHandler;
import backend.system.SystemPrintHandler;

public class CheckArrayBoundsToken extends Token {

	public CheckArrayBoundsToken() {
	}

	@Override
	public TokSeq toPrepend() {
		return SystemErrorHandler.CHECK_BOUNDS.toPrepend();
	}

	@Override
	public TokSeq toAppend() {
		TokSeq errors = new TokSeq(
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
