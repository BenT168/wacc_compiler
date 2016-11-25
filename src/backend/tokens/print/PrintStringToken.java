package backend.tokens.print;

import backend.Token;
import backend.Register;
import backend.TokenSequence;
import backend.system.SystemPrintHandler;

public class PrintStringToken extends Token {

	private Register r;

	public PrintStringToken(Register r) {
		this.r = r;
		this.addRegister(r);
	}

	@Override
	public TokenSequence toPrepend() {
		return SystemPrintHandler.PRINT_STRING.toPrepend();
	}

	@Override
	public TokenSequence toAppend() {
		return new TokenSequence(SystemPrintHandler.PRINT_STRING);
	}

	@Override
	public String toString() {
		return "MOV r0, " + r.toString() + "\n"
				+ "\t\tBL p_print_string";
	}

}
