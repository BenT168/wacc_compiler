package backend.system;

import backend.TokSeq;
import backend.Token;

public class SystemPrintHandler {
public static Token PRINT_STRING = new Token() {

		public TokSeq toPrepend() {
			return new TokSeq(SystemFormatterHandler.STRING_FORMATTER);
		}

		@Override
		public String toString() {
			return	"p_print_string:"
						+"\n\t\tPUSH {lr}"
						+"\n\t\tLDR r1, [r0]"
						+"\n\t\tADD r2, r0, #4"
						+"\n\t\tLDR r0, =msg_0"
						+"\n\t\tADD r0, r0, #4"
						+"\n\t\tBL printf"
						+"\n\t\tMOV r0, #0"
						+"\n\t\tBL fflush"
						+"\n\t\tPOP {pc}";
		}

		@Override
		public boolean requiresTab() {
			return false;
		}
	};

	public static Token PRINT_LN = new Token() {

		public TokSeq toPrepend() {
			return new TokSeq(SystemFormatterHandler.NEW_LINE_FORMATTER);
		}

		@Override
		public String toString() {
			return	"p_print_ln:"
						+"\n\t\tPUSH {lr}"
						+"\n\t\tLDR r0, =msg_1"
						+"\n\t\tADD r0, r0, #4"
						+"\n\t\tBL puts"
						+"\n\t\tMOV r0, #0"
						+"\n\t\tBL fflush"
						+"\n\t\tPOP {pc}";
		}

		@Override
		public boolean requiresTab() {
			return false;
		}
	};

	public static Token PRINT_BOOL = new Token() {

		public TokSeq toPrepend() {
			return new TokSeq(SystemFormatterHandler.BOOL_FORMATTER);
		}

		@Override
		public String toString() {
			return	"p_print_bool:"
						+"\n\t\tPUSH {lr}"
						+"\n\t\tCMP r0, #0"
						+"\n\t\tLDRNE r0, =msg_2"
						+"\n\t\tLDREQ r0, =msg_3"
						+"\n\t\tADD r0, r0, #4"
						+"\n\t\tBL printf"
						+"\n\t\tMOV r0, #0"
						+"\n\t\tBL fflush"
						+"\n\t\tPOP {pc}";
		}

		@Override
		public boolean requiresTab() {
			return false;
		}
	};

	public static Token PRINT_INT = new Token() {

		public TokSeq toPrepend() {
			return new TokSeq(SystemFormatterHandler.INT_FORMATTER);
		}

		@Override
		public String toString() {
			return	"p_print_int:"
						+ "\n\t\tPUSH {lr}"
						+ "\n\t\tMOV r1, r0"
						+ "\n\t\tLDR r0, =msg_4"
						+ "\n\t\tADD r0, r0, #4"
						+ "\n\t\tBL printf"
						+ "\n\t\tMOV r0, #0"
						+ "\n\t\tBL fflush"
						+ "\n\t\tPOP {pc}";
		}

		@Override
		public boolean requiresTab() {
			return false;
		}
	};

	public static Token PRINT_REF = new Token() {
		public TokSeq toPrepend() {
			return new TokSeq(SystemFormatterHandler.REFERENCE_FORMATTER);
		}

		@Override
		public String toString() {
			return "p_print_reference:"
						+ "\n\t\tPUSH {lr}"
						+ "\n\t\tMOV r1, r0"
						+ "\n\t\tLDR r0, =msg_9"
						+ "\n\t\tADD r0, r0, #4"
						+ "\n\t\tBL printf"
						+ "\n\t\tMOV r0, #0"
						+ "\n\t\tBL fflush"
						+ "\n\t\tPOP {pc}";
		}

		@Override
		public boolean requiresTab() {
			return false;
		}
	};
}
