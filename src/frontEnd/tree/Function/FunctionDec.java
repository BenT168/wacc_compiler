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
	this.returnType = returnType;
	this.params = params;
	complete = false;
    }

    public FunctionDec(BaseType returnType, String funcName) {
	this.returnType = returnType;
	this.funcName = funcName;
	complete = false;
    }


    private void checkFunctionNameAndReturnType(SymbolTable symbolTable, ParserRuleContext ctx) {
        ASTTree idfromAll = symbolTable.lookUpAll(String.valueOf(returnType));
        ASTTree id = symbolTable.lookUp(funcName);

        if(idfromAll != null) {
            semanticError.semanticErrorCase(String.valueOf(returnType), "unknownType");
        } else if( !(idfromAll instanceof BaseType)) {
            semanticError.semanticErrorCase(String.valueOf(returnType), "notAType");
        } else if(id != null) {
            semanticError.semanticErrorCase(funcName, "alreadyDeclared");
        } else {
            Function function = new Function((BaseType) idfromAll);
            function.setSymbolTable(symbolTable);
            symbolTable.add(funcName, function);
        }
    }


    public boolean check(SymbolTable symbolTable, ParserRuleContext ctx) {
        checkFunctionNameAndReturnType(symbolTable, ctx);
        funcBody.setSymbolTable(symbolTable);

        int i = 0;
        for(Parameter p : params) {
            p.check(symbolTable, ctx);
            funcBody.setFormals(p, i);
        }
        symbolTable = symbolTable.getEncSymbolTable();

        return true;
    }

    public ParamList getParams() {
        return params;
    }


    public void add(Stat funcBody) {
        this.funcBody = funcBody;
		complete = true; 
    }
}
