package frontend.assignments;

import backend.Register;
import backend.TokenSequence;
import backend.tokens.check.CheckNullPointerToken;
import backend.tokens.store.StoreToken;
import backend.tokens.load.LoadAddressToken;
import backend.tokens.move.MovRegToken;
import frontend.exception.SemanticErrorException;
import frontend.expressions.ExprNode;
import frontend.type.PairType;
import frontend.type.BaseType;
import org.antlr.v4.runtime.ParserRuleContext;
import symboltable.SymbolTable;

/*
 *
 */
public class PairElem extends Assignable implements AssignLHS {

	public enum ORDER {
		FST("fst", 0), SND("snd", 4);

		private String s;
		private int offset;

		ORDER(String s, int i) {
			this.s = s;
			this.offset = i;
		}

		private int getOffset() {
			return offset;
		}
		@Override
		public String toString() {
			return s;
		}
	}

	private ExprNode expr;
	private ORDER order;

	//Expr here should be of type 'pairType'
	public PairElem(String fstOrSnd, ExprNode expr) {
		this.expr = expr;
		if (fstOrSnd.equals("fst")) {
			this.order = ORDER.FST;
		} else if (fstOrSnd.equals("snd")) {
			this.order = ORDER.SND;
		} else {
			throw new IllegalArgumentException("The `fstOrSnd` string must be either `fst` or `snd`.\n"
					+ "Given: " + fstOrSnd);
		}
	}

	@Override
	public boolean check( SymbolTable st, ParserRuleContext ctx ) {
		if (!(expr.getType() instanceof PairType)) {
			throw new SemanticErrorException("The `fst` and `snd` operators must take an expressions of type PairType.\n"
					+ "Given: " + expr.getType().toString());
		}
		return true;
	}

	@Override
	public BaseType getType() {
		PairType p = (PairType) expr.getType();
		if (this.order == ORDER.FST) {
			return p.getFstType();
		} else {
			return p.getSndType();
		}
	}

	@Override
	public TokenSequence toStoreAssembly(Register dest) {
		TokenSequence exprSeq = expr.toAssembly(dest.getNext())
				.append(new MovRegToken(Register.R0, dest.getNext()))
				.append(new CheckNullPointerToken());

		return exprSeq.appendAll(
				new LoadAddressToken(dest.getNext(), dest.getNext(), order.getOffset()),
				new StoreToken(dest.getNext(), dest));
	}

	@Override
	public TokenSequence toAssembly(Register register) {

		TokenSequence exprSeq =
				expr.toAssembly(register)
				.append(new MovRegToken(Register.R0, register))
				.append(new CheckNullPointerToken());


		return exprSeq.appendAll(
				new LoadAddressToken(register, register, order.getOffset()),
				new LoadAddressToken(register, register));
	}

	@Override
	public TokenSequence loadAddress(Register dest) {
		TokenSequence seq = expr.toAssembly(dest);
		seq.append(new LoadAddressToken(dest, dest, order.offset));
		return seq;
	}

}
