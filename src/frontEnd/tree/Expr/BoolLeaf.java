package frontEnd.tree.Expr;

import frontEnd.tree.Type.Type;
import org.antlr.v4.runtime.ParserRuleContext;
import symbolTable.SymbolTable;

public class BoolLeaf extends Expr {

    private String strVal;

    public BoolLeaf(String boolStr) {
        this.strVal = boolStr;
    }

    @Override
    public boolean check(SymbolTable symtble, ParserRuleContext ctx){
        return true;
    }

    @Override
    public Type getType(){
        return Type.BOOL;
    }

}
