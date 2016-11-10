package frontEnd.tree.Expr;

import frontEnd.tree.Assignment.Assignment;
import frontEnd.tree.Type.BaseType;
import org.antlr.v4.runtime.ParserRuleContext;
import symbolTable.SymbolTable;

public abstract class Expr extends Assignment {

    public abstract boolean check(SymbolTable symbolTable, ParserRuleContext ctx);

    public abstract BaseType getType();

}


