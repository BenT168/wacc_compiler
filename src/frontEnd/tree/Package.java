package frontEnd.tree;

import symbolTable.SymbolTable;

public class Package extends Identifier {
    private SymbolTable symtab;

    public Package(SymbolTable symtab) {
        this.symtab = symtab;
    }

    public SymbolTable getSymtab() {
        return symtab;
    }
}
