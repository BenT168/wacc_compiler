package frontEnd.tree.Function;

import frontEnd.tree.ASTTree;
import frontEnd.tree.Parameter.Parameter;
import frontEnd.tree.Type.BaseType;
import org.antlr.v4.runtime.ParserRuleContext;
import symbolTable.SymbolTable;

public class Function extends ASTTree {

    private BaseType returnType;
    private Parameter formals[];
    private SymbolTable symbolTable;

    public Function(BaseType returnType) {
        this.returnType = returnType;
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
}


