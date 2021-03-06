package backend.system;

import backend.Token;

public class SystemFreeHandler {
	public static Token FREE_PAIR = new Token() {

		@Override
		public String toString() {
			return "p_free_pair:"
					   + "\n\t\tPUSH {lr}"
					   + "\n\t\tCMP r0, #0"
					   + "\n\t\tLDREQ r0, =msg_8"
					   + "\n\t\tBEQ p_throw_runtime_error"
					   + "\n\t\tPUSH {r0}"
					   + "\n\t\tLDR r0, [r0]"
					   + "\n\t\tBL free"
					   + "\n\t\tLDR r0, [sp]"
					   + "\n\t\tLDR r0, [r0, #4]"
					   + "\n\t\tBL free"
					   + "\n\t\tPOP {r0}"
					   + "\n\t\tBL free"
					   + "\n\t\tPOP {pc}";
		}

		@Override
		public boolean requiresTab() {
			return false;
		}
	};

	public static Token FREE_ARRAY = new Token() {

		@Override
		public String toString() {
			return "p_free_array:"
						+ "\n\t\tPUSH {lr}"
						+ "\n\t\tCMP r0, #0"
						+ "\n\t\tLDREQ r0, =msg_8"
						+ "\n\t\tBEQ p_throw_runtime_error"
						+ "\n\t\tBL free"
						+ "\n\t\tPOP {pc}";
		}

		@Override
		public boolean requiresTab() {
			return false;
		}
	};
}
