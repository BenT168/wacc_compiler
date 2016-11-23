package frontend.stat;

import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import frontend.WACCTree;
import frontend.assignments.AssignLhsNode;
import frontend.assignments.Assignable;
import frontend.expr.VarNode;
import frontend.func.FuncDecNode;
import WACCExceptions.IncompatibleTypesException;
import backend.Register;
import backend.TokenSequence;

/**
 * Class to represent variable assignment statements
 * Rule: assign-lhs '=' assign-rhs
 *
 */

public class AssignStatNode extends StatNode {
	
	private WACCTree lhs;
	private WACCTree rhs;
	
	public AssignStatNode(AssignLhsNode lhs, Assignable rhs) {
		this.lhs = (WACCTree) lhs;
		this.rhs = (WACCTree) rhs;
	}
	
	@Override
	public boolean check(SymbolTable st, ParserRuleContext ctx) {
		
		//Check types are compatible
		if (!lhs.getType().isCompatible(rhs.getType())) {
			new IncompatibleTypesException(
					"Cannot assign a " + rhs.getType().toString()
					+ " to a " + lhs.getType().toString(), ctx);
			return false;
		}
		
		if (lhs instanceof VarNode) {
			String ident = ((VarNode) lhs).getIdent();
			if (st.get(ident) instanceof FuncDecNode) {
				new IncompatibleTypesException(
						"Cannot assign to a function" , ctx);
			}
		}
		return true;
		
	}
	
	@Override
	public TokenSequence toAssembly(Register dest) {
		return new TokenSequence()
						.appendAll(rhs.toAssembly(dest))
						.appendAll(((AssignLhsNode) lhs).toStoreAssembly(dest));
	}
}
