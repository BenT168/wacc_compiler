package frontend.statements;


import backend.Register;
import backend.TokSeq;
import backend.Token;
import backend.tokens.load.LoadToken;
import backend.tokens.store.StoreToken;
import frontend.exception.SemanticErrorException;
import frontend.expressions.BinaryExpr;
import frontend.expressions.ExprNode;
import frontend.type.BaseType;
import frontend.type.BinaryOperators;
import org.antlr.v4.runtime.ParserRuleContext;
import symboltable.SymbolTable;

public class ex_CompoundAssignNode extends StatNode {

    // Of the form : i += i -=

    private ExprNode variable;
    private BinaryOperators operator ;
    private ExprNode intLiter;
    private boolean isDoubleOper = true;

    public ex_CompoundAssignNode(ExprNode variable, BinaryOperators operator, ExprNode intLiter) {
        this.variable = variable;
        this.operator = operator;
        this.intLiter = intLiter;
    }

    /*
    Method checks that compound assign takes the correct types
     */
    @Override
    public boolean check(SymbolTable st, ParserRuleContext ctx) {
        if (!operator.check(variable, intLiter)) {
            throw new SemanticErrorException("The types in the Binary expression are not compatible.", ctx);
        } return true;
    }

    /*
    Method generates ARM assembly code for compound assign
     */
    @Override
    public TokSeq assemblyCodeGenerating(Register r) {
        TokSeq exprs = intLiter.assemblyCodeGenerating(r);
        exprs.appendAll(variable.assemblyCodeGenerating(r.getNext()));
        exprs.appendAll(operator.apply(r, r.getNext()));
        StoreToken storeToken = new StoreToken(Register.sp, r);
        exprs.appendAll(storeToken);
        return exprs;
    }

}
