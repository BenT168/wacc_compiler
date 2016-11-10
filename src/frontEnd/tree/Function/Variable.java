package frontEnd.tree.Function;

import frontEnd.tree.ASTTree;
import frontEnd.tree.Type.BaseType;
import org.antlr.v4.runtime.ParserRuleContext;
import symbolTable.SymbolTable;

public class Variable extends ASTTree {

    private BaseType type;


    //Uninitialised Variable
    public Variable(BaseType type, String expr) {
        this.type = type;
    }

    public BaseType getType() {
        return type;
    }

    @Override
    public boolean check(SymbolTable symbolTable, ParserRuleContext ctx) {
        return true;
    }
}
