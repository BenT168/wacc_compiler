package frontEnd.tree.Stat;

import frontEnd.ErrorHandling.IncompatibleTypesException;
import frontEnd.ErrorHandling.InvalidTypeException;
import frontEnd.tree.ASTTree;
import frontEnd.tree.Assignment.AssignLHS;
import frontEnd.tree.Assignment.Assignment;
import frontEnd.tree.Expr.Ident;
import frontEnd.tree.Function.FunctionDec;
import org.antlr.v4.runtime.ParserRuleContext;
import symbolTable.SymbolTable;

public class AssignStat extends Stat {

    private ASTTree lhs;
    private ASTTree rhs;

    public AssignStat(AssignLHS lhs, Assignment rhs) {
        this.lhs = (ASTTree) lhs;
	    this.rhs = (ASTTree) rhs;
    }

    @Override
    public boolean check(SymbolTable symbolTable, ParserRuleContext ctx) {
        if ( !lhs.getType().isCompatible(rhs.getType())){
            throw new InvalidTypeException("Cant assign" + rhs.getType().toString() + " to " + lhs.getType().toString(), ctx);
        }
        if (lhs instanceof Ident) {
		String ident = ((Ident) lhs).getIdent();
		if (symbolTable.lookUp(ident) instanceof FunctionDec) {
			throw new IncompatibleTypesException(
				"Cannot assign to a function" , ctx);
			}
		}
		return true;
    }

}
