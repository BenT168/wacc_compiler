package frontEnd.tree.Function;

import frontEnd.tree.Assignment.Assignment;
import frontEnd.tree.IdentifierAST;
import frontEnd.tree.Type.BaseType;
import org.antlr.v4.runtime.ParserRuleContext;
import symbolTable.SymbolTable;

public class VariableDec extends Assignment {

    private String typeName;

    public VariableDec(String typeName, String varname, Variable variable) {
        this.typeName = typeName;
        varName = varname;
        this.variable = variable;

    }

    @Override
    public boolean check(SymbolTable symbolTable, ParserRuleContext ctx) {
        IdentifierAST varFromAll = ST.lookUpAll(typeName);
        IdentifierAST var = ST.lookUp(varName);

        if(varFromAll == null) {
            semanticError.semanticErrorCase(typeName, "unknownType");
        } else if(! (varFromAll instanceof BaseType)) {
            semanticError.semanticErrorCase(typeName, "notAType");
        } else if(var == null) {
            semanticError.semanticErrorCase(varName, "alreadyDeclared");
        } else {
            variable = new Variable(((BaseType) varFromAll), null);
            ST.add(varName, variable);
        }

        return true;
    }
}