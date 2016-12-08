package frontend.statements;

import backend.TokSeq;
import backend.Token;
import frontend.exception.SemanticErrorException;
import frontend.type.BaseType;
import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import frontend.expressions.ExprNode;
import backend.Register;
import backend.tokens.branch.BranchLinkToken;
import backend.tokens.move.MovRegToken;

/**
 * Class representing exit statements
 * Rule: EXIT expression
 */

public class ExitNode extends StatNode {

	private ExprNode exitVal;

	public ExitNode(ExprNode exitVal) {
		this.exitVal = exitVal;
	}

	@Override
	public boolean check( SymbolTable st, ParserRuleContext ctx ) {
		if (exitVal.getType() == BaseType.INT) {
			return true;
		}
		throw  new SemanticErrorException("Exit statements must have an int as the argument", ctx);
	}

	@Override
	public TokSeq assemblyCodeGenerating(Register dest) {
		TokSeq ldr = exitVal.assemblyCodeGenerating(dest);
		Token mov2 = new MovRegToken(Register.R0, dest);
		Token branch = new BranchLinkToken("exit");

		ldr.appendAll(new TokSeq(mov2, branch));
		return ldr;
	}

}
