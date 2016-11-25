package backend.tokens.operator;

import backend.Token;
import backend.TokenSequence;
import backend.system.SystemErrorHandler;
import backend.system.SystemPrintHandler;



public class DivideByZeroErrorToken extends Token {

	public DivideByZeroErrorToken() {
	}

	@Override
	public TokenSequence toPrepend() {
		return SystemErrorHandler.DIVIDE_BY_ZERO_ERROR.toPrepend();
	}

	@Override
	public TokenSequence toAppend() {
		TokenSequence errors = new TokenSequence(SystemErrorHandler.DIVIDE_BY_ZERO_ERROR);
		errors.append(SystemErrorHandler.RUNTIME_ERROR);
		errors.append(SystemPrintHandler.PRINT_STRING);
		return errors;
	}

	@Override
	public String toString() {
		return "BL p_check_divide_by_zero";
	}
}
