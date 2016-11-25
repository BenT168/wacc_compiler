package frontend.statements;

import frontend.exception.SemanticErrorException;
import frontend.Tree;
import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import frontend.assignments.AssignLHS;
import frontend.assignments.Assignable;
import frontend.expressions.Variable;
import frontend.function.FuncDec;
import backend.Register;
import backend.TokenSequence;

/**
 * Class to represent variable assignment statements
 * Rule: assign-lhs '=' assign-rhs
 *
 */

public class AssignNode extends StatNode {

	private Tree lhs;
	private Tree rhs;

	public AssignNode(AssignLHS lhs, Assignable rhs) {
		this.lhs = (Tree) lhs;
		this.rhs = (Tree) rhs;
	}

	@Override
	public boolean check(SymbolTable st, ParserRuleContext ctx) {

		//Check types are compatible
		if (!lhs.getType().isCompatible(rhs.getType())) {
			throw new SemanticErrorException(
					"Cannot assign a " + rhs.getType().toString()
					+ " to a " + lhs.getType().toString(), ctx);
		}

		if (lhs instanceof Variable) {
			String ident = ((Variable) lhs).getIdent();
			if (st.get(ident) instanceof FuncDec) {
				throw new SemanticErrorException(
						"Cannot assign to a function" , ctx);
			}
		}
		return true;

	}

	@Override
	public TokenSequence toAssembly(Register dest) {
		return new TokenSequence()
						.appendAll(rhs.toAssembly(dest))
						.appendAll(((AssignLHS) lhs).toStoreAssembly(dest));
	}
}
