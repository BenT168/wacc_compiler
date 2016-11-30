package frontend;

import backend.TokSeq;
import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import frontend.type.BaseType;
import frontend.exception.ErrorListener;
import frontend.exception.Exception;
import backend.Register;

public abstract class Tree {

	public static ErrorListener el = Exception.ERROR_LISTENER;

	public abstract boolean check( SymbolTable st, ParserRuleContext ctx );

	public abstract BaseType getType();

	public abstract TokSeq assemblyCodeGenerating(Register register);

}
