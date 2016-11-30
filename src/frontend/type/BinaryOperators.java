package frontend.type;

import backend.TokSeq;
import backend.Token;
import frontend.expressions.ExprNode;
import backend.Register;
import backend.tokens.operator.AddToken;
import backend.tokens.operator.AndToken;
import backend.tokens.branch.BranchLinkToken;
import backend.tokens.operator.CompareToken;
import backend.tokens.operator.DivideByZeroErrorToken;
import backend.tokens.move.MovImmToken;
import backend.tokens.move.MovRegToken;
import backend.tokens.operator.MultiplySignedLongToken;
import backend.tokens.operator.OrToken;
import backend.tokens.check.OverflowToken;
import backend.tokens.print.PrintBoolToken;
import backend.tokens.print.PrintIntToken;
import backend.tokens.operator.SubToken;

public abstract class BinaryOperators {

	public BinaryOperators() {}

	public abstract boolean check(ExprNode lhs, ExprNode rhs);

	public abstract BaseType getType();

	public abstract TokSeq apply(Register lhs, Register rhs);

	public abstract TokSeq print(Register r);

	/*
	 * There are only 4 types of logic to check that `rhs BinOp lhs` is correct:
	 * 1 - lhs and rhs must be INT  (Arithmetic BinOps)
	 * 2 - lhs and rhs must be of equal type, which must be either INT or CHAR (>=, >, <, <=)
	 * 3 - always correct (== and !=)
	 * 4 - lhs and rhs must be BOOL (&& and ||)
	 */

	// 1:
	public static final BinaryOperators MUL = new ArithBinaryOperator() {

		@Override
		public TokSeq apply(Register lhs, Register rhs) {
			Token mul = new MultiplySignedLongToken(lhs, rhs, lhs, rhs);
			Token cmp = new CompareToken(rhs, lhs, "ASR #31");
			Token overflow = new OverflowToken("NE");
			return new TokSeq(mul, cmp, overflow);
		}

		@Override
		public TokSeq print(Register r) {
			return new TokSeq(new PrintIntToken(r));
		}
	};
	public static final BinaryOperators DIV = new ArithBinaryOperator() {

		@Override
		public TokSeq apply(Register lhs, Register rhs) {
			Token op1 = new MovRegToken(Register.R0, lhs);
			Token op2 = new MovRegToken(Register.R1, rhs);
			Token divZeroError = new DivideByZeroErrorToken();
			Token div = new BranchLinkToken("__aeabi_idiv");
			Token mov = new MovRegToken(lhs, Register.R0);
			TokSeq seq = new TokSeq(op1, op2, divZeroError, div, mov);
			return seq;
		}

		@Override
		public TokSeq print(Register r) {
			return new TokSeq(new PrintIntToken(r));
		}

	};
	public static final BinaryOperators MOD = new ArithBinaryOperator() {

		@Override
		public TokSeq apply(Register lhs, Register rhs) {
			Token op1 = new MovRegToken(Register.R0, lhs);
			Token op2 = new MovRegToken(Register.R1, rhs);
			Token divZeroError = new DivideByZeroErrorToken();
			Token div = new BranchLinkToken("__aeabi_idivmod");
			Token mov = new MovRegToken(lhs, Register.R1);
			return new TokSeq(op1, op2, divZeroError, div, mov);
		}

		@Override
		public TokSeq print(Register r) {
			return new TokSeq(new PrintIntToken(r));
		}

	};
	public static final BinaryOperators ADD = new ArithBinaryOperator() {

		@Override
		public TokSeq apply(Register lhs, Register rhs) {
			Token add = new AddToken("S", lhs, lhs, rhs);
			Token overflow = new OverflowToken("VS");
			return new TokSeq(add, overflow);
		}

		@Override
		public TokSeq print(Register r) {
			return new TokSeq(new PrintIntToken(r));
		}

	};
	public static final BinaryOperators SUB = new ArithBinaryOperator() {

		@Override
		public TokSeq apply(Register lhs, Register rhs) {
			Token sub = new SubToken("S", lhs, lhs, rhs);
			Token overflow = new OverflowToken("VS");
			return new TokSeq(sub, overflow);
		}

		@Override
		public TokSeq print(Register r) {
			return new TokSeq(new PrintIntToken(r));
		}

	};

