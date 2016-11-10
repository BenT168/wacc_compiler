package frontEnd.tree.Stat;

import frontEnd.ErrorHandling.InvalidTypeException;
import frontEnd.tree.Expr.Expr;
import frontEnd.tree.Type.BaseType;
import org.antlr.v4.runtime.ParserRuleContext;
import symbolTable.SymbolTable;

public class IfElseStat extends Stat {

    private Expr condLoop;

    public IfElseStat(Expr condLoop) {
        super(condLoop);
        this.condLoop = condLoop;
    }

    @Override
    public boolean check(SymbolTable symbolTable, ParserRuleContext ctx) {
        if ( ! condLoop.getType().equals(BaseType.BOOL)){
            throw new InvalidTypeException("IfElse statement should take in a bool as condition", ctx);
        }
        return true;
    }

}
