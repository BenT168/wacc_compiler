package frontend.stat;

import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import frontend.expr.ExprNode;
import frontend.type.WACCType;
import WACCExceptions.InvalidTypeException;
import backend.ImmValue;
import backend.LabelCounter;
import backend.Register;
import backend.TokenSequence;
import backend.tokens.branch.BranchToken;
import backend.tokens.operator.CompareToken;
import backend.tokens.general.LabelToken;

/**
 * Class to represent if statements 
 * Rule: 'if' expr 'then' stat 'else' stat 'fi'
 *
 */

public class IfStatNode extends StatNode {
	
	private ExprNode ifCond;
	private StatNode thenStat;
	private StatNode elseStat;
	
	public IfStatNode(ExprNode expr, StatNode thenStat, StatNode elseStat) {
		this.ifCond = expr;
		this.thenStat = thenStat;
		this.elseStat = elseStat;
	}
	
	@Override
	public boolean check(SymbolTable st, ParserRuleContext ctx) {
		if (ifCond.getType() == WACCType.BOOL) {
			return true;
		} else {
			new InvalidTypeException("If statements should have an expr of type BOOL", ctx);
		    return false;
		}
	}
	 
	public TokenSequence toAssembly(Register register) {
		TokenSequence ifStat = ifCond.toAssembly(register);
		String l0 = "l" + LabelCounter.counter.getLabel();
		String l1 = "l" + LabelCounter.counter.getLabel();		
		ifStat.appendAll(new TokenSequence(
				new CompareToken(register, ImmValue.zero),
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