	// 2:
	public static final BinaryOperators GRT = new CompBinaryOperator() {

		@Override
		public TokSeq apply(Register lhs, Register rhs) {
			Token cmp = new CompareToken(lhs, rhs);
			Token gt = new MovImmToken("GT", lhs, "1");
			Token leq = new MovImmToken("LE", lhs, "0");
			return new TokSeq(cmp, gt, leq);
		}

		@Override
		public TokSeq print(Register r) {
			return new TokSeq(new PrintBoolToken(r));
		}

	};
	public static final BinaryOperators GRT_EQ = new CompBinaryOperator() {

		@Override
		public TokSeq apply(Register lhs, Register rhs) {
			Token cmp = new CompareToken(lhs, rhs);
			Token geq = new MovImmToken("GE", lhs, "1");
			Token lt = new MovImmToken("LT", lhs, "0");
			return new TokSeq(cmp, geq, lt);
		}

		@Override
		public TokSeq print(Register r) {
			return new TokSeq(new PrintBoolToken(r));
		}

	};
	public static final BinaryOperators LESS = new CompBinaryOperator() {

		@Override
		public TokSeq apply(Register lhs, Register rhs) {
			Token cmp = new CompareToken(lhs, rhs);
			Token lt = new MovImmToken("LT", lhs, "1");
			Token geq = new MovImmToken("GE", lhs, "0");
			return new TokSeq(cmp, lt, geq);
		}

		@Override
		public TokSeq print(Register r) {
			return new TokSeq(new PrintBoolToken(r));
		}

	};
	public static final BinaryOperators LESS_EQ = new CompBinaryOperator() {

		@Override
		public TokSeq apply(Register lhs, Register rhs) {
			Token cmp = new CompareToken(lhs, rhs);
			Token leq = new MovImmToken("LE", lhs, "1");
			Token gt = new MovImmToken("GT", lhs, "0");
			return new TokSeq(cmp, leq, gt);
		}

		@Override
		public TokSeq print(Register r) {
			return new TokSeq(new PrintBoolToken(r));
		}

	};

	// 3:
	public static final BinaryOperators EQ = new EqualBinaryOperator() {

		@Override
		public TokSeq apply(Register lhs, Register rhs) {
			Token cmp = new CompareToken(lhs, rhs);
			Token eq = new MovImmToken("EQ", lhs, "1");
			Token neq = new MovImmToken("NE", lhs, "0");
			return new TokSeq(cmp, eq, neq);
		}

		@Override
		public TokSeq print(Register r) {
			return new TokSeq(new PrintBoolToken(r));
		}

	};
	public static final BinaryOperators NOT_EQ = new EqualBinaryOperator() {

		@Override
		public TokSeq apply(Register lhs, Register rhs) {
			Token cmp = new CompareToken(lhs, rhs);
			Token neq = new MovImmToken("NE", lhs, "1");
			Token eq = new MovImmToken("EQ", lhs, "0");
			return new TokSeq(cmp, neq, eq);
		}

		@Override
		public TokSeq print(Register r) {
			return new TokSeq(new PrintBoolToken(r));
		}

	};

	// 4:
	public static final BinaryOperators AND = new BoolBinaryOperator() {

		@Override
		public TokSeq apply(Register lhs, Register rhs) {
			return new TokSeq(new AndToken(lhs, lhs, rhs));
		}

		@Override
		public TokSeq print(Register r) {
			return new TokSeq(new PrintBoolToken(r));
		}

	};
	public static final BinaryOperators OR = new BoolBinaryOperator(){

		@Override
		public TokSeq apply(Register lhs, Register rhs) {
			return new TokSeq(new OrToken(lhs, lhs, rhs));
		}

		@Override
		public TokSeq print(Register r) {
			return new TokSeq(new PrintBoolToken(r));
		}

	};

	/*
	 * Utility method to convert a StringLeaf to a Binary Operator
	 */
	public static BinaryOperators evalBinOp(String operator) {
		switch (operator) {
		// 1:
		case "*":
			return MUL;
		case "/":
			return DIV;
		case "%":
			return MOD;
		case "+":
			return ADD;
		case "-":
			return SUB;
		// 2:
		case ">":
			return GRT;
		case ">=":
			return GRT_EQ;
		case "<":
			return LESS;
		case "<=":
			return LESS_EQ;
		// 3:
		case "==":
			return EQ;
		case "!=":
			return NOT_EQ;
		// 4:
		case "&&":
			return AND;
		case "||":
			return OR;

		default:
			throw new IllegalArgumentException("The provided StringLeaf does not match any operators: " + operator);
		}
	}

}
