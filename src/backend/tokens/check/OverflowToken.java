package backend.tokens.check;

import backend.TokSeq;
import backend.Token;
import backend.system.SystemErrorHandler;
import backend.system.SystemPrintHandler;

public class OverflowToken extends Token {

	private String condition = "";

	public OverflowToken(String cond) {
		this.condition = cond;
	}

	@Override
	public TokSeq toPrepend() {
		return SystemErrorHandler.OVERFLOW_ERROR.toPrepend();
	}

	@Override
	public TokSeq toAppend() {
		TokSeq errors = new TokSeq(SystemErrorHandler.OVERFLOW_ERROR);
		errors.append(SystemErrorHandler.RUNTIME_ERROR);
		errors.append(SystemPrintHandler.PRINT_STRING);
		return errors;
	}

	@Override
	public String toString() {
		return "BL" + condition + " p_throw_overflow_error";
	}

}
