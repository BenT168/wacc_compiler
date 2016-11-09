package frontEnd.tree.AST;

import frontEnd.tree.Identifier;
import frontEnd.tree.Type.BaseType;
import frontEnd.tree.Variable;
import org.antlr.v4.runtime.ParserRuleContext;
import symbolTable.SymbolTable;

public class VariableDeclAST extends AST {

    private String typeName;

    public VariableDeclAST(String typeName, String varname, Variable variable) {
        this.typeName = typeName;
        varName = varname;
        this.variable = variable;

    }

    @Override
    public boolean check(SymbolTable symbolTable, ParserRuleContext ctx) {
        Identifier varFromAll = ST.lookUpAll(typeName);
        Identifier var = ST.lookUp(varName);

        if(varFromAll == null) {
            semanticError.semanticErrorCase(typeName, "unknownType");
        } else if(! (varFromAll instanceof BaseType)) {
            semanticError.semanticErrorCase(typeName, "notAType");
        } else if(var == null) {
            semanticError.semanticErrorCase(varName, "alreadyDeclared");
        } else {
            variable = new Variable((BaseType) varFromAll, varName);
            ST.add(varName, variable);
        }

        return true;
    }
}