package frontEnd.tree.Expr;

import frontEnd.tree.Type.PairType;
import frontEnd.tree.Type.BaseType;
import org.antlr.v4.runtime.ParserRuleContext;
import symbolTable.SymbolTable;

public class PairLiter extends Expr {

    private BaseType fst;
    private BaseType snd;
    private String ident;

    public PairLiter(String ident, BaseType fst, BaseType snd){
        this.fst = fst;
        this.snd = snd;
        this.ident = ident;
    }

    @Override
    public boolean check(SymbolTable symtble, ParserRuleContext ctx){
        return true;
    }

    @Override
    public BaseType getType(){
        return new PairType(fst, snd);
    }

    public String getIdent(){
        return ident;
    }

}
