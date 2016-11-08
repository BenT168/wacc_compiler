import frontEnd.tree.Type.Type;
import symbolTable.SymbolTable;

public class Class extends Type {
    private Class superclass;
    private SymbolTable symtab;

    public Class(String type, SymbolTable symtab) {
        super(type);
        this.symtab = symtab;
        this.superclass = null;
    }

    public Class(String type, SymbolTable symtab, Class superclass) {
        super(type);
        this.symtab = symtab;
        this.superclass = superclass;
    }

    public Class getSuperclass() {
        return superclass;
    }

    public SymbolTable getSymtab() {
        return symtab;
    }
}
