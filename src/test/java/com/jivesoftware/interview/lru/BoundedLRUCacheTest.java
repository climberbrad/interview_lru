package com.jivesoftware.interview.lru;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Tests for {@link LRUCache} that enforce bounded size behavior.
 *
 * @author Ryan Tyer
 * @version 1.0
 */
public class BoundedLRUCacheTest {

    @Test
    public void keepsAllMembersWhenAddingBelowCapacity(){
        final int MAX_CAPACITY = 2;

        Cache<Integer,String> cache = new LRUCache<Integer,String>(MAX_CAPACITY);

        cache.put(1, "Test Value");
        cache.put(2, "Test Value 2");

        assertTrue(cache.contains(1));
        assertTrue(cache.contains(2));
    }

    @Test
    public void evictsOldestWithNoOtherActivity(){
        final int MAX_CAPACITY = 2;

        Cache<Integer,String> cache = new LRUCache<Integer,String>(MAX_CAPACITY);

        cache.put(1, "Test Value");
        cache.put(2, "Test Value 2");
        cache.put(3, "Test Value 3");  //should trigger eviction

        assertFalse("value should be evicted", cache.contains(1));
        assertTrue(cache.contains(2));
        assertTrue(cache.contains(3));
    }

    @Test
    public void evictsLeastRecentlyUsed(){
        final int MAX_CAPACITY = 2;

        Cache<Integer,String> cache = new LRUCache<Integer,String>(MAX_CAPACITY);

        cache.put(1, "Test Value");
        cache.put(2, "Test Value 2");

        cache.get(1); //makes object with key '1' more recently used than object with key '2'

        cache.put(3, "Test Value 3");  //should trigger eviction

        assertFalse("value should be evicted", cache.contains(2));
        assertTrue(cache.contains(1));
        assertTrue(cache.contains(3));
    }

    @Test
    public void evictsEntryDueToAge() throws InterruptedException {
        Cache<Integer,String> cache = new LRUCache<Integer,String>(LRUCache.DEFAULT_CAPACITY, 10, TimeUnit.MILLISECONDS);

        cache.put(1, "Test Value");

        Thread.sleep(11);

        assertFalse("should have been evicted due to age", cache.contains(1));
    }


}
