package frontend.type;

import backend.TokSeq;
import backend.Token;
import frontend.expressions.ExprNode;
import backend.Register;
import backend.tokens.operator.EorToken;
import backend.tokens.load.LoadAddressToken;
import backend.tokens.check.OverflowToken;

public abstract class UnaryOperators {

	public abstract boolean check(ExprNode e);
	public abstract BaseType getType();
	public abstract TokSeq apply(Register register);


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
		public TokSeq apply(Register register) {
			return new TokSeq(
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
		public TokSeq apply(final Register register) {
			return new TokSeq(
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
		public TokSeq apply(Register register) {
			return new TokSeq(new LoadAddressToken(register, register));
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
		public TokSeq apply(Register register) {
			return new TokSeq();
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
		public TokSeq apply(Register register) {
			return new TokSeq();
		}
	};
}
