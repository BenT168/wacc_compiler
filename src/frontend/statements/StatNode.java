package frontend.statements;

import frontend.Tree;
import org.antlr.v4.runtime.ParserRuleContext;

/**
 * Abstract class used to group together all Tree statement nodes
 *
 */

import symboltable.SymbolTable;
import frontend.type.BaseType;

public abstract class StatNode extends Tree {

	@Override
	public boolean check( SymbolTable st, ParserRuleContext ctx ) {
		return true;
	}


	@Override
	public BaseType getType() {
		throw new UnsupportedOperationException("It's not possible to call getType() on a StatNode.");
	}

}
