public class Id {
    String name;

    Type typeCheck(SymbolTable symbolTable) {
       return symbolTable.lookup(name);
    }
}
