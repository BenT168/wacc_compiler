package intermediate.symboltable;

public interface SymbolTableStack<K extends Comparable<? super K>, V> {

    public void insertLocal(K key, V value);

    public V lookupLocal(K key);

    public V lookupGlobal(K key);

    public int getCurrentScopeLevel();

    public SymbolTable<K, V> getCurrentSymbolTable();
}
