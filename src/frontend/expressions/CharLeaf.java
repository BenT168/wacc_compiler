package frontend.expressions;

import backend.Token;
import frontend.exception.SemanticErrorException;
import frontend.type.BaseType;
import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import backend.Register;
import backend.TokSeq;
import backend.tokens.move.MovImmToken;

public class CharLeaf extends ExprNode {

	private String text;

	public CharLeaf(String text) {
		this.text = text;
	}

	@Override
	public boolean check( SymbolTable st, ParserRuleContext ctx ) {
		return true;
	}

	@Override
	public BaseType getType() {
		return BaseType.CHAR;
	}

	@Override
	public int weight() {
		return 1;
	}

	@Override
	public TokSeq assemblyCodeGenerating(Register register) {
		try {
			int value = EscapedChar.match(text);
			return new TokSeq(new MovImmToken(register, String.valueOf(value)));
		} catch (Exception e) {
			Token tok = new MovImmToken(register, text);
			return new TokSeq(tok);
		}
	}


	public enum EscapedChar {
		END_OF_STRING("\\0", '\0'),
		NEWLINE("\\n", '\n'),
		TAB("\\t", '\t'),
		CARRIAGE_RETURN("\\r", '\r'),
		FORM_FEED("\\f", '\f'),
		DOUBLE_QUOTES("\\\"", '"'),
		BACKSLASH("\\", '\\'),
		WHITESPACE(" ", ' '),
		APOSTROPHE("'", '\'');

		private String s;
		private char c;

		private EscapedChar(String s, char c) {
			this.s = s;
			this.c = c;
		}

		public int toInt() {
			return (int) c;
		}

		public static int match(String s) throws Exception {
			String unwrapped = unwrap(s);
			for (EscapedChar c:EscapedChar.values()) {
				if (c.getStr().equals(unwrapped))
					return c.toInt();
			}
			throw new SemanticErrorException("The character was not found.");
		}

		private static String unwrap(String w) {
			return w.substring(1, w.length() - 1);
		}

		private String getStr() {
			return s;
		}
	}

}
