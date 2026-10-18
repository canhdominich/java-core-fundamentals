package dev.canhdominich.core.collections.setmap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SetMapDemo {

    public static String lostKey() {
        Map<BadPoint, String> bad = new HashMap<>();
        bad.put(new BadPoint(3, 4), "home");
        Map<GoodPoint, String> good = new HashMap<>();
        good.put(new GoodPoint(3, 4), "home");
        return "equals only: get " + bad.get(new BadPoint(3, 4)) + " | equals and hashCode: get " + good.get(new GoodPoint(3, 4));
    }

    public static String lruCache() {
        LruCache<String, Integer> cache = new LruCache<>(3);
        cache.put("a", 1);
        cache.put("b", 2);
        cache.put("c", 3);
        cache.get("a");
        cache.put("d", 4);
        return "capacity 3, after touching a and adding d the cache holds " + cache.keySet() + " and b was evicted: " + !cache.containsKey("b");
    }

    public static String badHashCost(int size) {
        Set<ConstantHashKey> set = new HashSet<>();
        for (int i = 0; i < size; i++) {
            set.add(new ConstantHashKey(i));
        }
        ConstantHashKey.reset();
        set.contains(new ConstantHashKey(size - 1));
        int calls = ConstantHashKey.calls();
        return size + " keys with the same hashCode: one lookup made more than " + size / 20 + " equals calls: " + (calls > size / 20) + " (the exact number varies from run to run)";
    }

    public static void main(String[] args) {
        System.out.println("set algebra     = " + Sets.algebra());
        System.out.println("add             = " + Sets.addReturnsWhetherItChanged());
        System.out.println("contract        = " + Sets.contractWithoutHashCode());
        System.out.println("tree set        = " + Sets.treeSetOperations());
        System.out.println("map basics      = " + Maps.basics());
        System.out.println("word count      = " + Maps.wordCount());
        System.out.println("group by        = " + Maps.groupBy());
        System.out.println("iteration       = " + Maps.iteration());
        System.out.println("tree map        = " + Maps.treeMapNavigation());
        System.out.println("views           = " + Maps.viewsWriteThrough());
        System.out.println("immutable       = " + Maps.immutableMap());
        System.out.println("lost key        = " + lostKey());
        System.out.println("LRU cache       = " + lruCache());
        System.out.println("one bad hash    = " + badHashCost(1000));
    }
}
