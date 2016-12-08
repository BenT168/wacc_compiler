package frontend.statements;

import backend.TokSeq;
import frontend.exception.SemanticErrorException;
import frontend.Tree;
import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import frontend.assignments.AssignLHS;
import frontend.assignments.Assignable;
import frontend.expressions.Variable;
import frontend.function.FuncDec;
import backend.Register;

/**
 * Class representing assignment statements
 * Rule: assignLHS EQUALS assignRHS
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
	public TokSeq assemblyCodeGenerating(Register dest) {
		return new TokSeq()
						.appendAll(rhs.assemblyCodeGenerating(dest))
						.appendAll(((AssignLHS) lhs).assemblyCodeStoring(dest));
	}
}
