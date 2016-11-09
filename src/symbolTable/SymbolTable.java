package symbolTable;

import frontEnd.tree.Identifier;
import java.util.HashMap;
import java.util.Map;

public class SymbolTable {

    private SymbolTable encSymbolTable;
    private Map<String, Identifier> dict = new HashMap<>();

    /* Pass parent as argument. Pass null if top-level Symtab */
    public SymbolTable(SymbolTable st) {
        encSymbolTable = st;
    }

    public void add(String name, Identifier id) {
        dict.put(name, id);
    }

    public void replace(String name, Identifier id) {
        dict.replace(name, id);
    }

    public Identifier lookUp(String name) {
        return dict.get(name);
    }

    public Identifier lookUpAll(String name) {
        SymbolTable s = this;
        while(s != null) {
            Identifier t = s.lookUp(name);
            if(t != null) {
                return t;
            }
            s = s.encSymbolTable;
        }
        return null;
    }

    public SymbolTable getEncSymbolTable() {
        return encSymbolTable;
    }

    public Map<String, Identifier> getDict() {
        return dict;
    }

    public boolean contains(String ident) {
        return !(lookUp(ident) == null);
    }

    public boolean containsAll(String ident) {
        return !(lookUpAll(ident) == null);
    }
}
