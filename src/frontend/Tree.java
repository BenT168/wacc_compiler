package frontend;

import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import frontend.type.BaseType;
import frontend.exception.ErrorListener;
import frontend.exception.Exception;
import backend.Register;
import backend.TokenSequence;

public abstract class Tree {

	public static ErrorListener el = Exception.ERROR_LISTENER;

	/**
	 * @param st The current symbol table at the time of the call.
	 * @param ctx The parser rule context associated with the object on which
	 * the method is called.
	 * @return Returns a boolean value which returns false is any semantic errors
	 * are detected in the Tree node on which this function is called.
	 * Returns true otherwise.
	 */
	public abstract boolean check( SymbolTable st, ParserRuleContext ctx );


	/**
	 * @return Returns the BaseType associated with the Tree node.
	 * In cases where there is no clear associated type, this will return BaseType.NULL
	 */
	public abstract BaseType getType();

	public static boolean isCorrect() {
		return el.errorCount() == 0;
	}

	public abstract TokenSequence toAssembly(Register register);

}
