package frontEnd.tree.Stat;

import frontEnd.tree.ASTTree;
import frontEnd.tree.Expr.Expr;
import frontEnd.tree.Parameter.Parameter;
import frontEnd.tree.Type.BaseType;
import org.antlr.v4.runtime.ParserRuleContext;
import symbolTable.SymbolTable;

public class Stat extends ASTTree {

   
    @Override
    public boolean check(SymbolTable symbolTable, ParserRuleContext ctx) {
        return true;
    }

    public BaseType getType() {
        throw new UnsupportedOperationException("It's not possible to call getType() on a StatNode.");
    }
}


