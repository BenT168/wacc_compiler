package frontEnd.tree.Function;

import frontEnd.tree.IdentifierAST;
import frontEnd.tree.Parameter.Parameter;
import frontEnd.tree.Type.BaseType;
import org.antlr.v4.runtime.ParserRuleContext;
import symbolTable.SymbolTable;

import java.util.List;

public class FunctionDec extends IdentifierAST {

    private final String returnType;
    private String funcName;
    private List<Parameter> params;
    private Function funcBody;
    private boolean complete;


    public FunctionDec(String returnType, String funcName, List<Parameter> params) {
	this(returnType, funcName);
	this.params = params;
	complete = false;
    }

    public FunctionDec(String returnType, String funcName) {
	this.returnType = returnType;
	this.funcName = funcName;
	complete = false;
    }


    private void checkFunctionNameAndReturnType(SymbolTable symbolTable, ParserRuleContext ctx) {
        IdentifierAST idfromAll = symbolTable.lookUpAll(returnType);
        IdentifierAST id = symbolTable.lookUp(funcName);

        if(idfromAll != null) {
            semanticError.semanticErrorCase(returnType, "unknownType");
        } else if( !(idfromAll instanceof BaseType)) {
            semanticError.semanticErrorCase(returnType, "notAType");
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

}
