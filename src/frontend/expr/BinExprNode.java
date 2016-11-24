package frontend.expr;

import backend.Register;
import backend.TokenSequence;
import frontend.exception.ThrowException;
import frontend.type.WACCBinOp;
import frontend.type.WACCType;
import org.antlr.v4.runtime.ParserRuleContext;
import symboltable.SymbolTable;

/* Represents a Binary Operator expression
 * Holds the operator and the expressions
 * Checks expression types are compatible with the operator
 * Rule: expr binary-oper expr
 * Where binary-oper is '*'|'/'|'%'|'+'|'-'|'>'|'>='|'<'|'<='|'=='|'!='|'&&'|'||'
 */

public class BinExprNode extends ExprNode {
	
	private ExprNode lhs;
	private WACCBinOp operator;
	private ExprNode rhs;

	public BinExprNode(ExprNode lhs, WACCBinOp operator, ExprNode rhs) {
		this.lhs = lhs;
		this.operator = operator;
		this.rhs = rhs;
	}

	@Override
	public boolean check(SymbolTable st, ParserRuleContext ctx) {
		//Getting line and column number for error message
		int line = ctx.start.getLine();
		int column = ctx.start.getCharPositionInLine();
		if (!operator.check(lhs, rhs)) {
				//Throw Semantic Error
			String msg = "Expected arguments to be the same type in Binary Operator\n "+
					"Actual type : " + lhs.toString() + ", " + rhs.toString();
			ThrowException.callSemanticException(line, column, msg);
			return false;
		}
		return true;
	}

	@Override
	public WACCType getType() {
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
		if (operator == WACCBinOp.ADD || operator == WACCBinOp.MUL) {
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
