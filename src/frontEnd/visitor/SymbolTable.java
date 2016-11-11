package frontEnd.visitor;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class SymbolTable {

    // Identifiers are strings
    private LinkedList<HashMap<String, Type>> vTableScopes = new LinkedList<>();
    private HashMap<String, List<Type>> fTable = new HashMap<>();

    public LinkedList<HashMap<String, Type>> getvTableScopes() {
        return vTableScopes;
    }

    public HashMap<String, List<Type>> getfTable() {
        return fTable;
    }

    public boolean fTableContainsKey(String key) {
        return fTable.containsKey(key);
    }

    public Type varLookup(String key) {
        Type res;
        for (HashMap<String, Type> scope: vTableScopes) {
            res = scope.get(key);
            if (!(res == null)) {
                return res;
            }
        }
        System.err.println("Variable identifier: " + key + " unbound in current scope or any enclosing scopes");
        System.exit(200);
        return null;
    }

    public List<Type> funcLookup(String key) {
        if (!(fTable.containsKey(key))) {
            System.err.println("Function: " + key + " doesn't exist in symbol table");
            System.exit(200);
        }
        return fTable.get(key);
    }

    // Function symbol table insert method
    void fTableInsert(String key, List<Type> value) {
        if (fTable.containsKey(key)) {
            System.err.println("Function identifier: " + key + " already in scope");
            System.exit(200);
        }
        fTable.put(key, value);
    }

    // Variable symbol table insert method
    void vTableInsert(String key, Type value) {
        // We cannot insert an identifier twice. This is a semantic error in the program.
        if (vTableScopes.getFirst().containsKey(key)) {
            System.err.println("Variable identifier: " + key + " already in scope");
            System.exit(200);
        }
        vTableScopes.getFirst().put(key, value);
    }

    void enterScope() {
        vTableScopes.addFirst(new HashMap<String, Type>());
    }

    void removeScope() {
        // TODO: Why do we need to check whether it equals 1?
        // TODO: What about when we are removing the symbol table in 'visitProgram' after visiting each function context.
        if(vTableScopes.size() != 1) {
            vTableScopes.removeFirst();
        }
    }
  }