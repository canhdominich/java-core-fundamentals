package dev.canhdominich.core.java8.stream;

public class StreamDemo {

    public static void main(String[] args) {
        System.out.println("basic pipeline    = " + Pipelines.basicPipeline());
        System.out.println("laziness          = " + Pipelines.laziness());
        System.out.println("single use        = " + Pipelines.reuseFails());
        System.out.println("grouping          = " + Pipelines.groupingAndPartitioning());
        System.out.println("toMap             = " + Pipelines.toMapAndDuplicates());
        System.out.println("primitive streams = " + Pipelines.primitiveStreams());
        System.out.println("flatMap, joining  = " + Pipelines.joiningAndFlatMap());
        System.out.println("toList            = " + Pipelines.listsFromStreams());
        System.out.println("source untouched  = " + Pipelines.sourceIsNotModified());
        String wrongCollect = "import java.util.*; import java.util.stream.*; class A { List<String> f(List<String> l) { return l.stream().map(String::length).collect(Collectors.toList()); } }";
        String primitiveMismatch = "import java.util.*; import java.util.stream.*; class A { int f(List<String> l) { return l.stream().map(String::length).sum(); } }";
        String assign = "import java.util.*; class A { void f(List<Integer> l) { int total = 0; l.stream().forEach(n -> total += n); } }";
        System.out.println("collect into the wrong list type = " + Javac.messages("A", wrongCollect));
        System.out.println("sum on a Stream of objects       = " + Javac.messages("A", primitiveMismatch));
        System.out.println("accumulate into a local variable = " + Javac.messages("A", assign));
    }
}
