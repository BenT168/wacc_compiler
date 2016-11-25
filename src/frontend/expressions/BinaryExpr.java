package frontend.expressions;

import backend.Register;
import backend.TokenSequence;
import frontend.exception.SemanticErrorException;
import frontend.type.BinaryOperators;
import frontend.type.BaseType;
import org.antlr.v4.runtime.ParserRuleContext;
import symboltable.SymbolTable;

/* Represents a Binary Operator expression
 * Holds the operator and the expressions
 * Checks expression types are compatible with the operator
 * Rule: expressions binary-oper expressions
 * Where binary-oper is '*'|'/'|'%'|'+'|'-'|'>'|'>='|'<'|'<='|'=='|'!='|'&&'|'||'
 */

public class BinaryExpr extends ExprNode {

	private ExprNode lhs;
	private BinaryOperators operator;
	private ExprNode rhs;

	public BinaryExpr(ExprNode lhs, BinaryOperators operator, ExprNode rhs) {
		this.lhs = lhs;
		this.operator = operator;
		this.rhs = rhs;
	}

	@Override
	public boolean check(SymbolTable st, ParserRuleContext ctx) {
		if (!operator.check(lhs, rhs)) {
			throw new SemanticErrorException("The types in the Binary expression are not compatible.", ctx);
		}
		return true;
	}

	@Override
	public BaseType getType() {
		return operator.getType();
	}

	@Override
	public int weight() {
		return Math.min(
				Math.max(lhs.weight() + 1, rhs.weight()),
				Math.max(lhs.weight(), rhs.weight() + 1)
		);
	}

	@Override
	public TokenSequence toAssembly(Register r) {
		if (operator == BinaryOperators.ADD || operator == BinaryOperators.MUL) {
			if (lhs.weight() > rhs.weight()) {
				TokenSequence exprs = lhs.toAssembly(r);
				exprs.appendAll(rhs.toAssembly(r.getNext()));
				exprs.appendAll(operator.apply(r, r.getNext()));
				return exprs;
			} else {
				TokenSequence exprs = rhs.toAssembly(r);
				exprs.appendAll(lhs.toAssembly(r.getNext()));
				exprs.appendAll(operator.apply(r, r.getNext()));
				return exprs;
			}
		} else {
			TokenSequence exprs = lhs.toAssembly(r);
			exprs.appendAll(rhs.toAssembly(r.getNext()));
			exprs.appendAll(operator.apply(r, r.getNext()));
			return exprs;
		}
	}

}
