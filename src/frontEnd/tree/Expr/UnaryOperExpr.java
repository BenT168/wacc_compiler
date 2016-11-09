package frontEnd.tree.Expr;

import frontEnd.ErrorHandling.InvalidTypeException;
import frontEnd.tree.Type.Type;
import frontEnd.tree.Type.UnaryOp;
import org.antlr.v4.runtime.ParserRuleContext;
import symbolTable.SymbolTable;

public class UnaryOperExpr extends Expr {

    private UnaryOp unaryOp;
    private Expr expr;

    public UnaryOperExpr(UnaryOp unaryOp, Expr expr){
        this.unaryOp = unaryOp;
        this.expr = expr;
    }

    @Override
    public boolean check(SymbolTable symtble, ParserRuleContext ctx){
        if(!unaryOp.check(expr)){
            throw new InvalidTypeException("The type in the unary operator expression is not valid", ctx);
            return false;
        }
        return true;
    }

    @Override
    public Type getType(){
        return unaryOp.getType();
    }


}
