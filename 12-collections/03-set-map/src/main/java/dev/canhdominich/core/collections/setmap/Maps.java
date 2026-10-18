package dev.canhdominich.core.collections.setmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public final class Maps {

    private Maps() {
    }

    public static String basics() {
        Map<String, Integer> stock = new HashMap<>();
        Integer first = stock.put("apple", 5);
        Integer replaced = stock.put("apple", 8);
        stock.putIfAbsent("apple", 100);
        stock.putIfAbsent("pear", 3);
        return "first put returns " + first + ", second returns the old value " + replaced + ", putIfAbsent left apple at " + stock.get("apple")
                + ", get(missing) " + stock.get("kiwi") + ", getOrDefault " + stock.getOrDefault("kiwi", 0) + ", containsKey " + stock.containsKey("pear");
    }

    public static String wordCount() {
        String text = "to be or not to be";
        Map<String, Integer> counts = new TreeMap<>();
        for (String word : text.split(" ")) {
            counts.merge(word, 1, Integer::sum);
        }
        return counts.toString();
    }

    public static String groupBy() {
        Map<Integer, List<String>> byLength = new TreeMap<>();
        for (String word : new String[] {"fig", "pear", "kiwi", "plum", "date", "apple"}) {
            byLength.computeIfAbsent(word.length(), length -> new ArrayList<>()).add(word);
        }
        return byLength.toString();
    }

    public static String iteration() {
        Map<String, Integer> prices = new TreeMap<>(Map.of("tea", 3, "coffee", 5, "milk", 2));
        StringBuilder out = new StringBuilder();
        for (Map.Entry<String, Integer> entry : prices.entrySet()) {
            out.append(entry.getKey()).append('=').append(entry.getValue()).append(' ');
        }
        return out + "| keys " + prices.keySet() + " | values " + prices.values();
    }

    public static String treeMapNavigation() {
        TreeMap<Integer, String> grades = new TreeMap<>(Map.of(0, "F", 50, "C", 65, "B", 80, "A"));
        return "floorEntry(79) " + grades.floorEntry(79) + ", ceilingKey(51) " + grades.ceilingKey(51) + ", firstKey " + grades.firstKey()
                + ", headMap(65) " + grades.headMap(65);
    }

    public static String viewsWriteThrough() {
        Map<String, Integer> map = new HashMap<>(Map.of("a", 1, "b", 2));
        map.keySet().remove("a");
        map.values().remove(2);
        return "after removing through keySet and values the map is " + map;
    }

    public static String immutableMap() {
        Map<String, Integer> fixed = Map.of("a", 1);
        try {
            fixed.put("b", 2);
            return "added";
        } catch (UnsupportedOperationException e) {
            return "Map.of is immutable: UnsupportedOperationException";
        }
    }
}
