package frontEnd.tree.Expr;

import frontEnd.tree.Type.Type;
import org.antlr.v4.runtime.ParserRuleContext;
import symbolTable.SymbolTable;

public class CharLeaf extends Expr {

    private String strChar;

    public CharLeaf(String strChar) {
        this.strChar = strChar;
    }

    @Override
    public boolean check(SymbolTable symtble, ParserRuleContext ctx){
        return true;
    }

    @Override
    public Type getType(){
        return Type.CHAR;
    }


}
