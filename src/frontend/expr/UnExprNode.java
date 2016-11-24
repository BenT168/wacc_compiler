package frontend.expr;

import backend.Register;
import backend.TokenSequence;
import frontend.exception.ThrowException;
import frontend.type.WACCType;
import frontend.type.WACCUnOp;
import org.antlr.v4.runtime.ParserRuleContext;
import symboltable.SymbolTable;

/* Represents a Unary Operator expression
 * Holds the operator and the expression
 * Checks expression type is compatible with the operator
 * Rule: unary-oper expr
 * Where unary-oper is '!' | '-' | 'len' | 'ord' | 'chr'
 */

public class UnExprNode extends ExprNode{

	private WACCUnOp operator;
	private ExprNode expr;

	public UnExprNode(WACCUnOp unaryOp, ExprNode expr) {
		this.operator = unaryOp;
		this.expr = expr;
	}

	@Override
	public boolean check(SymbolTable st, ParserRuleContext ctx) {
		//Getting line and column number for error message
		int line = ctx.start.getLine();
		int column = ctx.start.getCharPositionInLine();

		if (!operator.check(expr)) {
			//Throw Semantic Error
			String msg = "Wrong argument type for unary operator.\n" +
					"Expected type : INT \n" + "Actual type : " + expr.toString();
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
		return this.expr.weight();
	}

	@Override
	public TokenSequence toAssembly(Register register) {
		TokenSequence exprSeq = expr.toAssembly(register);
		return exprSeq.appendAll(operator.apply(register));
	}

}
