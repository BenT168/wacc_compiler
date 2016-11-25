package frontend.type;

import backend.Token;
import frontend.expressions.ExprNode;
import backend.Register;
import backend.TokenSequence;
import backend.tokens.operator.EorToken;
import backend.tokens.load.LoadAddressToken;
import backend.tokens.check.OverflowToken;

public abstract class UnaryOperators {

	public abstract boolean check(ExprNode e);
	public abstract BaseType getType();
	public abstract TokenSequence apply(Register register);

	/*
	 * Utility method to parse a unary operator.
	 */
	public static UnaryOperators evalUnOp(String opString) {
		switch (opString) {
		case "!":
			return NOT;
		case "-":
			return NEG;
		case "len":
			return LEN;
		case "ord":
			return ORD;
		case "chr":
			return CHR;

		default:
			throw new IllegalArgumentException("The StringLeaf provided " + opString + " did not match any unary operators.");
		}
	}

	/*
	 * Static final instances of the WACC Unary Operators:
	 * NOT, NEG, LEN, ORD, CHR
	 */
	public static final UnaryOperators NOT = new UnaryOperators() {

		@Override
		public BaseType getType() {
			return BaseType.BOOL;
		}

		@Override
		public boolean check(ExprNode e) {
			return e.getType() == BaseType.BOOL;
		}

		@Override
		public String toString() {
			return "NOT";
		}

		@Override
		public TokenSequence apply(Register register) {
			return new TokenSequence(
				new EorToken(register, register, "#1")
			);
		}
	};

	public static final UnaryOperators NEG = new UnaryOperators() {

		@Override
		public BaseType getType() {
			return BaseType.INT;
		}

		@Override
		public boolean check(ExprNode e) {
			return e.getType() == BaseType.INT;
		}

		@Override
		public String toString() {
			return "NEG";
		}

		@Override
		public TokenSequence apply(final Register register) {
			return new TokenSequence(
					new Token() {
						@Override
						public String toString() {
							return "RSB " + register.toString() + ", " + register.toString() + ", #0";
						}
					},
					new OverflowToken("VS")
			);
		}
	};

	public static final UnaryOperators LEN = new UnaryOperators() {

		@Override
		public BaseType getType() {
			return BaseType.INT;
		}

		@Override
		public boolean check(ExprNode e) {
			return e.getType() instanceof ArrayType;
		}

		@Override
		public String toString() {
			return "LEN";
		}

		@Override
		public TokenSequence apply(Register register) {
			return new TokenSequence(new LoadAddressToken(register, register));
		}
	};

	public static final UnaryOperators ORD = new UnaryOperators() {

		@Override
		public BaseType getType() {
			return BaseType.INT;
		}

		@Override
		public boolean check(ExprNode e) {
			return e.getType() == BaseType.CHAR;
		}

		@Override
		public String toString() {
			return "ORD";
		}

		@Override
		public TokenSequence apply(Register register) {
			return new TokenSequence();
		}
	};

	public static final UnaryOperators CHR = new UnaryOperators() {

		@Override
		public BaseType getType() {
			return BaseType.CHAR;
		}

		@Override
		public boolean check(ExprNode e) {
			return e.getType() == BaseType.INT;
		}

		@Override
		public String toString() {
			return "CHR";
		}

		@Override
		public TokenSequence apply(Register register) {
			return new TokenSequence();
		}
	};
}
