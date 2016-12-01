package frontend.statements;

import backend.Register;
import frontend.Tree;
import org.antlr.v4.runtime.ParserRuleContext;

/**
 * Abstract class used to group together all Tree statement nodes
 *
 */

import symboltable.SymbolTable;
import frontend.type.BaseType;

public abstract class StatNode extends Tree {

	protected static boolean hasContinue = false;
	protected static ContinueNode lastContinue;
	protected static boolean hasBreak = false;
	protected static BreakNode lastBreak;

	public void checkContinue(Register reg) {
		if (hasContinue) {
			lastContinue.assemblyCodeGenerating(reg);
		}
		hasContinue = false;
		lastContinue = null;
	}

	public void checkBreak(Register reg) {
		if (hasBreak) {
			lastBreak.assemblyCodeGenerating(reg);
		}
		hasBreak = false;
		lastBreak = null;
	}

	@Override
	public boolean check( SymbolTable st, ParserRuleContext ctx ) {
		return true;
	}


	@Override
	public BaseType getType() {
		throw new UnsupportedOperationException("It's not possible to call getType() on a StatNode.");
	}

}
