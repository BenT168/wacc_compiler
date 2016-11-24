package frontend;

import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import frontend.type.WACCType;
import WACCExceptions.ErrorListener;
import WACCExceptions.WACCException;
import backend.Register;
import backend.TokenSequence;

public abstract class tree {
	
	public static ErrorListener el = WACCException.ERROR_LISTENER;	
	
	/**
	 * @param st The current symbol table at the time of the call.
	 * @param ctx The parser rule context associated with the object on which
	 * the method is called.
	 * @return Returns a boolean value which returns false is any semantic errors
	 * are detected in the tree node on which this function is called.
	 * Returns true otherwise.
	 */
	public abstract boolean check( SymbolTable st, ParserRuleContext ctx );
	
	
	/**
	 * @return Returns the WACCType associated with the tree node.
	 * In cases where there is no clear associated type, this will return WACCType.NULL
	 */
	public abstract WACCType getType();
	
	public static boolean isCorrect() {
		return el.errorCount() == 0;
	}

	public abstract TokenSequence toAssembly(Register register);
	
}
