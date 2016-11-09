package frontEnd.tree.AST;

import frontEnd.tree.Expr.Expr;
import frontEnd.tree.Identifier;
import frontEnd.tree.Variable;
import org.antlr.v4.runtime.ParserRuleContext;
import symbolTable.SymbolTable;


public class AssignmentAST extends AST {

    private Expr expr;

    @Override
    public boolean check(SymbolTable symbolTable, ParserRuleContext ctx) {

        Identifier id = symbolTable.lookUpAll(varName);
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
