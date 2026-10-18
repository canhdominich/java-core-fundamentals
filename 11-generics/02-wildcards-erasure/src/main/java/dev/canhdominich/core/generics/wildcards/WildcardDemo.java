package dev.canhdominich.core.generics.wildcards;

import java.util.List;

public class WildcardDemo {

    public static void main(String[] args) throws Exception {
        System.out.println("bounded sum        = " + Bounds.sum(List.of(1, 2, 3)) + " and " + Bounds.sum(List.of(1.5, 2.5)));
        System.out.println("two bounds         = " + Bounds.largest(List.of(3, 9, 4)) + " and " + Bounds.largest(List.of(2.5, 1.5)));
        System.out.println("wildcards          = " + Wildcards.usage());
        System.out.println("erasure            = " + Erasure.sameClassAtRunTime());
        System.out.println("erased bound       = " + Erasure.erasureOfTypeParameters());
        System.out.println("bridge method      = " + Erasure.bridgeMethods());
        System.out.println("array via token    = " + Erasure.arrayThroughAClassToken());
        System.out.println("instance via token = " + Erasure.instanceThroughAClassToken());
        String addToExtends = "import java.util.*; class A { void f(List<? extends Number> l) { l.add(5); } }";
        String addNullToExtends = "import java.util.*; class A { void f(List<? extends Number> l) { l.add(null); } }";
        String readFromSuper = "import java.util.*; class A { Integer f(List<? super Integer> l) { return l.get(0); } }";
        String readAsObject = "import java.util.*; class A { Object f(List<? super Integer> l) { return l.get(0); } }";
        String invariance = "import java.util.*; class A { void f(List<Integer> i) { List<Number> n = i; } }";
        String covariant = "import java.util.*; class A { void f(List<Integer> i) { List<? extends Number> n = i; } }";
        String sameErasure = "import java.util.*; class A { void f(List<String> l) { } void f(List<Integer> l) { } }";
        String boundMissing = "class A { <T> double f(T t) { return t.doubleValue(); } }";
        String catchT = "class A<T extends Throwable> { void f() { try { } catch (T e) { } } }";
        System.out.println("add to List<? extends Number>  = " + Javac.messages("A", addToExtends));
        System.out.println("add null to the same list      = " + Javac.messages("A", addNullToExtends));
        System.out.println("read Integer from ? super      = " + Javac.messages("A", readFromSuper));
        System.out.println("read Object from ? super       = " + Javac.messages("A", readAsObject));
        System.out.println("List<Integer> to List<Number>  = " + Javac.messages("A", invariance));
        System.out.println("List<Integer> to ? extends     = " + Javac.messages("A", covariant));
        System.out.println("same erasure                   = " + Javac.messages("A", sameErasure));
        System.out.println("method on an unbounded T       = " + Javac.messages("A", boundMissing));
        System.out.println("catch a type parameter         = " + Javac.messages("A", catchT));
    }
}
