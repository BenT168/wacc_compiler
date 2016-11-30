package backend.system;


import backend.Token;

public class SystemFormatterHandler {


	public static Token NEW_LINE_FORMATTER = new Token() {
		@Override
		public String toString() {
			return "msg_1:"
					+ "\n\t\t.word 1"
					+ "\n\t\t.ascii	\"\\0\"";
		}

		@Override
		public boolean requiresTab() {
			return false;
		}
	};

	public static Token BOOL_FORMATTER = new Token() {
		@Override
		public String toString() {
			return "msg_2:"
					+"\n\t\t.word 5"
					+"\n\t\t.ascii	\"true\\0\""
					+"\n\n\tmsg_3:"
					+"\n\t\t.word 6"
					+"\n\t\t.ascii	\"false\\0\"";
		}

		@Override
		public boolean requiresTab() {
			return false;
		}
	};

	public static Token INT_FORMATTER = new Token() {
		@Override
		public String toString() {
			return "msg_4:"
					+ "\n\t\t.word 3"
					+ "\n\t\t.ascii	\"%d\\0\"";
		}

		@Override
		public boolean requiresTab() {
			return false;
		}
	};

	public static Token CHAR_FORMATTER = new Token() {
		@Override
		public String toString() {
			return "msg_7:"
					+ "\n\t\t.word 4"
					+ "\n\t\t.ascii	\" %c\\0\"";
		}

		@Override
		public boolean requiresTab() {
			return false;
		}
	};

	public static Token STRING_FORMATTER = new Token() {
		@Override
		public String toString() {
			return "msg_0:"
					+ "\n\t\t.word 5"
					+ "\n\t\t.ascii	\"%.*s\\0\"";
		}

		@Override
		public boolean requiresTab() {
			return false;
		}
	};

	public static Token REFERENCE_FORMATTER = new Token() {
		@Override
		public String toString() {
			return "msg_9:"
						+ "\n\t\t.word 3"
						+ "\n\t\t.ascii	\"%p\\0\"";
		}
		@Override
		public boolean requiresTab() {
			return false;
		}
	};
}
