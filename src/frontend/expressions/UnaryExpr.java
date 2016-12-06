package frontend.expressions;

import backend.Register;
import backend.TokSeq;
import frontend.exception.SemanticErrorException;
import frontend.statements.ex_ForLoopNode;
import frontend.type.BaseType;
import frontend.type.UnaryOperators;
import org.antlr.v4.runtime.ParserRuleContext;
import symboltable.SymbolTable;

public class UnaryExpr extends ExprNode{

	private UnaryOperators operator;
	private ExprNode expr;

	public UnaryExpr(UnaryOperators unaryOp, ExprNode expr) {
		this.operator = unaryOp;
		this.expr = expr;
	}

	@Override
	public boolean check(SymbolTable st, ParserRuleContext ctx) {
		if (!operator.check(expr)) {
			throw new SemanticErrorException("The type in the Unary " +
					"operator expression is not valid", ctx);
		}
		return true;
	}

	@Override
	public BaseType getType() {
		return operator.getType();
	}

	@Override
	public int weight() {
		return this.expr.weight();
	}

	@Override
	public TokSeq assemblyCodeGenerating(Register register) {
		if(ex_ForLoopNode.isInLoopAssembler()) {
			TokSeq exprSeq = new TokSeq();
			return exprSeq.appendAll(operator.apply(register));
		}
		TokSeq exprSeq = expr.assemblyCodeGenerating(register);
		return exprSeq.appendAll(operator.apply(register));
	}

}
