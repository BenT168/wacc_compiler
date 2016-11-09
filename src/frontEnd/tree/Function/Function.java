package frontEnd.tree.Function;

import frontEnd.tree.Identifier;
import frontEnd.tree.Parameter.Parameter;
import frontEnd.tree.Type.BaseType;
import symbolTable.SymbolTable;

public class Function extends Identifier {

    private BaseType returnType;
    private Parameter formals[];
    private SymbolTable symbolTable;
    private Identifier id;

    public Function(Identifier Id) {
        this.id = Id;
    }

    public void setSymbolTable(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    public void setFormals(Parameter formal, int i) {
        this.formals[i]= formal;
    }

    public Parameter[] getFormals() {
        return formals;
    }

    @Override
    public void check() {

    }
}


