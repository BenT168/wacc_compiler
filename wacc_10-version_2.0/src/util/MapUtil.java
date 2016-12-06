package util;

import java.util.*;

/**
 * Created by MarkAduol on 05-Dec-16.
 */
public class MapUtil {
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {

        List<Map.Entry<K, V>> entries = new ArrayList<>(map.entrySet());
        Collections.sort(entries, new Comparator<Map.Entry<K, V>>() {
            @Override
            public int compare(Map.Entry<K, V> entry1, Map.Entry<K, V> entry2) {
                return entry1.getValue().compareTo(entry2.getValue());
            }
        });
        Map<K, V> result = new LinkedHashMap<K, V>();
        for (Map.Entry<K, V> entry : entries) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    public static <K, V> Map<K, Set<V>> sortBySetSize(Map<K, Set<V>> map) {
        List<Map.Entry<K, Set<V>>> entries = new ArrayList<>(map.entrySet());
        Collections.sort(entries, new Comparator<Map.Entry<K, Set<V>>>() {
            @Override
            public int compare(Map.Entry<K, Set<V>> entry1, Map.Entry<K, Set<V>> entry2) {
                return entry2.getValue().size() - entry1.getValue().size();
            }
        });
        Map<K, Set<V>> result = new LinkedHashMap<K, Set<V>>();
        for (Map.Entry<K, Set<V>> entry : entries) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
}
