package intermediate.symboltable;

import java.util.NoSuchElementException;

public interface SymbolTable<K extends Comparable<? super K>, V> {

    /**
     * Standard lookup method.
     * @param key key to lookup in symbol table.
     * @return value associated with key, or null if key does not exist in symbol table.
     */
    public V lookup(K key);

    /**
     * Insert key-value pair into symbol table.
     * @param key key to be inserted.
     * @param value value to be inserted.
     */
    public void insert(K key, V value);
}
