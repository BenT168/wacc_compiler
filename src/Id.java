/**
 * Created by aa14415 on 01/11/16.
 */
public class Id {
    String name;

    Type typeCheck(SymbolTable symbolTable) {
       return symbolTable.lookup(name);
    }
}
