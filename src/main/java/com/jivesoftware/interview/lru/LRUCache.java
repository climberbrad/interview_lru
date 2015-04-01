package com.jivesoftware.interview.lru;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Implementation of a LRU cache (Least Recently Used).
 *
 * @author YOUR_NAME_HERE
 */
public class LRUCache<K,V> implements Cache<K,V> {
    static final int DEFAULT_CAPACITY = 10000;
    static final int DEFAULT_AGE = 10;
    static final TimeUnit DEFAULT_UNIT = TimeUnit.MINUTES;

    private Map<K,V> backing = new HashMap<K,V>();
    private int maxCapacity;
    private int evictionAge;
    private TimeUnit unit;

    public LRUCache(){
        this(DEFAULT_CAPACITY);
    }

    public LRUCache(int maxCapacity) {
        this(maxCapacity, DEFAULT_AGE, DEFAULT_UNIT);
    }

    public LRUCache(int maxCapacity, int evictionAge, TimeUnit unit) {
        this.maxCapacity = maxCapacity;
        this.evictionAge = evictionAge;
        this.unit = unit;
    }

    @Override
    public void put(K key, V item) {
        backing.put(key, item);
    }

    @Override
    public boolean contains(K key) {
        return backing.containsKey(key);
    }

    @Override
    public V get(K key) {
        return backing.get(key);
    }
}
