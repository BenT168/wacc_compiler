package frontEnd.tree.Stat;

import frontEnd.ErrorHandling.InvalidTypeException;
import frontEnd.tree.Expr.Expr;
import frontEnd.tree.Type.BaseType;
import org.antlr.v4.runtime.ParserRuleContext;
import symbolTable.SymbolTable;

public class WhileStat extends Stat {

    private Expr loop;

    public WhileStat(Expr loop) {
        this.loop = loop;
    }

    @Override
    public boolean check(SymbolTable symbolTable, ParserRuleContext ctx) {
        if ( !loop.getType().equals(BaseType.BOOL)){
            throw new InvalidTypeException("While loop should take in a bool in condition", ctx);
        }
        return true;
    }

}
