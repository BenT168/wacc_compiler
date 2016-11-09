package frontEnd.tree.Expr;

import frontEnd.tree.Type.Pair;
import frontEnd.tree.Type.Type;
import org.antlr.v4.runtime.ParserRuleContext;
import symbolTable.SymbolTable;

public class PairLeaf extends Expr {

    private Type fst;
    private Type snd;
    private String ident;

    public UnaryOperExpr(String ident, Type fst, Type snd){
        this.fst = fst;
        this.snd = snd;
        this.ident = ident;
    }

    @Override
    public boolean check(SymbolTable symtble, ParserRuleContext ctx){
        return true;
    }

    @Override
    public Type getType(){
        return new Pair(fst, snd);
    }

    public String getIdent(){
        return ident;
    }

}
