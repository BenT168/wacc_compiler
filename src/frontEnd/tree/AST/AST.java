package frontEnd.tree.AST;

import frontEnd.semanticCheck.SemanticError;
import frontEnd.tree.Type.BaseType;
import frontEnd.tree.Variable;
import org.antlr.v4.runtime.ParserRuleContext;
import symbolTable.SymbolTable;

public abstract class AST {

    protected String varName;
    protected Variable variable;
    protected SymbolTable ST;
    protected SemanticError semanticError;

    public abstract boolean check(SymbolTable symbolTable, ParserRuleContext ctx);

    public boolean assignCompat(BaseType type, BaseType exprType) {
        return type.equals(exprType);
    }
}
