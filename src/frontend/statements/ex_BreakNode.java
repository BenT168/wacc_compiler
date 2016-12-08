package frontend.statements;

import backend.Register;
import backend.TokSeq;
import backend.Token;
import backend.tokens.general.ex_JumpToken;

/**
 * Class representing break statements
 * Rule: BREAK
 */

public class ex_BreakNode extends StatNode {

    public ex_BreakNode() {
    }

    @Override
    public TokSeq assemblyCodeGenerating(Register r) {
        TokSeq breakStat = new TokSeq();
        hasBreak = true;
        lastBreak = this;
        Token jump = new ex_JumpToken("break");
        breakStat.prepend(jump);
        return breakStat;
    }

}