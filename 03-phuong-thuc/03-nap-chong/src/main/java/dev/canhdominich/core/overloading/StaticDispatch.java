package dev.canhdominich.core.overloading;

import java.util.ArrayList;
import java.util.List;

public class StaticDispatch {

    static String show(Object value) {
        return "Object";
    }

    static String show(String value) {
        return "String";
    }

    static String pick(String value) {
        return "String";
    }

    static String pick(StringBuilder value) {
        return "StringBuilder";
    }

    static String pick(Object value) {
        return "Object";
    }

    public static List<Integer> removeByIndex(List<Integer> list, int index) {
        List<Integer> copy = new ArrayList<>(list);
        copy.remove(index);
        return copy;
    }

    public static List<Integer> removeByValue(List<Integer> list, Integer value) {
        List<Integer> copy = new ArrayList<>(list);
        copy.remove(value);
        return copy;
    }

    public static void main(String[] args) {
        Object runtimeString = "text";
        System.out.println("show(\"text\")                 = " + show("text"));
        System.out.println("show(Object holding \"text\")  = " + show(runtimeString));
        System.out.println("show((String) runtimeString) = " + show((String) runtimeString));
        System.out.println("show(null)                   = " + show(null));
        System.out.println("pick(\"text\"), pick(new StringBuilder()), pick(5) = " + pick("text") + ", " + pick(new StringBuilder()) + ", " + pick(5));
        List<Integer> list = List.of(10, 20, 30, 1);
        System.out.println("list                         = " + list);
        System.out.println("remove(1) as an index        = " + removeByIndex(list, 1));
        System.out.println("remove(Integer 1) as a value = " + removeByValue(list, 1));
        String pickNull = "class A { String pick(String s) { return \"s\"; } String pick(StringBuilder b) { return \"b\"; } String g() { return pick(null); } }";
        String duplicate = "class A { int f(int x) { return x; } int f(int y) { return y; } }";
        String byReturn = "class A { int f(int x) { return x; } long f(int x) { return x; } }";
        String none = "class A { int f(int x) { return x; } int f(double x) { return 0; } int g() { return f(\"a\"); } }";
        String longArg = "class A { int f(int x) { return x; } int g() { return f(5L); } }";
        System.out.println("pick(null) with String and StringBuilder = " + Javac.messages("A", pickNull));
        System.out.println("same parameter types, other names        = " + Javac.messages("A", duplicate));
        System.out.println("only the return type differs             = " + Javac.messages("A", byReturn));
        System.out.println("no overload matches                      = " + Javac.messages("A", none));
        System.out.println("long argument, only an int overload     = " + Javac.messages("A", longArg));
    }
}
