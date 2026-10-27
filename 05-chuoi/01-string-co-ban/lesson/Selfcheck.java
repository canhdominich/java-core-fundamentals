public class Selfcheck {
    static boolean isYes(String input) {
        return "yes".equalsIgnoreCase(input);
    }

    static String email(String raw) {
        String e = raw.trim().toLowerCase();
        int at = e.indexOf('@');
        if (at <= 0 || !e.substring(at + 1).contains(".")) {
            return "invalid";
        }
        return e;
    }

    static boolean palindrome(String text) {
        String s = text.toLowerCase();
        for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("b3 " + isYes("YES") + " " + isYes("no") + " " + isYes(null));
        System.out.println("b4 " + email(" An.Nguyen@Example.com ") + " | " + email("bad-email") + " | " + email("@x.com"));
        System.out.println("b5 " + palindrome("level") + " " + palindrome("Java") + " " + palindrome("Racecar"));
    }
}
