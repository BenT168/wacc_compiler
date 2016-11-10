package frontEnd.tree.Stat;

import frontEnd.ErrorHandling.InvalidTypeException;
import frontEnd.tree.Expr.Expr;
import frontEnd.tree.Expr.Ident;
import frontEnd.tree.Type.ArrayType;
import frontEnd.tree.Type.PairType;
import org.antlr.v4.runtime.ParserRuleContext;
import symbolTable.SymbolTable;

public class FreeStat extends Stat {

    private Expr expr;

    public FreeStat(Expr expr) {
        super(expr);
        this.expr = expr;
    }

    @Override
    public boolean check(SymbolTable symbolTable, ParserRuleContext ctx) {
       if (expr instanceof Ident) {
	  Ident identN = (Ident) expr;
	  String ident = identN.getIdent();
	  if (symbolTable.containsRecursive(ident)) {
		if (!(symbolTable.lookUp(ident).getType() instanceof ArrayType
			|| symbolTable.lookUp(ident).getType() instanceof PairType)) {
			throw new InvalidTypeException("Can only free an Array or a Pair", ctx);
			}
		}
		return true;
		}
		new InvalidTypeException("'Free' must be passed an identifier to a variable", ctx);
		return false;
        
    }

}
