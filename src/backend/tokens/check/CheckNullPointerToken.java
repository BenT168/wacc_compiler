package backend.tokens.check;

import backend.TokSeq;
import backend.Token;
import backend.system.SystemErrorHandler;
import backend.system.SystemFormatterHandler;
import backend.system.SystemPrintHandler;

public class CheckNullPointerToken extends Token {

	public CheckNullPointerToken() {
	}

	@Override
	public TokSeq toPrepend() {
		return SystemErrorHandler.NULL_POINTER_CHECK.toPrepend()
				.appendAll(SystemFormatterHandler.STRING_FORMATTER);
	}

	@Override
	public TokSeq toAppend() {
		TokSeq errors = new TokSeq(
				SystemErrorHandler.NULL_POINTER_CHECK,
				SystemErrorHandler.RUNTIME_ERROR,
				SystemPrintHandler.PRINT_STRING);
		return errors;
	}

	@Override
	public String toString() {
		return "BL p_check_null_pointer";
	}
}
