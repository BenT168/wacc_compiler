package frontEnd.tree.Assignment;

import org.antlr.v4.runtime.ParserRuleContext;

import symbolTable.SymbolTable;
import frontEnd.tree.Expr.Expr;
import frontEnd.tree.Type.PairType;
import frontEnd.tree.Type.BaseType;

public class NewPair extends Assignment {
	
	private Expr fst;
	private Expr snd;
	
	public NewPair(Expr fst, Expr snd) {
		this.fst = fst;
		this.snd = snd;
	}

	@Override
	public boolean check(SymbolTable st, ParserRuleContext ctx) {
		return true;
	}

	@Override
	public BaseType getType() {
		return new PairType(fst.getType(), snd.getType());
	}

}
