package dev.canhdominich.core.generics.basics;

import java.util.ArrayList;
import java.util.List;

public class GenericsDemo {

    public static String beforeGenerics() {
        ObjectBox box = new ObjectBox("text");
        try {
            Integer number = (Integer) box.get();
            return "got " + number;
        } catch (ClassCastException e) {
            return "the mistake shows up only at run time: ClassCastException";
        }
    }

    public static String withGenerics() {
        Box<String> box = new Box<>("text");
        String value = box.get();
        return "no cast needed: " + value + ", length " + value.length();
    }

    public static String genericMethods() {
        List<String> words = List.of("pear", "apple", "fig");
        List<Integer> numbers = List.of(7, 42, 3);
        return "first " + Util.first(words) + " and " + Util.first(numbers) + ", max " + Util.max(words) + " and " + Util.max(numbers)
                + ", listOf " + Util.listOf(1, 2, 3) + ", pair " + Util.pair("a", 1) + ", swapped " + Util.pair("a", 1).swapped();
    }

    public static String mappingABox() {
        Box<String> text = new Box<>("12345");
        Box<Integer> length = text.map(String::length);
        Box<String> label = length.map(n -> "length=" + n);
        return "Box<String> to Box<Integer> to Box<String>: " + length.get() + " then " + label.get();
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static String heapPollution() {
        List<String> names = new ArrayList<>();
        List raw = names;
        raw.add(42);
        try {
            String first = names.get(0);
            return "read " + first;
        } catch (ClassCastException e) {
            return "the Integer slipped in through a raw type and failed at the read, naming both classes: "
                    + e.getMessage().startsWith("class java.lang.Integer cannot be cast to class java.lang.String");
        }
    }

    public static String boxingIdentity() {
        Integer smallA = 127;
        Integer smallB = 127;
        Integer bigA = 128;
        Integer bigB = 128;
        return "127 == 127 is " + (smallA == smallB) + ", 128 == 128 is " + (bigA == bigB) + ", equals gives " + bigA.equals(bigB);
    }

    public static String noPrimitives() {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(5);
        int unboxed = numbers.get(0);
        Integer nothing = null;
        try {
            int boom = nothing;
            return "no failure " + boom;
        } catch (NullPointerException e) {
            return "primitives are boxed as Integer: stored " + numbers + ", read back " + unboxed + ", and unboxing null throws NullPointerException";
        }
    }

    public static void main(String[] args) {
        System.out.println("before generics   = " + beforeGenerics());
        System.out.println("with generics     = " + withGenerics());
        System.out.println("generic methods   = " + genericMethods());
        System.out.println("mapping a box     = " + mappingABox());
        System.out.println("heap pollution    = " + heapPollution());
        System.out.println("boxing identity   = " + boxingIdentity());
        System.out.println("primitives        = " + noPrimitives());
        String wrongType = "import java.util.*; class A { void f() { List<String> l = new ArrayList<>(); l.add(5); } }";
        String primitive = "import java.util.*; class A { List<int> l; }";
        String invariance = "import java.util.*; class A { void f() { List<Integer> ints = new ArrayList<>(); List<Number> nums = ints; } }";
        String newT = "class A<T> { T make() { return new T(); } }";
        String newArray = "class A<T> { T[] make() { return new T[3]; } }";
        String staticField = "class A<T> { static T shared; }";
        String instanceOfGeneric = "import java.util.*; class A { boolean f(Object o) { return o instanceof List<String>; } }";
        String boundViolated = "class Box2<T extends Comparable<T>> { } class A { Box2<Object> b; }";
        System.out.println("wrong element type     = " + Javac.messages("A", wrongType));
        System.out.println("primitive type argument= " + Javac.messages("A", primitive));
        System.out.println("List<Integer> to List<Number> = " + Javac.messages("A", invariance));
        System.out.println("new T()                = " + Javac.messages("A", newT));
        System.out.println("new T[3]               = " + Javac.messages("A", newArray));
        System.out.println("static field of type T = " + Javac.messages("A", staticField));
        System.out.println("instanceof List<String>= " + Javac.messages("A", instanceOfGeneric));
        System.out.println("bound violated         = " + Javac.messages("A", boundViolated));
    }
}
