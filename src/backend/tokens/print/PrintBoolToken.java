package backend.tokens.print;

import backend.TokSeq;
import backend.system.SystemPrintHandler;
import backend.Token;
import backend.Register;

public class PrintBoolToken extends Token {

	private Register r;

	public PrintBoolToken(Register r) {
		this.r = r;
		this.addReg(r);
	}

	@Override
	public TokSeq toPrepend() {
		return SystemPrintHandler.PRINT_BOOL.toPrepend();
	}

	@Override
	public TokSeq toAppend() {
		return new TokSeq(SystemPrintHandler.PRINT_BOOL);
	}

	@Override
	public String toString() {
		return "MOV r0, " + r.toString() + "\n"
				+ "\t\tBL p_print_bool";
	}

}
