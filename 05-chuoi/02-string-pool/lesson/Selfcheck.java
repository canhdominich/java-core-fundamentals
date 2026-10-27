import java.util.IdentityHashMap;
import java.util.Map;

public class Selfcheck {
    static boolean isTaken(String[] usernames, String typed) {
        if (typed == null) {
            return false;
        }
        for (String name : usernames) {
            if (name.equals(typed)) {
                return true;
            }
        }
        return false;
    }

    static boolean isTakenWrong(String[] usernames, String typed) {
        for (String name : usernames) {
            if (name == typed) {
                return true;
            }
        }
        return false;
    }

    static int count(boolean intern, boolean literal) {
        Map<String, Boolean> seen = new IdentityHashMap<>();
        for (int i = 0; i < 100_000; i++) {
            String v = literal ? "shared text" : new String("shared text");
            seen.put(intern ? v.intern() : v, Boolean.TRUE);
        }
        return seen.size();
    }

    public static void main(String[] args) {
        String[] users = {"an", "binh", "chi"};
        System.out.println("b4 " + isTaken(users, new String("binh")) + " " + isTaken(users, "dung") + " " + isTaken(users, null) + " wrong " + isTakenWrong(users, new String("binh")));
        System.out.println("b5 " + count(false, false) + " " + count(true, false) + " " + count(false, true));
        System.out.println("b3 " + "ab".hashCode());
    }
}
