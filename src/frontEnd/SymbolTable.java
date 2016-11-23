package frontEnd;

import frontEnd.exception.SemanticException;
import frontEnd.type.Type;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class SymbolTable {

    // Identifiers are strings
    private LinkedList<HashMap<String, Type>> vTableScopes = new LinkedList<>();

    //Accumulates the variables, so getFirst returns lit of all variables seen so far
    private LinkedList<HashMap<String, Type>> totalVTable = new LinkedList<>();

    private HashMap<String, List<Type>> fTable = new HashMap<>();

    public LinkedList<HashMap<String, Type>> getvTableScopes() {
        return vTableScopes;
    }

    public LinkedList<HashMap<String, Type>> getTotalVTable() {
        return totalVTable;
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
        return null;
    }

    public List<Type> funcLookup(String key) {
        if (!(fTable.containsKey(key))) {
            return null;
        }
        return fTable.get(key);
    }

    // Function symbol table insert method
    void fTableInsert(String key, List<Type> value) {
        if (fTable.containsKey(key)) {
            throw new SemanticException("Function identifier: " +
                    key + " already in scope");
        }
        fTable.put(key, value);
    }

    // Variable symbol table insert method
    void vTableInsert(String key, Type value) {
        // We cannot insert an identifier twice. This is a semantic error in the program.
        if (vTableScopes.getFirst().containsKey(key)) {
            throw new SemanticException("Variable identifier: " + key + " already in scope");
        }
        vTableScopes.getFirst().put(key, value);
        totalVTable.getFirst().put(key, value);
    }

    void enterScope() {
        vTableScopes.addFirst(new HashMap<String, Type>());
        //Set totalvTable
        if(totalVTable.isEmpty()) {
            totalVTable.addFirst(new HashMap<>());
        } else {
            HashMap<String, Type> v = totalVTable.getFirst();
            totalVTable.addFirst(v);
        }
    }

    //Check if key is in totalVTable
    public boolean intotalVTable(String var) {
        for(HashMap<String, Type> table : totalVTable) {
            if(table.containsKey(var)) {
                return true;
            }
        }
        return false;
    }


    void removeScope() {
        if(vTableScopes.size() != 1) {
            vTableScopes.removeFirst();
        }
    }
  }