package com.bluemobi.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jimi
 *         14-5-27 下午4:45
 */
public class CommonUtils {
    public static <T> T single(List<T> list) {
        if (list == null) {
            return null;
        }
        if (list.isEmpty()) {
            return null;
        }
        if (list.size() > 1) {
            throw new AssertionError();
        }
        return list.get(0);
    }

    public static <K, V> Map<K, V> getOrCreateIfAbsent(Map<K, Map<K, V>> mapMap, K key) {
        Map<K, V> map = mapMap.get(key);
        if (map == null) {
            map = new HashMap<K, V>();
            mapMap.put(key, map);
        }
        return map;
    }

    public static Map<Integer, HashMap<Integer, Integer>> addValue(Map<Integer, HashMap<Integer, Integer>> mapMap, int firstKey, int secondKey, int value) {
        HashMap<Integer, Integer> map = mapMap.get(firstKey);
        if (map == null) {
            map = new HashMap<Integer, Integer>();
        }

        if (map.get(secondKey) == null) {
            map.put(secondKey, value);
        } else {
            map.put(secondKey, map.get(secondKey) + value);
        }
        return mapMap;
    }

    public static HashMap<Integer, Integer> addValue(HashMap<Integer, Integer> map, int key, int value) {
        if (map.get(key) == null) {
            map.put(key, value);
        } else {
            map.put(key, map.get(key) + value);
        }
        return map;
    }
}
