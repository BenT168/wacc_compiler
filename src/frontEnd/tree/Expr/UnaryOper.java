package frontEnd.tree.Expr;

import frontEnd.ErrorHandling.InvalidTypeException;
import frontEnd.tree.Type.BaseType;
import frontEnd.tree.Type.UnaryOp;
import org.antlr.v4.runtime.ParserRuleContext;
import symbolTable.SymbolTable;

public class UnaryOper extends Expr {

    private UnaryOp unaryOp;
    private Expr expr;

    public UnaryOper(UnaryOp unaryOp, Expr expr){
        this.unaryOp = unaryOp;
        this.expr = expr;
    }

    @Override
    public boolean check(SymbolTable symtble, ParserRuleContext ctx){
        if(!unaryOp.check(expr)){
            throw new InvalidTypeException("The type in the unary operator expression is not valid", ctx);
        }
        return true;
    }


    @Override
    public BaseType getType(){
        return unaryOp.getType();
    }


}
