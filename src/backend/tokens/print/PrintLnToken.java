package backend.tokens.print;

import backend.Token;
import backend.TokenSequence;
import backend.system.SystemPrintHandler;

public class PrintLnToken extends Token {

	public PrintLnToken() {
	}

	@Override
	public TokenSequence toPrepend() {
		return SystemPrintHandler.PRINT_LN.toPrepend();
	}

	@Override
	public TokenSequence toAppend() {
		return new TokenSequence(SystemPrintHandler.PRINT_LN);
	}

	@Override
	public String toString() {
		return "BL p_print_ln";
	}

}
