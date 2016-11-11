package frontEnd.tree;

import frontEnd.ErrorHandling.ErrorListener;
import frontEnd.tree.Type.BaseType;
import org.antlr.v4.runtime.ParserRuleContext;
import symbolTable.SymbolTable;

public abstract class ASTTree {

    public abstract boolean check(SymbolTable symbolTable, ParserRuleContext ctx);

    public static ErrorListener el = new ErrorListener();

    public abstract BaseType getType();
    
    public static boolean isCorrect() {
	return el.errorCount() == 0;
    }

}
