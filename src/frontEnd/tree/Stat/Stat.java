package frontEnd.tree.Stat;

import frontEnd.tree.ASTTree;
import frontEnd.tree.Expr.Expr;
import frontEnd.tree.Parameter.Parameter;
import frontEnd.tree.Type.BaseType;
import org.antlr.v4.runtime.ParserRuleContext;
import symbolTable.SymbolTable;

public class Stat extends ASTTree {

    private Expr expr;
    private Parameter formals[];
    private SymbolTable symbolTable;

    public Stat(Expr expr) {
        this.expr = expr;
    }

    public Stat() {
    }

    public void setSymbolTable(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    public void setFormals(Parameter formal, int i) {
        this.formals[i]= formal;
    }

    public Parameter[] getFormals() {
        return formals;
    }

    @Override
    public boolean check(SymbolTable symbolTable, ParserRuleContext ctx) {
        return true;
    }

    public BaseType getType() {
        return expr.getType();
    }
}


