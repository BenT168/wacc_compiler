package backend.tokens.print;

import backend.TokSeq;
import backend.Token;
import backend.Register;
import backend.system.SystemPrintHandler;

public class PrintReferenceToken extends Token {

	private Register reg;

	public PrintReferenceToken(Register reg) {
		this.reg = reg;
	}

	@Override
	public TokSeq toPrepend() {
		return SystemPrintHandler.PRINT_REF.toPrepend();
	}

	@Override
	public TokSeq toAppend() {
		return new TokSeq(SystemPrintHandler.PRINT_REF);
	}

	@Override
	public String toString() {
		return "MOV r0, " + reg.toString() + "\n"
				+"\t\tBL p_print_reference";
	}
}
