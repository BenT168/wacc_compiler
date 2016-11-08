package frontEnd.tree;

import symbolTable.SymbolTable;

public class Function extends Identifier {
    private Type returnType;
    private Parameter parameters[];
    private SymbolTable symtab;
}
