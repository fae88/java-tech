package com.gemini.cloud.service.guava.cache.test.collection;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import org.junit.Test;

public class CollectionTest {

    @Test
    public void testMultiSet() {

        Multiset<String> multiset = HashMultiset.create();

        multiset.add("b");
        multiset.add("b");
        multiset.add("c");
        multiset.add("d");

        multiset.forEach((e) -> {
            System.out.println(e);
        });
    }

    @Test
    public void testBiMap() {

        BiMap<String, String> biMap = HashBiMap.create();

        biMap.put("aa", "cc");
        biMap.put("bb", "dd");
        biMap.put("ee", "ff");

        System.out.println(biMap.values());
    }
}
