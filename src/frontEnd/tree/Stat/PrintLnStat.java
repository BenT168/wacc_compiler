package frontEnd.tree.Stat;

import frontEnd.tree.Expr.Expr;

public class PrintLnStat extends Stat {
   
    private Expr expr;

    public PrintLnStat(Expr expr) {
        this.expr = expr;
    }
}
