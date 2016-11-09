package frontEnd.tree.Type;

import symbolTable.SymbolTable;

public class Function extends Identifier {

    private Type returnType;
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


