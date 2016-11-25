package frontend.expressions;

import backend.Register;
import backend.TokenSequence;
import frontend.exception.SemanticErrorException;
import frontend.type.BaseType;
import frontend.type.UnaryOperators;
import org.antlr.v4.runtime.ParserRuleContext;
import symboltable.SymbolTable;

/* Represents a Unary Operator expression
 * Holds the operator and the expression
 * Checks expression type is compatible with the operator
 * Rule: unary-oper expressions
 * Where unary-oper is '!' | '-' | 'len' | 'ord' | 'chr'
 */

public class UnaryExpr extends ExprNode{

	private UnaryOperators operator;
	private ExprNode expr;

	public UnaryExpr(UnaryOperators unaryOp, ExprNode expr) {
		this.operator = unaryOp;
		this.expr = expr;
	}

	@Override
	public boolean check(SymbolTable st, ParserRuleContext ctx) {
		//Getting line and column number for error message
		int line = ctx.start.getLine();
		int column = ctx.start.getCharPositionInLine();

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
	public TokenSequence toAssembly(Register register) {
		TokenSequence exprSeq = expr.toAssembly(register);
		return exprSeq.appendAll(operator.apply(register));
	}

}
