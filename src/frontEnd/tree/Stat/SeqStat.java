package frontEnd.tree.Stat;

public class SeqStat extends Stat {
   
    private Stat lhs;
    private Stat rhs;

    public SeqStat(Stat lhs, Stat rhs) {
        this.lhs = lhs;
        this.rhs = rhs;

    }
}
