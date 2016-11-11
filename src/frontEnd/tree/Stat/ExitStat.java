package frontEnd.tree.Stat;

import frontEnd.ErrorHandling.InvalidTypeException;
import frontEnd.tree.Expr.Expr;
import frontEnd.tree.Type.BaseType;
import org.antlr.v4.runtime.ParserRuleContext;
import symbolTable.SymbolTable;

public class ExitStat extends Stat {

    private Expr exitCode;

    public ExitStat(Expr exitCode) {
        this.exitCode = exitCode;
    }

    @Override
    public boolean check(SymbolTable symbolTable, ParserRuleContext ctx) {
        if ( !exitCode.getType().equals(BaseType.INT)){
            new InvalidTypeException("Exit statement must be an int.", ctx);
            return false;
        }
        return true;
    }

}
