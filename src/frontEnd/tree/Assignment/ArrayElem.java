package frontEnd.tree.Assignment;

import frontEnd.ErrorHandling.InvalidTypeException;
import frontEnd.tree.Expr.Expr;
import frontEnd.tree.Type.ArrayType;
import frontEnd.tree.Type.BaseType;
import org.antlr.v4.runtime.ParserRuleContext;
import symbolTable.SymbolTable;

import java.util.ArrayList;

public class ArrayElem extends Expr implements AssignLHS {

	public final ArrayList<Expr> locations;
	public final ArrayType arrayType;

	public ArrayElem(ArrayList<Expr> locations, ArrayType type) {
		this.locations = locations;
		this.arrayType = type;
	}

	@Override
	public boolean check(SymbolTable st, ParserRuleContext ctx ) {
		for(Expr  pos : locations) {
			if(!(pos.getType() == BaseType.INT)) {
                el.record(new InvalidTypeException("Array position can only be found using an Int", ctx));
				return false;
			}
		}

		return true;
	}

	@Override
	public BaseType getType() {
		return arrayType.getType();
	}

}
