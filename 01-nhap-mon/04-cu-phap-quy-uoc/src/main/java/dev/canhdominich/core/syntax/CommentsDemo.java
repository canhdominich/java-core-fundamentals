package dev.canhdominich.core.syntax;

import java.security.MessageDigest;
import java.util.HexFormat;

public class CommentsDemo {

    static final String PLAIN = "public class A { static int twice(int x) { return x * 2; } }";

    static final String SAME_LINE_COMMENT = "public class A { static int twice(int x) { return x * 2; /* doubles the number */ } }";

    static final String COMMENT_LINES = """
            public class A {
                // doubles the number
                /* a longer
                   explanation */
                static int twice(int x) { return x * 2; }
            }
            """;

    static String fingerprint(String source, String... options) throws Exception {
        byte[] bytes = SourceChecks.compile("A", source, options).classes().get("A");
        return HexFormat.of().formatHex(MessageDigest.getInstance("SHA-256").digest(bytes)).substring(0, 12);
    }

    public static void main(String[] args) throws Exception {
        System.out.println("-g:none (no debug info)");
        System.out.println("  plain                   " + fingerprint(PLAIN, "-g:none"));
        System.out.println("  comment on the same line " + fingerprint(SAME_LINE_COMMENT, "-g:none"));
        System.out.println("  comments on other lines  " + fingerprint(COMMENT_LINES, "-g:none"));
        System.out.println("default debug info (line numbers kept)");
        System.out.println("  plain                   " + fingerprint(PLAIN));
        System.out.println("  comment on the same line " + fingerprint(SAME_LINE_COMMENT));
        System.out.println("  comments on other lines  " + fingerprint(COMMENT_LINES));
    }
}
