package frontend.function;

import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import frontend.expressions.Variable;
import frontend.type.BaseType;

/* Represents a single parameter of a function
 * Contains information of the type and identifier *
 */

public class Param extends Variable {

	public Param(BaseType paramType, String ident) {
		super(paramType, ident);
	}

	@Override
	public boolean check(SymbolTable st, ParserRuleContext ctx)  {
		return true;
	}

	@Override
	public BaseType getType() {
		return type;
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof Param) {
			Param pn = (Param) other;
			if (pn.getType() == type) {
				return true;
			}
		}
		return false;
	}

	public String getIdent() {
		return ident;
	}

}
