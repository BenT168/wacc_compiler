package frontEnd.tree.Function;

import frontEnd.tree.ASTTree;
import frontEnd.tree.Assignment.Assignment;
import frontEnd.tree.Type.BaseType;
import org.antlr.v4.runtime.ParserRuleContext;
import symbolTable.SymbolTable;

public class VariableDec extends Assignment {

    private final Assignment assignment;
    private String ident;

    public VariableDec(BaseType varType, String ident, Assignment assignment) {
        this.ident = ident;
        this.assignment = assignment;
    }


    @Override
    public boolean check(SymbolTable symbolTable, ParserRuleContext ctx) {
        ASTTree varFromAll = ST.lookUpAll(ident);
        ASTTree var = ST.lookUp(varName);

        if(varFromAll == null) {
            semanticError.semanticErrorCase(ident, "unknownType");
        } else if(! (varFromAll instanceof BaseType)) {
            semanticError.semanticErrorCase(ident, "notAType");
        } else if(var == null) {
            semanticError.semanticErrorCase(varName, "alreadyDeclared");
        } else {
            variable = new Variable(((BaseType) varFromAll), null);
            ST.add(varName, variable);
        }

        return true;
    }
}