package backend.system;

import backend.Token;
import backend.TokenSequence;

public class SystemReadHandler {
	public static Token READ_INT = new Token() {

		public TokenSequence toPrepend() {
			return new TokenSequence(SystemFormatterHandler.INT_FORMATTER);
		}

		@Override
		public String toString() {
			return "p_read_int:"
					+ "\n\t\tPUSH {lr}"
					+ "\n\t\tMOV r1, r0"
					+ "\n\t\tLDR r0, =msg_4"
					+ "\n\t\tADD r0, r0, #4"
					+ "\n\t\tBL scanf"
					+ "\n\t\tPOP {pc}";
		}

		@Override
		public boolean requiresTab() {
			return false;
		}
	};

	public static Token READ_CHAR = new Token() {

		public TokenSequence toPrepend() {
			return new TokenSequence(SystemFormatterHandler.CHAR_FORMATTER);
		}

		@Override
		public String toString() {
			return "p_read_char:"
					+ "\n\t\tPUSH {lr}"
					+ "\n\t\tMOV r1, r0"
					+ "\n\t\tLDR r0, =msg_7"
					+ "\n\t\tADD r0, r0, #4"
					+ "\n\t\tBL scanf"
					+ "\n\t\tPOP {pc}";
		}

		@Override
		public boolean requiresTab() {
			return false;
		}
	};
}
