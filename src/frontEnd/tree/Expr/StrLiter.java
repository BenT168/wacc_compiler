package frontEnd.tree.Expr;

import frontEnd.tree.Type.BaseType;
import org.antlr.v4.runtime.ParserRuleContext;
import symbolTable.SymbolTable;

public class StrLiter extends Expr {

    private String str;

    public StrLiter(String str) {
        this.str = str;
    }

    @Override
    public boolean check(SymbolTable symtble, ParserRuleContext ctx){
        return true;
    }

    @Override
    public BaseType getType() {
        return BaseType.STRING;
    }
}
