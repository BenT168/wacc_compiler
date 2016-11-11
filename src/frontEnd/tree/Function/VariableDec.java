package frontEnd.tree.Function;

import frontEnd.ErrorHandling.IncompatibleTypesException;
import frontEnd.ErrorHandling.NotUniqueIdentifierException;
import frontEnd.tree.Assignment.Assignment;
import frontEnd.tree.Stat.Stat;
import frontEnd.tree.Type.BaseType;
import org.antlr.v4.runtime.ParserRuleContext;
import symbolTable.SymbolTable;

public class VariableDec extends Stat {

    private Assignment rhs;
    private BaseType var;
    private String ident;

    public VariableDec(BaseType varType, String ident, Assignment rhs) {
        this.ident = ident;
        this.rhs = rhs;
        this.var = varType;
    }


    @Override
    public boolean check(SymbolTable st, ParserRuleContext ctx) {
        // First we check the identifier is unique and it is not a function
	if ( st.containsCurrent(ident) && !(st.lookUp(ident) instanceof FunctionDec) ) {
	new NotUniqueIdentifierException(
	"A variable with identifier " + ident + " was already declared", ctx);
	return false;
} 
		
	// We add the current var to the SymbolTable
	st.add(ident, this);
		
	if ( !var.isCompatible(rhs.getType()) ) {
		new IncompatibleTypesException(
		"The types of the rhs and lhs of the variable declaration do not match.\n" + "LHS: " + var.toString() + ",\n" + "RHS: " + rhs.getType().toString(), ctx);
		return false;
	}
	return true;
    }

   @Override
   public BaseType getType() {
	return this.var;
   }
}
