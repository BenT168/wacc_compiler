package backend.tokens.print;

import backend.system.SystemPrintHandler;
import backend.Token;
import backend.Register;
import backend.TokenSequence;

public class PrintBoolToken extends Token {

	private Register r;

	public PrintBoolToken(Register r) {
		this.r = r;
		this.addRegister(r);
	}

	@Override
	public TokenSequence toPrepend() {
		return SystemPrintHandler.PRINT_BOOL.toPrepend();
	}

	@Override
	public TokenSequence toAppend() {
		return new TokenSequence(SystemPrintHandler.PRINT_BOOL);
	}

	@Override
	public String toString() {
		return "MOV r0, " + r.toString() + "\n"
				+ "\t\tBL p_print_bool";
	}

}
