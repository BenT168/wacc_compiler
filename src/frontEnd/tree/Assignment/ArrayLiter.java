package frontEnd.tree.Assignment;

import frontEnd.tree.Expr.Expr;
import frontEnd.tree.Type.BaseType;
import org.antlr.v4.runtime.ParserRuleContext;
import symbolTable.SymbolTable;

import java.util.ArrayList;

public class ArrayLiter extends Assignment {

	protected ArrayList<Expr> elems;
	protected BaseType baseType;

       public ArrayLiter(ArrayList<Expr> elems) {
		this.elems = elems;
		if (elems.size() > 0) {	
			baseType = elems.get(0).getType();
		} else {
			baseType = BaseType.NULL;
		}
	}

	@Override
	public boolean check(SymbolTable st, ParserRuleContext ctx) {
		//Iterates through array  and checks all elements are of the same type (i.e. baseType)
		for(int i = 0; i < elems.size(); i++) {
			if (!elems.get(i).getType().isCompatible(baseType)) {
				return false;
			}
		}
		return true;
	}

}
