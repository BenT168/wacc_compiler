package backend.tokens.print;

import backend.Register;
import backend.TokSeq;
import backend.Token;
import backend.system.SystemPrintHandler;

public class ex_PrintHexToken extends Token {

	private Register r;

	public ex_PrintHexToken(Register r) {
		this.r = r;
		this.addReg(r);
	}

	@Override
	public TokSeq toPrepend() {
		return SystemPrintHandler.PRINT_HEX.toPrepend();
	}

	@Override
	public TokSeq toAppend() {
		return new TokSeq(SystemPrintHandler.PRINT_HEX);
	}

	public String toString() {
		return "MOV r0, " + r.toString() + "\n"
				+"\t\tBL p_print_hex";
	}


}
