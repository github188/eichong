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
    
    /** 
     * 计算地球上任意两点(经纬度)距离 
     *  
     * @param long1 
     *            第一点经度 
     * @param lat1 
     *            第一点纬度 
     * @param long2 
     *            第二点经度 
     * @param lat2 
     *            第二点纬度 
     * @return 返回距离 单位：米 
     */  
    public static double Distance(double long1, double lat1, double long2,  double lat2) {  
        double a, b, R;  
        R = 6378137; // 地球半径  
        lat1 = lat1 * Math.PI / 180.0;  
        lat2 = lat2 * Math.PI / 180.0;  
        a = lat1 - lat2;  
        b = (long1 - long2) * Math.PI / 180.0;  
        double d;  
        double sa2, sb2;  
        sa2 = Math.sin(a / 2.0);  
        sb2 = Math.sin(b / 2.0);  
        d = 2 * R * Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat1) * Math.cos(lat2) * sb2 * sb2));  
        
        return d;  
    }  
}
