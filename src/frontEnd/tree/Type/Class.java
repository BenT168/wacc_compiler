import frontEnd.tree.Type.Type;
import symbolTable.SymbolTable;

public class Class extends Type {

    private Class superclass;
    private SymbolTable symtab;
    private Type type;

    public Class(Type type, SymbolTable symtab) {
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
    public void check() {
    }

    @Override
    public boolean isCompatible(Type type) {
        return false;
    }

    @Override
    public String toString() {
        return null;
    }
}
