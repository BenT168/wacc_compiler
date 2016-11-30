package frontend.expressions;

import backend.TokSeq;
import frontend.type.BaseType;
import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import backend.Register;
import backend.tokens.load.LoadToken;

public class PairLiter extends ExprNode {

	@Override
	public boolean check(SymbolTable st, ParserRuleContext ctx) {
		return true;
	}

	@Override
	public BaseType getType() {
		return BaseType.NULL;
	}

	@Override
	public int weight() {
		return 1;
	}

	@Override
	public TokSeq assemblyCodeGenerating(Register register) {
		return new TokSeq(
				new LoadToken(register, "0"));
	}


}
