package frontEnd.tree.Expr;

import frontEnd.tree.Type.BinaryOp;
import frontEnd.ErrorHandling.InvalidTypeException;
import frontEnd.tree.Type.Type;
import org.antlr.v4.runtime.ParserRuleContext;
import symbolTable.SymbolTable;


public class BinaryOperExpr extends Expr {

    private BinaryOp binaryOp;
    private Expr lhs;
    private Expr rhs;

    public BinaryOperExpr(Expr lhs,BinaryOp binaryOp, Expr rhs){
        this.lhs = lhs;
        this.binaryOp = binaryOp;
        this.rhs = rhs;
    }

    @Override
    public boolean check(SymbolTable symtble, ParserRuleContext ctx){
        if(!binaryOp.check(lhs, rhs)){
            throw new InvalidTypeException("The type in the binary expression are not compatible", ctx);
            return false;
        }
        return true;
    }

    @Override
    public boolean check() {
        return false;
    }

    @Override
    public Type getType(){
        return binaryOp.getType();
    }


}
