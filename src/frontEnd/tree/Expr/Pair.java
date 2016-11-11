package frontEnd.tree.Expr;

import frontEnd.tree.Type.BaseType;
import frontEnd.tree.Type.PairType;
import org.antlr.v4.runtime.ParserRuleContext;
import symbolTable.SymbolTable;

public class Pair extends Expr {
    
    private BaseType fstType;
    private BaseType sndType;
    private String ident;
    
    public Pair(String ident, BaseType fstType, BaseType sndType) {
		this.fstType = fstType;
		this.sndType = sndType;
    }

    @Override
    public boolean check(SymbolTable symtble, ParserRuleContext ctx){
        return true;
    }

    @Override
    public BaseType getType(){
        return new PairType(fstType, sndType);
    }


}
