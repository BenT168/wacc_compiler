package frontend.statements;

import backend.Labeller;
import backend.Register;
import backend.TokSeq;
import backend.tokens.branch.BranchToken;
import backend.tokens.general.LabelToken;
import backend.tokens.operator.CompareToken;
import frontend.exception.SemanticErrorException;
import frontend.expressions.ExprNode;
import frontend.type.BaseType;
import org.antlr.v4.runtime.ParserRuleContext;
import symboltable.SymbolTable;

public class WhileNode extends StatNode {

	private ExprNode loopCond;
	private StatNode loopBody;

	public WhileNode(ExprNode expr, StatNode stat) {
		this.loopCond = expr;
		this.loopBody = stat;
	}

	@Override
	public boolean check(SymbolTable st, ParserRuleContext ctx) {
		if (loopCond.getType() == BaseType.BOOL) {
			return true;
		} else {
			throw new SemanticErrorException("While statement should have an expressions of type BOOL", ctx);
		}
	}

	public TokSeq assemblyCodeGenerating(Register register) {
		String l0 = "l" + Labeller.counter.getLabel();
		String l1 = "l" + Labeller.counter.getLabel();
		TokSeq whileStat = new TokSeq(
				new BranchToken(l0),
				new LabelToken(l1));
		whileStat.appendAll(loopBody.assemblyCodeGenerating(register));
		whileStat.append(
				new LabelToken(l0));
		whileStat.appendAll(loopCond.assemblyCodeGenerating(register));
		whileStat.appendAll(new TokSeq(
				new CompareToken(register, "#1"),
				new BranchToken("EQ", l1)));
		return whileStat;
	}

}
