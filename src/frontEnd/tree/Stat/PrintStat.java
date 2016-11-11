package frontEnd.tree.Stat;

import frontEnd.tree.Expr.Expr;


public class PrintStat extends Stat{
    private Expr expr;
    public PrintStat(Expr expr) {
        this.expr = expr;
    }
}
