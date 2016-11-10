package frontEnd.tree.Assignment;

import frontEnd.ErrorHandling.InvalidTypeException;
import frontEnd.tree.Expr.Expr;
import frontEnd.tree.Type.BaseType;
import frontEnd.tree.Type.PairType;
import org.antlr.v4.runtime.ParserRuleContext;
import symbolTable.SymbolTable;

public class PairElem extends Assignment implements AssignLHS {
	public enum ORDER {
		FST("fst"), SND("snd");

		private String s;
		ORDER(String s) {
			this.s = s;
		}
		@Override
		public String toString() {
			return s;
		}
	}	

	private Expr expr;
	private ORDER order;


	//Expr here should be of type 'pairType'
	public PairElem(String fstOrSnd, Expr expr) {
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
	public boolean check(SymbolTable st, ParserRuleContext ctx ) {
		if (!(expr.getType() instanceof PairType)) {
			new InvalidTypeException("The `fst` and `snd` operators must take an expr of type PairType.\n"
					+ "Given: " + expr.getType().toString());
			return false;
		}
		return true;
	}

	public BaseType getType() {
		PairType p = (PairType) expr.getType();
		if (this.order == ORDER.FST) {
			return p.getFst();
		} else {
			return p.getSnd();
		}
	}

}
