package dev.canhdominich.core.collections.overview;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public final class Overview {

    private Overview() {
    }

    public static List<String> interfacesOf() {
        List<String> out = new ArrayList<>();
        for (Class<?> type : new Class<?>[] {ArrayList.class, LinkedList.class, HashSet.class, TreeSet.class, ArrayDeque.class, HashMap.class, TreeMap.class}) {
            StringBuilder names = new StringBuilder();
            for (Class<?> iface : type.getInterfaces()) {
                if (names.length() > 0) {
                    names.append(", ");
                }
                names.append(iface.getSimpleName());
            }
            out.add(type.getSimpleName() + " implements " + names);
        }
        return out;
    }

    public static String subInterfaces() {
        return "List extends " + List.class.getInterfaces()[0].getSimpleName() + ", Set extends " + Set.class.getInterfaces()[0].getSimpleName()
                + ", Collection extends " + java.util.Collection.class.getInterfaces()[0].getSimpleName() + ", Map extends nothing: " + (Map.class.getInterfaces().length == 0);
    }

    public static String iterationOrders() {
        String[] words = {"pear", "apple", "fig", "banana", "cherry"};
        Set<String> hash = new HashSet<>();
        Set<String> linked = new LinkedHashSet<>();
        Set<String> tree = new TreeSet<>();
        List<String> list = new ArrayList<>();
        for (String word : words) {
            hash.add(word);
            linked.add(word);
            tree.add(word);
            list.add(word);
        }
        return "list " + list + ", HashSet " + hash + ", LinkedHashSet " + linked + ", TreeSet " + tree;
    }

    public static String duplicatesAndNulls() {
        List<String> list = new ArrayList<>(List.of("a", "b", "a"));
        Set<String> set = new HashSet<>(list);
        Map<String, Integer> hashMap = new HashMap<>();
        hashMap.put(null, 1);
        hashMap.put("x", 2);
        hashMap.put("x", 3);
        String treeResult;
        try {
            new TreeMap<String, Integer>().put(null, 1);
            treeResult = "accepted";
        } catch (NullPointerException e) {
            treeResult = "NullPointerException";
        }
        return "list keeps duplicates " + list.size() + ", set keeps " + set.size() + ", HashMap null key allowed and size " + hashMap.size()
                + " with x=" + hashMap.get("x") + ", TreeMap null key: " + treeResult;
    }

    public static String immutableFactories() {
        List<Integer> fixed = List.of(1, 2, 3);
        String addResult;
        try {
            fixed.add(4);
            addResult = "added";
        } catch (UnsupportedOperationException e) {
            addResult = "UnsupportedOperationException";
        }
        String nullResult;
        try {
            List.of(1, null);
            nullResult = "accepted";
        } catch (NullPointerException e) {
            nullResult = "NullPointerException";
        }
        List<Integer> copy = new ArrayList<>(fixed);
        copy.add(4);
        return "List.of add: " + addResult + ", List.of with null: " + nullResult + ", a mutable copy has " + copy.size() + " elements";
    }

    public static String lookupCost(int size) {
        List<CountedKey> list = new ArrayList<>();
        Set<CountedKey> set = new HashSet<>();
        for (int i = 0; i < size; i++) {
            CountedKey key = new CountedKey(i);
            list.add(key);
            set.add(key);
        }
        CountedKey probe = new CountedKey(size - 1);
        CountedKey.resetCounter();
        boolean inList = list.contains(probe);
        int listCalls = CountedKey.equalsCalls();
        CountedKey.resetCounter();
        boolean inSet = set.contains(probe);
        int setCalls = CountedKey.equalsCalls();
        return "searching for the last of " + size + " keys: list " + inList + " after " + listCalls + " equals calls, HashSet " + inSet + " after " + setCalls + " equals call";
    }

    public static String utilityMethods() {
        List<Integer> numbers = new ArrayList<>(List.of(5, 3, 9, 3, 1));
        Collections.sort(numbers);
        String sorted = numbers.toString();
        Collections.reverse(numbers);
        String reversed = numbers.toString();
        Collections.shuffle(numbers, new Random(42));
        List<Integer> view = Collections.unmodifiableList(numbers);
        String viewResult;
        try {
            view.add(7);
            viewResult = "added";
        } catch (UnsupportedOperationException e) {
            viewResult = "UnsupportedOperationException";
        }
        return "sorted " + sorted + ", reversed " + reversed + ", max " + Collections.max(numbers) + ", min " + Collections.min(numbers)
                + ", frequency of 3 is " + Collections.frequency(numbers, 3) + ", unmodifiable view add: " + viewResult;
    }
}
