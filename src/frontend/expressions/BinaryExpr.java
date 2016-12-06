package frontend.expressions;

import backend.Register;
import backend.TokSeq;
import frontend.exception.SemanticErrorException;
import frontend.statements.ex_ForLoopNode;
import frontend.type.BinaryOperators;
import frontend.type.BaseType;
import org.antlr.v4.runtime.ParserRuleContext;
import symboltable.SymbolTable;

public class BinaryExpr extends ExprNode {

	private ExprNode lhs;
	private BinaryOperators operator;
	private ExprNode rhs;

	/* booleans to set branching statements in forloop */
	public static boolean inLoopGT = false;
	public static boolean inLoopLT = false;
	public static boolean inLoopGTE = false;
	public static boolean inLoopLTE =  false;

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
	public TokSeq assemblyCodeGenerating(Register r) {
		if (operator == BinaryOperators.ADD || operator == BinaryOperators.MUL) {
			if (lhs.weight() > rhs.weight()) {
				TokSeq exprs = lhs.assemblyCodeGenerating(r);
				exprs.appendAll(rhs.assemblyCodeGenerating(r.getNext()));
				exprs.appendAll(operator.apply(r, r.getNext()));
				return exprs;
			} else {
				TokSeq exprs = rhs.assemblyCodeGenerating(r);
				exprs.appendAll(lhs.assemblyCodeGenerating(r.getNext()));
				exprs.appendAll(operator.apply(r, r.getNext()));
				return exprs;
			}
		} else if(ex_ForLoopNode.isInLoopAssembler()) {
			TokSeq exprs = rhs.assemblyCodeGenerating(r);
			setForBranchCondInLoop();
			//exprs.appendAll(operator.apply(r, r.getNext()));
			return exprs;
		} else {
				TokSeq exprs = lhs.assemblyCodeGenerating(r);
				exprs.appendAll(rhs.assemblyCodeGenerating(r.getNext()));
				exprs.appendAll(operator.apply(r, r.getNext()));
				return exprs;
			}
	}

	private void setForBranchCondInLoop() {
		if(operator == BinaryOperators.GRT) {
			inLoopGT = true;
			return;
		} else if(operator == BinaryOperators.GRT_EQ) {
			inLoopGTE = true;
			return;
		} else if(operator == BinaryOperators.LESS) {
			inLoopLT = true;
			return;
		} else if(operator == BinaryOperators.LESS_EQ) {
			inLoopLTE = true;
			return;
		}
	}

}
