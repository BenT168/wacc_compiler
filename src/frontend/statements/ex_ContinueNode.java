package frontend.statements;

import backend.Register;
import backend.TokSeq;
import backend.Token;
import backend.tokens.general.ex_JumpToken;

/**
 * Class representing continue statements
 * Rule: CONTINUE
 */

public class ex_ContinueNode extends StatNode {

    public ex_ContinueNode() {
    }

    /*
    Method for generating ARM code for continue
     */
    @Override
    public TokSeq assemblyCodeGenerating(Register register) {
        TokSeq contStat = new TokSeq();
        hasContinue = true;
        lastContinue = this;
        Token jump = new ex_JumpToken("continue");
        contStat.prepend(jump);
        return contStat;
    }
}
