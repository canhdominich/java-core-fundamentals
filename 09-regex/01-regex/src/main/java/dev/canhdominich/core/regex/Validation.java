package dev.canhdominich.core.regex;

import java.util.regex.Pattern;

public final class Validation {

    private static final Pattern PHONE = Pattern.compile("0\\d{9}|\\+84\\d{9}");
    private static final Pattern EMAIL = Pattern.compile("[\\w.+-]+@[\\w-]+(\\.[\\w-]+)+");
    private static final Pattern STRONG_PASSWORD = Pattern.compile("(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}");

    private Validation() {
    }

    public static String phones() {
        return "0912345678 " + PHONE.matcher("0912345678").matches() + ", +84912345678 " + PHONE.matcher("+84912345678").matches()
                + ", 091234567 " + PHONE.matcher("091234567").matches() + ", 09123456789 " + PHONE.matcher("09123456789").matches();
    }

    public static String emails() {
        return "an@example.com " + EMAIL.matcher("an@example.com").matches() + ", a.b+c@mail.co.uk " + EMAIL.matcher("a.b+c@mail.co.uk").matches()
                + ", an@ " + EMAIL.matcher("an@").matches() + ", no-at.example.com " + EMAIL.matcher("no-at.example.com").matches()
                + ", a@b..c " + EMAIL.matcher("a@b..c").matches();
    }

    public static String passwords() {
        return "Secret123 " + STRONG_PASSWORD.matcher("Secret123").matches() + ", secret123 " + STRONG_PASSWORD.matcher("secret123").matches()
                + ", Sec1 " + STRONG_PASSWORD.matcher("Sec1").matches();
    }
}
