package frontend.statements;

import backend.Labeller;
import backend.Register;
import backend.TokenSequence;
import backend.tokens.branch.BranchToken;
import backend.tokens.general.LabelToken;
import backend.tokens.operator.CompareToken;
import frontend.exception.SemanticErrorException;
import frontend.expressions.ExprNode;
import frontend.type.BaseType;
import org.antlr.v4.runtime.ParserRuleContext;
import symboltable.SymbolTable;

/**
 * Class to represent while statements.
 * Rule: 'while' expressions 'do' statements 'done'
 *
 */

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

	public TokenSequence toAssembly(Register register) {
		String l0 = "l" + Labeller.counter.getLabel();
		String l1 = "l" + Labeller.counter.getLabel();
		TokenSequence whileStat = new TokenSequence(
				new BranchToken(l0),
				new LabelToken(l1));
		whileStat.appendAll(loopBody.toAssembly(register));
		whileStat.append(
				new LabelToken(l0));
		whileStat.appendAll(loopCond.toAssembly(register));
		whileStat.appendAll(new TokenSequence(
				new CompareToken(register, "#1"),
				new BranchToken("EQ", l1)));
		return whileStat;
	}

}
