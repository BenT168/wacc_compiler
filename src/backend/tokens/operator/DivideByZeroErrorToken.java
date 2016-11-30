package backend.tokens.operator;

import backend.TokSeq;
import backend.Token;
import backend.system.SystemErrorHandler;
import backend.system.SystemPrintHandler;



public class DivideByZeroErrorToken extends Token {

	public DivideByZeroErrorToken() {
	}

	@Override
	public TokSeq toPrepend() {
		return SystemErrorHandler.DIVIDE_BY_ZERO_ERROR.toPrepend();
	}

	@Override
	public TokSeq toAppend() {
		TokSeq errors = new TokSeq(SystemErrorHandler.DIVIDE_BY_ZERO_ERROR);
		errors.append(SystemErrorHandler.RUNTIME_ERROR);
		errors.append(SystemPrintHandler.PRINT_STRING);
		return errors;
	}

	@Override
	public String toString() {
		return "BL p_check_divide_by_zero";
	}
}
