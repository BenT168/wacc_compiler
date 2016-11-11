package frontEnd.tree.Function;

import frontEnd.tree.ASTTree;
import frontEnd.tree.Parameter.ParamList;
import frontEnd.tree.Parameter.Parameter;
import frontEnd.tree.Stat.Stat;
import frontEnd.tree.Type.BaseType;
import org.antlr.v4.runtime.ParserRuleContext;
import symbolTable.SymbolTable;

public class FunctionDec extends ASTTree {

    private BaseType returnType;
    private String funcName;
    private ParamList params;
    private Stat funcBody;
    private boolean complete;


    public FunctionDec(BaseType returnType, String funcName, ParamList params) {
	this(returnType, funcName); 
	this.params = params;
	complete = false;
    }

    public FunctionDec(BaseType returnType, String funcName) {
	this.returnType = returnType;
	this.funcName = funcName;
	complete = false;
    }

    public boolean check(SymbolTable symbolTable, ParserRuleContext ctx) {
        return complete;
    }

    @Override
    public BaseType getType() {
	return this.returnType;
    }

    public ParamList getParams() {
        return params;
    }

    public String getFuncName() {
	return funcName;
    }


    public void add(Stat funcBody) {
        this.funcBody = funcBody;
		complete = true; 
    }
}
