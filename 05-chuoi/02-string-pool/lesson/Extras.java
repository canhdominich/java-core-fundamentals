import java.util.ArrayList;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

public class Extras {
    static int distinct(int count, boolean intern, boolean literal) {
        Map<String, Boolean> seen = new IdentityHashMap<>();
        for (int i = 0; i < count; i++) {
            String value = literal ? "shared text" : new String("shared text");
            seen.put(intern ? value.intern() : value, Boolean.TRUE);
        }
        return seen.size();
    }

    public static void main(String[] args) {
        System.out.println("100000 new String " + distinct(100_000, false, false));
        System.out.println("100000 intern " + distinct(100_000, true, false));
        System.out.println("100000 literal " + distinct(100_000, false, true));
        Map<String, Boolean> seen = new IdentityHashMap<>();
        String text = "";
        for (int i = 0; i < 1000; i++) {
            text += "x";
            seen.put(text, Boolean.TRUE);
        }
        System.out.println("1000 times += " + seen.size() + " distinct objects");
        String same = "java";
        System.out.println("toString " + (same.toString() == same) + ", concat empty " + (same.concat("") == same) + ", trim " + (same.trim() == same) + ", substring(0) " + (same.substring(0) == same) + ", toUpperCase " + (same.toUpperCase() == same));
        Map<List<Integer>, String> byList = new HashMap<>();
        List<Integer> key = new ArrayList<>(List.of(1, 2));
        byList.put(key, "value");
        key.add(3);
        System.out.println("mutable key: get(key) " + byList.get(key) + ", size " + byList.size());
        Map<String, String> byString = new HashMap<>();
        String id = "id-42";
        byString.put(id, "value");
        id = id + "!";
        System.out.println("string key: get(id-42) " + byString.get("id-42") + ", get(changed variable) " + byString.get(id));
    }
}
