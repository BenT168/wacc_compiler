package symbolTable;

import frontEnd.tree.Type.Identifier;
import java.util.Dictionary;

public class SymbolTable {

    private SymbolTable encSymbolTable;
    private Dictionary<String, Identifier> dict;

    public SymbolTable(SymbolTable st) {
        dict = null ;
        encSymbolTable = st;
    }

    public void add(String name, Identifier id) {
        dict.put(name, id);
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

    public Dictionary<String, Identifier> getDict() {
        return dict;
    }
}

