package frontEnd.tree.Expr;

import frontEnd.tree.Type.Type;
import org.antlr.v4.runtime.ParserRuleContext;
import symbolTable.SymbolTable;

public class StrLeaf extends Expr {

    private String str;

    public StrLeaf(String str) {
        this.str = str;
    }

    @Override
    public boolean check(SymbolTable symtble, ParserRuleContext ctx){
        return true;
    }

    @Override
    public Type getType() {
        return Type.STRING;
    }
}
