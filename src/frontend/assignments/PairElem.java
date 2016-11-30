package frontend.assignments;

import backend.Register;
import backend.TokSeq;
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
	public TokSeq assemblyCodeStoring(Register dest) {
		TokSeq exprSeq = expr.assemblyCodeGenerating(dest.getNext())
				.append(new MovRegToken(Register.R0, dest.getNext()))
				.append(new CheckNullPointerToken());

		return exprSeq.appendAll(
				new LoadAddressToken(dest.getNext(), dest.getNext(), order.getOffset()),
				new StoreToken(dest.getNext(), dest));
	}

	@Override
	public TokSeq assemblyCodeGenerating(Register register) {

		TokSeq exprSeq =
				expr.assemblyCodeGenerating(register)
				.append(new MovRegToken(Register.R0, register))
				.append(new CheckNullPointerToken());


		return exprSeq.appendAll(
				new LoadAddressToken(register, register, order.getOffset()),
				new LoadAddressToken(register, register));
	}

	@Override
	public TokSeq loadAddr(Register dest) {
		TokSeq seq = expr.assemblyCodeGenerating(dest);
		seq.append(new LoadAddressToken(dest, dest, order.offset));
		return seq;
	}

}
