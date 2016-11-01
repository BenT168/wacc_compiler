import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class SymbolTable implements Cloneable {
    private Map<String, Type> table;

    /* Creates new symbol table */
    public SymbolTable() {
        this.table = new HashMap<>();
    }

    /* Adds item to symbol table.
     * Every item is a pair of variable name and its associated type. */
    public void add(String varName, Type type) {
        if (!table.containsKey(varName)) {
            //if table does not contain variable name yet, insert normally
            table.put(varName, type);
        } else {
            //if table already contains variable name, replace type
            table.replace(varName, type);
        }
    }

    /* Searches for variable name and returns its associated key
     * in the symbol table. */
    public Type lookup(String varName) {
        if (table.containsKey(varName)) {
            return table.get(varName);
        } else {
            throw new NoSuchElementException();
        }
    }

    /* Returns a clone of the current symbol table.
     * Useful for child scopes. */
    public Map<String, Type> clone() {
        Map<String, Type> tableClone = new HashMap<>();
        for (String key : table.keySet()) {
            tableClone.put(key, table.get(key));
        }
        return tableClone;
    }

    /* Removes all items from symbol table. */
    public void clear() {
        table.clear();
    }

}
