package frontend.stat;

import org.antlr.v4.runtime.ParserRuleContext;

/**
 * Abstract class used to group together all tree statement nodes
 * 
 */

import symboltable.SymbolTable;
import frontend.tree;
import frontend.type.WACCType;

public abstract class StatNode extends tree {

	@Override
	public boolean check( SymbolTable st, ParserRuleContext ctx ) {
		return true;
	}


	@Override
	public WACCType getType() {
		throw new UnsupportedOperationException("It's not possible to call getType() on a StatNode.");
	}

}
