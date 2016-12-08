package frontend.expressions;

import backend.Labeller;
import backend.Register;
import backend.TokSeq;
import backend.tokens.branch.BranchToken;
import backend.tokens.general.LabelToken;
import backend.tokens.operator.CompareToken;
import frontend.exception.SemanticErrorException;
import frontend.type.BaseType;
import org.antlr.v4.runtime.ParserRuleContext;
import symboltable.SymbolTable;

public class ex_TernaryExpr extends ExprNode {

    private ExprNode cond;
    private ExprNode ifExpr;
    private ExprNode thenExpr;

    public ex_TernaryExpr(ExprNode cond, ExprNode ifExpr, ExprNode thenExpr) {
        this.cond = cond ;
        this.ifExpr = ifExpr;
        this.thenExpr = thenExpr;
    }

    /*
    Method checks that ternary arguments are correctly typed
     */
    @Override
    public boolean check(SymbolTable st, ParserRuleContext ctx) {
        if (cond.getType() == BaseType.BOOL && (ifExpr.getType() == ifExpr.getType())) {
            return true;
        } else {
            throw new SemanticErrorException("The type in the Unary " +
                    "operator expression is not valid", ctx);
        }
    }

    /*
    Method returns ternary cond type
     */
    @Override
    public BaseType getType() {
        return ifExpr.getType();
    }

    @Override
    public int weight() {
        return this.cond.weight();
    }

    /*
    Method for generating code for ternary expr
     */
    @Override
    public TokSeq assemblyCodeGenerating(Register register) {
        TokSeq ternaryExpr = cond.assemblyCodeGenerating(register);
        String l0 = "l" + Labeller.counter.getLabel();
        String l1 = "l" + Labeller.counter.getLabel();
        ternaryExpr.appendAll(new TokSeq(
                new CompareToken(register, "#0"),
                new BranchToken("EQ", l0)));
        //generate code for ifExpr
        ternaryExpr.appendAll(ifExpr.assemblyCodeGenerating(register));
        ternaryExpr.appendAll(new TokSeq(
                new BranchToken(l1),
                new LabelToken(l0)));
        //generate code for thenExpr
        ternaryExpr.appendAll(thenExpr.assemblyCodeGenerating(register));
        ternaryExpr.append(new LabelToken(l1));
        return ternaryExpr;
    }
}
