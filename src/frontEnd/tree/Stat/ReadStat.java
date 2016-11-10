package frontEnd.tree.Stat;

import frontEnd.ErrorHandling.IncompatibleTypesException;
import frontEnd.tree.Expr.Expr;
import frontEnd.tree.Type.BaseType;
import org.antlr.v4.runtime.ParserRuleContext;
import symbolTable.SymbolTable;

public class ReadStat extends Stat {

    private Expr expr;

    public ReadStat(Expr expr) {
        super(expr);
        this.expr = expr;
    }

    @Override
    public boolean check(SymbolTable symbolTable, ParserRuleContext ctx) {
        BaseType type = expr.getType();
        if ( ! symbolTable.checkType(type)){
            throw new IncompatibleTypesException("Return stat should return a compatible type", ctx);
        }
        return true;
    }

}
