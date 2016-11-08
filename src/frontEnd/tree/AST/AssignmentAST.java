package frontEnd.tree.AST;

import frontEnd.tree.Expr.Expr;
import frontEnd.tree.Type.Identifier;
import frontEnd.tree.Type.Variable;


public class AssignmentAST extends AST {

    private Expr expr;

    @Override
    public void check() {
        Identifier id = ST.lookUpAll(varName);
        expr.check();
        if(id == null) {
            semanticError.semanticErrorCase(varName, "unknownVariable");
        } else if(!(id instanceof Variable)) {
            semanticError.semanticErrorCase(varName, "notAVar");
        } else if(! assignCompat(id.getType(), expr.getType())) {

        } else {
            variable = (Variable) id;
        }

    }
}
