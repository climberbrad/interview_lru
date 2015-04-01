package com.jivesoftware.interview.lru;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Tests for {@link Cache}
 *
 * @author Ryan Tyer
 * @version 1.0
 */
public class CacheTest {

    @Test
    public void containsReturnsFalseWhenEmpty(){
        assertFalse(new LRUCache<Integer,Object>().contains(1));
    }

    @Test
    public void containsReturnsFalseWhenEntryNotPresent(){
        Cache<Integer, String> cache = new LRUCache<Integer,String>();

        cache.put(5, "Test Value");

        assertFalse(cache.contains(1));
    }

    @Test
    public void containsReturnsTrueWhenMatchingValueAdded(){
        Cache<Integer, String> cache = new LRUCache<Integer,String>();

        int key = 5;
        cache.put(key, "Test Value");

        assertTrue(cache.contains(key));
    }

    @Test
    public void willCacheSingleItem(){
        Cache<Integer,String> cache = new LRUCache<Integer,String>();

        Integer key = 1;
        String item = "A string";

        cache.put(key, item);

        assertEquals(item, cache.get(key));
    }

}
