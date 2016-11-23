package intermediate.symboltableimpl;

import intermediate.symboltable.SymbolTable;

import java.util.HashMap;
import java.util.NoSuchElementException;

public class SymbolTableImpl<K extends Comparable<? super K>, V>
        extends HashMap<K, V> implements SymbolTable<K, V> {

    public SymbolTableImpl() {
    }

    @Override
    public V lookup(K key) {
        return this.get(key);
    }

    @Override
    public void insert(K key, V value) {
        this.put(key, value);
    }
}
