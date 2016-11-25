package frontend.statements;

import backend.Token;
import frontend.exception.SemanticErrorException;
import frontend.type.BaseType;
import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import frontend.expressions.ExprNode;
import backend.Register;
import backend.TokenSequence;
import backend.tokens.branch.BranchLinkToken;
import backend.tokens.move.MovRegToken;

/**
 * Class to represent exit statements for exiting a program
 * Rule: 'exit' expressions
 *
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
	public TokenSequence toAssembly(Register dest) {
		TokenSequence ldr = exitVal.toAssembly(dest);
		Token mov2 = new MovRegToken(Register.R0, dest);
		Token branch = new BranchLinkToken("exit");

		ldr.appendAll(new TokenSequence(mov2, branch));
		return ldr;
	}

}
