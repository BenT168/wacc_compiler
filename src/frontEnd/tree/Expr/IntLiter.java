package frontEnd.tree.Expr;

import frontEnd.tree.Type.BaseType;
import frontEnd.ErrorHandling.IntOverflowException;
import org.antlr.v4.runtime.ParserRuleContext;
import symbolTable.SymbolTable;

public class IntLiter extends Expr {

    private int value;

    public IntLiter(int val){
        this.value = val;
    }

    @Override
    public boolean check(SymbolTable symtble, ParserRuleContext ctx){
        long integer = Long.valueOf(value);
	if (integer < (Math.pow(2, 31)) || integer > (Math.pow(2, 31) + 1)) {
		throw new IntOverflowException("The aboslute value, " + value + " is too huge.", ctx);
	}
	return true; 
    }

    @Override
    public BaseType getType(){
        return BaseType.INT;
    }

}
