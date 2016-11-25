package frontend.expressions;

import frontend.type.BaseType;
import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import frontend.type.PairType;
import backend.Register;
import backend.TokenSequence;

/** Represents a declared pair
 * Contains type information for 1st and 2nd elements of pair
 * Contains identifier string
 */

public class PairLeaf extends ExprNode {

	private final int VAR_SIZE = 4;
	private BaseType fstType;
	private BaseType sndType;
	private StringLeaf ident;

	public PairLeaf(StringLeaf ident, BaseType fstType, BaseType sndType) {
		this.fstType = fstType;
		this.sndType = sndType;
	}

	@Override
	public boolean check(SymbolTable st, ParserRuleContext ctx) {
		return true;
	}

	@Override
	public BaseType getType() {
		return new PairType(fstType, sndType);
	}

	public StringLeaf getIdent() {
		return ident;
	}

	@Override
	public int weight() {
		return 1;
	}

	@Override
	public TokenSequence toAssembly(Register register) {
		return null;
	}


}
