package frontEnd.tree.Stat;

import frontEnd.tree.Expr.Expr;

public class BlockStat extends Stat {
   
    private Stat stat;

    public BlockStat(Stat stat) {
        this.stat = stat;

    }
}
