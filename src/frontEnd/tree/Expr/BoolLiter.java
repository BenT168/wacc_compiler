package frontEnd.tree.Expr;

import frontEnd.tree.Type.BaseType;
import org.antlr.v4.runtime.ParserRuleContext;
import symbolTable.SymbolTable;

public class BoolLiter extends Expr {

    private String strVal;

    public BoolLiter(String boolStr) {
        this.strVal = boolStr;
    }

    @Override
    public boolean check(SymbolTable symtble, ParserRuleContext ctx){
        return true;
    }

    @Override
    public BaseType getType(){
        return BaseType.BOOL;
    }

}
