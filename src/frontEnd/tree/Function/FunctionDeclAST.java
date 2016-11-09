package frontEnd.tree.Function;

import frontEnd.tree.AST.AST;
import frontEnd.tree.Identifier;
import frontEnd.tree.Type.BaseType;
import frontEnd.tree.Parameter.Parameter;
import org.antlr.v4.runtime.ParserRuleContext;
import symbolTable.SymbolTable;

import java.util.List;

public class FunctionDeclAST extends AST {

    private String returnTypeName;
    private String funcName;
    private List<Parameter> params;
    private Function function;

    private void checkFunctionNameAndReturnType() {
        Identifier idfromAll = ST.lookUpAll(returnTypeName);
        Identifier id = ST.lookUp(funcName);

        if(idfromAll == null) {
            semanticError.semanticErrorCase(returnTypeName, "unknownType");
        } else if( !(idfromAll instanceof BaseType)) {
            semanticError.semanticErrorCase(returnTypeName, "notAType");
        } else if(id != null) {
            semanticError.semanticErrorCase(funcName, "alreadyDeclared");
        } else {
            function = new Function(idfromAll);
            ST.add(funcName, function);
        }
    }


    @Override
    public boolean check(SymbolTable symbolTable, ParserRuleContext ctx) {
        checkFunctionNameAndReturnType();
        function.setSymbolTable(symbolTable);


        int i = 0;
        for(Parameter p : params) {
            p.check(symbolTable, ctx);
            function.setFormals(p.getParam(), i);
        }
        symbolTable = symbolTable.getEncSymbolTable();

        return true;
    }

}
