package util;

import java.util.*;

/**
 * Created by MarkAduol on 05-Dec-16.
 */
public class MapUtil {
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {

        List<Map.Entry<K, V>> entries = new ArrayList<>(map.entrySet());

        entries.sort(Comparator.comparing(Map.Entry::getValue));

        Map<K, V> result = new LinkedHashMap<K, V>();

        for (Map.Entry<K, V> entry : entries) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }

    /**
     * Sort in ascending order of set cardinality
     * @param map Our map data structure, consisting of generic keys {@param K}
     *            and values {@param Set<V>}.
     * @param <K> The type of the keys in the map.
     * @param <V> The type of the values in the map
     * @return The same map, sorted in ascending order of the size of the values.
     */
    public static <K, V> Map<K, Set<V>> sortBySetSize(Map<K, Set<V>> map) {
        List<Map.Entry<K, Set<V>>> entries = new ArrayList<>(map.entrySet());

        // The comparator sorts the elements in ascending order of size.
        entries.sort(Comparator.comparingInt(entry2 -> entry2.getValue().size()));

        Map<K, Set<V>> result = new LinkedHashMap<K, Set<V>>();

        for (Map.Entry<K, Set<V>> entry : entries) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }
}
