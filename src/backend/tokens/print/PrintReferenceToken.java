package backend.tokens.print;

import backend.Token;
import backend.Register;
import backend.TokenSequence;
import backend.system.SystemPrintHandler;

public class PrintReferenceToken extends Token {

	private Register reg;

	public PrintReferenceToken(Register reg) {
		this.reg = reg;
	}

	@Override
	public TokenSequence toPrepend() {
		return SystemPrintHandler.PRINT_REF.toPrepend();
	}

	@Override
	public TokenSequence toAppend() {
		return new TokenSequence(SystemPrintHandler.PRINT_REF);
	}

	@Override
	public String toString() {
		return "MOV r0, " + reg.toString() + "\n"
				+"\t\tBL p_print_reference";
	}
}
