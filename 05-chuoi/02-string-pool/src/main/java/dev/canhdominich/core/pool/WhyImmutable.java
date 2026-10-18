package dev.canhdominich.core.pool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WhyImmutable {

    public static String mutableKeyIsLost() {
        List<Integer> key = new ArrayList<>(List.of(1, 2));
        Map<List<Integer>, String> map = new HashMap<>();
        map.put(key, "value");
        String before = "before " + map.get(key);
        key.add(3);
        return before + ", after changing the key: get(key) " + map.get(key) + ", get(List.of(1, 2)) " + map.get(List.of(1, 2))
                + ", get(List.of(1, 2, 3)) " + map.get(List.of(1, 2, 3)) + ", size " + map.size();
    }

    public static String stringKeyIsStable() {
        Map<String, String> map = new HashMap<>();
        String key = "id-42";
        map.put(key, "value");
        key = key + "-changed";
        return "get(id-42) " + map.get("id-42") + ", get(changed variable) " + map.get(key) + ", size " + map.size();
    }

    public static String collisionsStillWork() {
        Map<String, Integer> map = new HashMap<>();
        map.put("Aa", 1);
        map.put("BB", 2);
        return "hash of Aa " + "Aa".hashCode() + ", hash of BB " + "BB".hashCode() + ", equal strings " + "Aa".equals("BB")
                + ", lookups " + map.get("Aa") + " and " + map.get("BB");
    }

    public static String hashIsComputedFromTheContent() {
        String first = "hello";
        String second = new String("hello");
        return "hashCode " + first.hashCode() + " and " + second.hashCode() + " equal " + (first.hashCode() == second.hashCode())
                + ", empty string hash " + "".hashCode();
    }

    public static String arrayIsMutableStringIsNot() {
        char[] letters = {'j', 'a', 'v', 'a'};
        String snapshot = new String(letters);
        letters[0] = 'L';
        return "array " + new String(letters) + ", string made before the change " + snapshot;
    }

    public static void main(String[] args) {
        System.out.println("mutable key       = " + mutableKeyIsLost());
        System.out.println("string key        = " + stringKeyIsStable());
        System.out.println("collisions        = " + collisionsStillWork());
        System.out.println("hash from content = " + hashIsComputedFromTheContent());
        System.out.println("array vs string   = " + arrayIsMutableStringIsNot());
    }
}
