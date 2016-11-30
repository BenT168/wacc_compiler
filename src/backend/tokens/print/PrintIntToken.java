package backend.tokens.print;

import backend.Token;
import backend.Register;
import backend.TokSeq;
import backend.system.SystemPrintHandler;

public class PrintIntToken extends Token {

	private Register r;

	public PrintIntToken(Register r) {
		this.r = r;
		this.addReg(r);
	}

	@Override
	public TokSeq toPrepend() {
		return SystemPrintHandler.PRINT_INT.toPrepend();
	}

	@Override
	public TokSeq toAppend() {
		return new TokSeq(SystemPrintHandler.PRINT_INT);
	}



	public String toString() {
		return "MOV r0, " + r.toString() + "\n"
				+"\t\tBL p_print_int";
	}


}
