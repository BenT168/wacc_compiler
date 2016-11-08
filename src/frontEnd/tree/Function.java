package frontEnd.tree;

import frontEnd.tree.Type.Type;
import symbolTable.SymbolTable;

public class Function extends Identifier {
    private Type returnType;
    private Parameter parameters[];
    private SymbolTable symtab;
}
