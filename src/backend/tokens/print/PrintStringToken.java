package backend.tokens.print;

import backend.TokSeq;
import backend.Token;
import backend.Register;
import backend.system.SystemPrintHandler;

public class PrintStringToken extends Token {

	private Register r;

	public PrintStringToken(Register r) {
		this.r = r;
		this.addReg(r);
	}

	@Override
	public TokSeq toPrepend() {
		return SystemPrintHandler.PRINT_STRING.toPrepend();
	}

	@Override
	public TokSeq toAppend() {
		return new TokSeq(SystemPrintHandler.PRINT_STRING);
	}

	@Override
	public String toString() {
		return "MOV r0, " + r.toString() + "\n"
				+ "\t\tBL p_print_string";
	}

}
