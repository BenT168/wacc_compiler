package frontEnd.tree.Expr;

import frontEnd.tree.Type.BaseType;
import org.antlr.v4.runtime.ParserRuleContext;
import symbolTable.SymbolTable;

public class BoolLiter extends Expr {

    private String strVal;
    private boolean value;

    public BoolLiter(String boolStr) {
        this.strVal = boolStr;
    }

    @Override
    public boolean check(SymbolTable symtble, ParserRuleContext ctx){
        switch (this.strVal) {
	case "true":
		this.value = true;
		return true;
	case "false":
		this.value = false;
		return true;
	default:
		new IllegalArgumentException("BoolLeaf can only be 'true' or 'false', string given " + strVal);
		return false;
		}
    }

    @Override
    public BaseType getType(){
        return BaseType.BOOL;
    }

}
