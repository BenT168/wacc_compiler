package frontend.expressions;

import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import frontend.type.BaseType;
import backend.Register;
import backend.TokSeq;
import backend.tokens.load.LoadStringToken;

public class StringLeaf extends ExprNode {

	private java.lang.String text;

	public StringLeaf(java.lang.String text) {
		this.text = text;
	}

	@Override
	public boolean check(SymbolTable st, ParserRuleContext ctx) {
		return true;
	}

	@Override
	public BaseType getType() {
		return BaseType.STRING;
	}

	@Override
	public int weight() {
		return 1;
	}

	@Override
	public java.lang.String toString() {
		return text;
	}

	public TokSeq assemblyCodeGenerating(Register r) {
		return new TokSeq(new LoadStringToken(r, text));
	}


}
