package intermediate.symboltableimpl;

import intermediate.symboltable.SymbolTable;
import intermediate.symboltable.SymbolTableStack;

import java.util.ArrayList;

public class SymbolTableStackImpl<K extends Comparable<? super K>, V>
        extends ArrayList<SymbolTable<K, V>> implements SymbolTableStack<K, V> {

    private int currentScopeLevel;

    public SymbolTableStackImpl(int currentScopeLevel) {
        this.currentScopeLevel = currentScopeLevel;
        this.add(SymbolTableFactory.createSymbolTable());
    }

    @Override
    public void insertLocal(K key, V value) {
        get(currentScopeLevel).insert(key, value);
    }

    @Override
    public V lookupLocal(K key) {
        return get(currentScopeLevel).lookup(key);
    }

    @Override
    public V lookupGlobal(K key) {
        V result = null;
        for (int i = currentScopeLevel; i >= 0; i--) {
            result = get(i).lookup(key);
            if (result != null)
                break;
        }
        return result;
    }

    @Override
    public int getCurrentScopeLevel() {
        return currentScopeLevel;
    }

    @Override
    public SymbolTable<K, V> getCurrentSymbolTable() {
        return get(currentScopeLevel);
    }
}
