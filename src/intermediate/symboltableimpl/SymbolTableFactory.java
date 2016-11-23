package intermediate.symboltableimpl;

import intermediate.symboltable.SymbolTable;
import intermediate.symboltable.SymbolTableStack;

/**
 * We use a factory class to ensure that the concrete symbol table classes do
 * not directly depend on one another, and instead depend directly on the symbol
 * table interfaces and this factory class.
 */
public class SymbolTableFactory {

    public static SymbolTable createSymbolTable() {
        return new SymbolTableImpl<>();
    }

    public static SymbolTableStack createSymbolTableStack() {
        // Symbol Table stacks always created with initial 0 scope level.
        return new SymbolTableStackImpl<>(0);
    }
}
