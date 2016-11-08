package frontEnd.tree.AST;

import frontEnd.semanticCheck.SemanticError;
import frontEnd.tree.Type.Type;
import frontEnd.tree.Type.Variable;
import symbolTable.SymbolTable;

public abstract class AST {

    protected String varName;
    protected Variable variable;
    protected SymbolTable ST;
    protected SemanticError semanticError;

    public abstract void check();

    public boolean assignCompat(Type type, Type exprType) {
        return type.equals(exprType);
    }
}