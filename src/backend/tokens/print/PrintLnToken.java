package backend.tokens.print;

import backend.TokSeq;
import backend.Token;
import backend.system.SystemPrintHandler;

public class PrintLnToken extends Token {

	public PrintLnToken() {
	}

	@Override
	public TokSeq toPrepend() {
		return SystemPrintHandler.PRINT_LN.toPrepend();
	}

	@Override
	public TokSeq toAppend() {
		return new TokSeq(SystemPrintHandler.PRINT_LN);
	}

	@Override
	public String toString() {
		return "BL p_print_ln";
	}

}
