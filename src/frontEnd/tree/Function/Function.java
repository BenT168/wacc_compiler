package frontEnd.tree.Function;

import frontEnd.tree.Identifier;
import frontEnd.tree.Parameter.Parameter;
import frontEnd.tree.Type.BaseType;
import symbolTable.SymbolTable;

public class Function extends Identifier {

    private BaseType returnType;
    private Parameter formals[];
    private SymbolTable symbolTable;

    public Function(BaseType returnType) {
        this.returnType = returnType;
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

}


