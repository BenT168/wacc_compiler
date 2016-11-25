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
 * Class to represent if statements
 * Rule: 'if' expressions 'then' statements 'else' statements 'fi'
 *
 */

public class IfElseNode extends StatNode {

	private ExprNode ifCond;
	private StatNode thenStat;
	private StatNode elseStat;

	public IfElseNode(ExprNode expr, StatNode thenStat, StatNode elseStat) {
		this.ifCond = expr;
		this.thenStat = thenStat;
		this.elseStat = elseStat;
	}

	@Override
	public boolean check(SymbolTable st, ParserRuleContext ctx) {
		if (ifCond.getType() == BaseType.BOOL) {
			return true;
		} else {
			throw new SemanticErrorException("If statements should have an expressions of type BOOL", ctx);
		}
	}

	public TokenSequence toAssembly(Register register) {
		TokenSequence ifStat = ifCond.toAssembly(register);
		String l0 = "l" + Labeller.counter.getLabel();
		String l1 = "l" + Labeller.counter.getLabel();
		ifStat.appendAll(new TokenSequence(
				new CompareToken(register, "#0"),
				new BranchToken("EQ", l0)));
		ifStat.appendAll(thenStat.toAssembly(register));
		ifStat.appendAll(new TokenSequence(
				new BranchToken(l1),
				new LabelToken(l0)));
		ifStat.appendAll(elseStat.toAssembly(register));
		ifStat.append(new LabelToken(l1));
		return ifStat;
	}

}
