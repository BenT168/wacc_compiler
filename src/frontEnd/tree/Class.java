package frontEnd.tree;

import frontEnd.tree.Type.BaseType;
import symbolTable.SymbolTable;

public class Class extends BaseType {

    private Class superclass;
    private SymbolTable symtab;
    private BaseType type;

    public Class(BaseType type, SymbolTable symtab) {
        this.type = type;
        this.symtab = symtab;
        this.superclass = null;
    }

    public Class(String type, SymbolTable symtab, Class superclass) {
        this.symtab = symtab;
        this.superclass = superclass;
    }

    public Class getSuperclass() {
        return superclass;
    }

    public SymbolTable getSymtab() {
        return symtab;
    }

    @Override
    public boolean isCompatible(BaseType type) {
        return false;
    }

    @Override
    public String toString() {
        return null;
    }
}
