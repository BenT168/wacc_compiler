package frontEnd.tree.Expr;

import frontEnd.tree.Type.BinaryOp;
import frontEnd.ErrorHandling.InvalidTypeException;
import frontEnd.tree.Type.BaseType;
import org.antlr.v4.runtime.ParserRuleContext;
import symbolTable.SymbolTable;


public class BinaryOper extends Expr {

    private BinaryOp binaryOp;
    private Expr lhs;
    private Expr rhs;

    public BinaryOper(Expr lhs,BinaryOp binaryOp, Expr rhs){
        this.lhs = lhs;
        this.binaryOp = binaryOp;
        this.rhs = rhs;
    }

    @Override
    public boolean check(SymbolTable symtble, ParserRuleContext ctx){
        if(!binaryOp.check(lhs, rhs)){
            throw new InvalidTypeException("The type in the binary expression are not compatible", ctx);
        }
        return true;
    }

    @Override
    public BaseType getType(){
        return binaryOp.getType();
    }


}
