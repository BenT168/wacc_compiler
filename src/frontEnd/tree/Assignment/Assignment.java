package frontEnd.tree.Assignment;

import frontEnd.tree.Expr.Expr;
import frontEnd.tree.IdentifierAST;
import frontEnd.tree.Function.Variable;
import org.antlr.v4.runtime.ParserRuleContext;
import symbolTable.SymbolTable;


public class Assignment extends IdentifierAST {

    private Expr expr;

    @Override
    public boolean check(SymbolTable symbolTable, ParserRuleContext ctx) {

        IdentifierAST id = symbolTable.lookUpAll(varName);
        expr.check(symbolTable, ctx);
        if(id == null) {
            semanticError.semanticErrorCase(varName, "unknownVariable");
        } else if(!(id instanceof Variable)) {
            semanticError.semanticErrorCase(varName, "notAVar");
        } else if(! assignCompat(((Variable) id).getType(), expr.getType())) {

        } else {
            variable = (Variable) id;
        }
        return false;
    }

}
