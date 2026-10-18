package dev.canhdominich.core.pool;

import java.util.IdentityHashMap;
import java.util.Map;

public class PoolIdentity {

    public static String identities() {
        String literal = "java";
        String sameLiteral = "java";
        String created = new String("java");
        String interned = created.intern();
        String sliced = "javascript".substring(0, 4);
        String joined = "ja" + new String("va");
        return "literal == literal " + (literal == sameLiteral)
                + ", literal == new String " + (literal == created)
                + ", literal == intern " + (literal == interned)
                + ", literal == substring " + (literal == sliced) + " (equals " + literal.equals(sliced) + ")"
                + ", literal == runtime concat " + (literal == joined)
                + ", literal == runtime concat interned " + (literal == joined.intern());
    }

    public static String methodsThatReturnTheSameObject() {
        String text = "java";
        return "toString " + (text.toString() == text)
                + ", concat empty " + (text.concat("") == text)
                + ", trim without spaces " + (text.trim() == text)
                + ", toLowerCase already lower " + (text.toLowerCase() == text)
                + ", replace missing char " + (text.replace('z', 'y') == text)
                + ", substring(0) " + (text.substring(0) == text)
                + ", toUpperCase " + (text.toUpperCase() == text);
    }

    public static int distinctObjects(int count, boolean intern) {
        Map<String, Boolean> seen = new IdentityHashMap<>();
        for (int i = 0; i < count; i++) {
            String value = new String("shared text");
            seen.put(intern ? value.intern() : value, Boolean.TRUE);
        }
        return seen.size();
    }

    public static int distinctLiteralObjects(int count) {
        Map<String, Boolean> seen = new IdentityHashMap<>();
        for (int i = 0; i < count; i++) {
            seen.put("shared text", Boolean.TRUE);
        }
        return seen.size();
    }

    public static int objectsCreatedByPlusEquals(int rounds) {
        Map<String, Boolean> seen = new IdentityHashMap<>();
        String text = "";
        for (int i = 0; i < rounds; i++) {
            text += "x";
            seen.put(text, Boolean.TRUE);
        }
        return seen.size();
    }

    public static String reassignNotMutate() {
        String original = "cat";
        String alias = original;
        original = original + "s";
        return "original " + original + ", alias " + alias + ", same object " + (original == alias);
    }

    public static void main(String[] args) {
        System.out.println("identities           = " + identities());
        System.out.println("same object returned = " + methodsThatReturnTheSameObject());
        System.out.println("100000 new String    = " + distinctObjects(100_000, false) + " distinct objects");
        System.out.println("100000 intern        = " + distinctObjects(100_000, true) + " distinct object");
        System.out.println("100000 literals      = " + distinctLiteralObjects(100_000) + " distinct object");
        System.out.println("1000 times += \"x\"    = " + objectsCreatedByPlusEquals(1000) + " distinct objects");
        System.out.println("reassign, not mutate = " + reassignNotMutate());
    }
}
