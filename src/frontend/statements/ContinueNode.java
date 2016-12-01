package frontend.statements;

import backend.Register;
import backend.TokSeq;
import backend.Token;
import backend.tokens.general.JumpToken;

public class ContinueNode extends StatNode {

    public ContinueNode() {
    }

    @Override
    public TokSeq assemblyCodeGenerating(Register register) {
        TokSeq contStat = new TokSeq();
        hasContinue = true;
        lastContinue = this;
        Token jump = new JumpToken("continue");
        contStat.prepend(jump);
        return contStat;
    }
}
