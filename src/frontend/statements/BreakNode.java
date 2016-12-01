package frontend.statements;

import backend.Register;
import backend.TokSeq;
import backend.Token;
import backend.tokens.general.JumpToken;

public class BreakNode extends StatNode {

    public BreakNode() {
    }

    @Override
    public TokSeq assemblyCodeGenerating(Register r) {
        TokSeq breakStat = new TokSeq();
        hasBreak = true;
        lastBreak = this;
        Token jump = new JumpToken("break");
        breakStat.prepend(jump);
        return breakStat;
    }

}