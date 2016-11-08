package frontEnd.tree.AST;

import frontEnd.tree.Type.Identifier;
import frontEnd.tree.Type.Type;
import frontEnd.tree.Type.Parameter;
import symbolTable.SymbolTable;

import java.util.List;

package AST;


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
        } else if( !(idfromAll instanceof Type)) {
            semanticError.semanticErrorCase(returnTypeName, "notAType");
        } else if(id != null) {
            semanticError.semanticErrorCase(funcName, "alreadyDeclared");
        } else {
            function = new Function(idfromAll);
            ST.add(funcName, function);
        }
    }

    @Override
    public void check() {
        checkFunctionNameAndReturnType();
        ST = new SymbolTable(ST);
        function.setSymbolTable(ST);


        int i = 0;
        for(Parameter p : params) {
            p.check();
            function.setFormals(p.getParam(), i);
        }
        ST = ST.getEncSymbolTable();
    }
}
