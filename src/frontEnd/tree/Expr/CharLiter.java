package frontEnd.tree.Expr;

import frontEnd.tree.Type.BaseType;
import org.antlr.v4.runtime.ParserRuleContext;
import symbolTable.SymbolTable;

public class CharLiter extends Expr {

    private String strChar;

    public CharLiter(String strChar) {
        this.strChar = strChar;
    }

    @Override
    public boolean check(SymbolTable symtble, ParserRuleContext ctx){
        return true;
    }

    @Override
    public BaseType getType(){
        return BaseType.CHAR;
    }


}
