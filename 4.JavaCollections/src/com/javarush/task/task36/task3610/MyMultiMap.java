package com.javarush.task.task36.task3610;

import java.io.Serializable;
import java.util.*;

public class MyMultiMap<K, V> extends HashMap<K, V> implements Cloneable, Serializable {
    static final long serialVersionUID = 123456789L;
    private HashMap<K, List<V>> map;
    private int repeatCount;

    public MyMultiMap(int repeatCount) {
        this.repeatCount = repeatCount;
        map = new HashMap<>();
    }

    @Override
    public int size() {
        return values().size();
    }

    @Override
    public V put(K key, V value) {
        if (map.containsKey(key)) {
            List<V> exist = map.get(key);
            if (exist.size() == repeatCount) exist.remove(0);
            exist.add(value);

            return exist.get(exist.size() - 2);
        } else {
            List<V> valueList = new ArrayList<>();
            valueList.add(value);
            map.put(key, valueList);

            return null;
        }
    }

    @Override
    public V remove(Object key) {
        K mapKey = (K) key;
        if (!map.containsKey(mapKey)) return null;

        List<V> exist = map.get(mapKey);

        V value = exist.remove(0);
        if (exist.size() == 0) {
            map.remove(mapKey);
        }

        return value;
    }

    @Override
    public Set<K> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<V> values() {
        Collection<V> values = new ArrayList<>();
        for (Collection<V> exist : map.values()) {
            values.addAll(exist);
        }

        return values;
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return values().contains(value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            for (V v : entry.getValue()) {
                sb.append(v);
                sb.append(", ");
            }
        }
        String substring = sb.substring(0, sb.length() - 2);
        return substring + "}";
    }
}