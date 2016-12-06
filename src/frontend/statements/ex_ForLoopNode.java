package frontend.statements;

import backend.Labeller;
import backend.Register;
import backend.TokSeq;
import backend.tokens.branch.BranchToken;
import backend.tokens.general.LabelToken;
import backend.tokens.operator.CompareToken;
import frontend.exception.SemanticErrorException;
import frontend.expressions.BinaryExpr;
import frontend.expressions.ExprNode;
import frontend.type.BaseType;
import org.antlr.v4.runtime.ParserRuleContext;
import symboltable.SymbolTable;

public class ex_ForLoopNode extends StatNode {

    //for(loopCondOne ; loopCondTwo; loopCondThree) { loopBody }

    private StatNode loopCondOne;
    private ExprNode loopCondTwo;
    private ExprNode loopCondThree;
    private StatNode loopBody;
    private static boolean inLoop = false;

    public ex_ForLoopNode(StatNode loopCondOne, ExprNode loopCondTwo, ExprNode loopCondThree, StatNode loopBody) {
        this.loopCondOne = loopCondOne;
        this.loopCondTwo = loopCondTwo;
        this.loopCondThree = loopCondThree;
        this.loopBody = loopBody;
    }

    @Override
    public boolean check(SymbolTable st, ParserRuleContext ctx) {
        //loopCondOne should be a declaration
        if(loopCondOne instanceof DeclareNode) {
            if (loopCondOne.getType() == BaseType.INT && loopCondTwo.getType() == BaseType.BOOL
                    && loopCondThree.getType() == BaseType.INT) {
                return true;
            }
        } else {
            throw new SemanticErrorException("For loop statement should have an expressions of type INT, BOOL, INT", ctx);
        }
        return false;
    }

    public static boolean isInLoopAssembler() {
        return inLoop;
    }


    @Override
    public TokSeq assemblyCodeGenerating(Register register) {
        inLoop = true;
        TokSeq forLoopStat = new TokSeq();
        forLoopStat.appendAll(loopCondOne.assemblyCodeGenerating(register));
        String loop = "loop" + Labeller.counter.getLabel();
        forLoopStat.appendAll(new LabelToken(loop));
        forLoopStat.appendAll(loopBody.assemblyCodeGenerating(register));
        forLoopStat.appendAll(loopCondTwo.assemblyCodeGenerating(register.getNext().getNext()));
        forLoopStat.appendAll(loopCondThree.assemblyCodeGenerating(register));
        forLoopStat.appendAll(new TokSeq(new CompareToken(register, register.getNext().getNext())));
        forLoopStat.appendAll(new BranchToken(branchCond(), loop));
        return forLoopStat;
    }

    private String branchCond() {
        if(BinaryExpr.inLoopLTE) {
            return "LE";
        } else if(BinaryExpr.inLoopLT) {
            return "LT";
        } else if(BinaryExpr.inLoopGTE) {
            return "GE";
        } else if(BinaryExpr.inLoopGT) {
            return "GT";
        }
        return "";
    }
}
