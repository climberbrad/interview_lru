package com.jivesoftware.interview.lru;

/**
 * Generic Cache interface
 *
 * @author Ryan Tyer
 * @version 1.0
 */
public interface Cache<K, V> {
    void put(K key, V item);

    boolean contains(K key);

    V get(K key);
}
