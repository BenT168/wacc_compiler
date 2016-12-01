package backend.tokens.print;

import backend.Register;
import backend.TokSeq;
import backend.Token;
import backend.system.SystemPrintHandler;

public class ex_PrintBinToken extends Token {

	private Register r;

	public ex_PrintBinToken(Register r) {
		this.r = r;
		this.addReg(r);
	}

	@Override
	public TokSeq toPrepend() {
		return SystemPrintHandler.PRINT_BIN.toPrepend();
	}

	@Override
	public TokSeq toAppend() {
		return new TokSeq(SystemPrintHandler.PRINT_BIN);
	}

	public String toString() {
		return "MOV r0, " + r.toString() + "\n"
				+"\t\tBL p_print_bin";
	}


}
