package frontEnd.tree;

import frontEnd.semanticCheck.SemanticError;
import frontEnd.tree.Function.Variable;
import frontEnd.tree.Type.BaseType;
import org.antlr.v4.runtime.ParserRuleContext;
import symbolTable.SymbolTable;

public abstract class IdentifierAST {

    protected String varName;
    protected Variable variable;
    protected SymbolTable ST;
    protected SemanticError semanticError;

    public abstract boolean check(SymbolTable symbolTable, ParserRuleContext ctx);

    public boolean assignCompat(BaseType type, BaseType exprType) {
        return type.equals(exprType);
    }

}
