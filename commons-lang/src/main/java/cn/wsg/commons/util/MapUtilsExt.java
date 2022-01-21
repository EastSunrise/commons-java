package cn.wsg.commons.util;

import java.util.Map;

/**
 * Utility for {@link Map}.
 *
 * @author Kingen
 */
public final class MapUtilsExt {

    private MapUtilsExt() {
    }

    /**
     * Puts the key-value into the map if the key is absent, otherwise throw an exception.
     *
     * @throws IllegalStateException if the key is present in the map
     */
    public static <K, V> void putIfAbsentOrElseThrow(Map<K, V> map, K key, V value) {
        V prev = map.get(key);
        if (prev == null) {
            map.put(key, value);
        } else {
            String format = String.format("Duplicate key %s (attempted merging values %s and %s)", key, prev, value);
            throw new IllegalStateException(format);
        }
    }
}
