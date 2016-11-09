package frontEnd.tree.Expr;

import frontEnd.tree.AST.AST;
import frontEnd.tree.Type.Type;
import org.antlr.v4.runtime.ParserRuleContext;
import symbolTable.SymbolTable;

public abstract class Expr extends AST {

    public abstract boolean check(SymbolTable, ParserRuleContext);

    public abstract Type getType();

}


