package frontend.statements;

import backend.Labeller;
import backend.Register;
import backend.TokSeq;
import backend.tokens.branch.BranchToken;
import backend.tokens.general.LabelToken;
import backend.tokens.operator.CompareToken;
import frontend.exception.SemanticErrorException;
import frontend.expressions.ExprNode;
import frontend.type.BaseType;
import org.antlr.v4.runtime.ParserRuleContext;
import symboltable.SymbolTable;

/**
 * Class representing do while statements
 * Rule: DO stat WHILE expr DONE
 */

public class ex_DoWhileNode extends StatNode {

    private ExprNode loopCond;
    private StatNode loopBody;

    public ex_DoWhileNode(ExprNode expr, StatNode stat) {
        this.loopCond = expr;
        this.loopBody = stat;
    }

    /*
    Method checks that loop cond is a boolean
     */
    @Override
    public boolean check(SymbolTable st, ParserRuleContext ctx) {
        if (loopCond.getType() == BaseType.BOOL) {
            return true;
        } else {
            throw new SemanticErrorException("While condition should be an expression of type BOOL", ctx);
        }
    }

    /*
    Method for generating ARM code for do while
     */
    public TokSeq assemblyCodeGenerating(Register register) {
        String l0 = "l" + Labeller.counter.getLabel();
        String l1 = "l" + Labeller.counter.getLabel();
        TokSeq doWhileStat = new TokSeq(
                new LabelToken(l1));
        checkBreak(register);
        // generate code for loop body
        doWhileStat.appendAll(loopBody.assemblyCodeGenerating(register));
        doWhileStat.append(
                new LabelToken(l0));
        // generate code for loop cond
        doWhileStat.appendAll(loopCond.assemblyCodeGenerating(register));
        doWhileStat.appendAll(new TokSeq(
                new CompareToken(register, "#1"),
                new BranchToken("EQ", l1)));
        return doWhileStat;
    }

}
