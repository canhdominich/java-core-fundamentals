package dev.canhdominich.core.java8.optional;

public class OptionalDemo {

    public static void main(String[] args) {
        System.out.println("creating            = " + Basics.creating());
        System.out.println("get on empty        = " + Basics.getOnEmpty());
        System.out.println("orElse vs orElseGet = " + Basics.eagerVersusLazyDefault());
        System.out.println("orElseThrow         = " + Basics.orElseThrow());
        System.out.println("chaining            = " + Basics.chaining());
        System.out.println("nested lookups      = " + Basics.nestedLookups());
        System.out.println("in streams          = " + Basics.optionalInStreams());
        System.out.println("anti-pattern        = " + Basics.antiPattern());
        String wrongType = "import java.util.*; class A { String f(Optional<String> o) { return o; } }";
        String mapToOptional = "import java.util.*; class A { Optional<String> f(Optional<String> o) { Optional<Optional<String>> nested = o.map(s -> Optional.of(s)); return nested; } }";
        String optionalField = "import java.util.*; class A { Optional<String> name; }";
        System.out.println("Optional where a String is expected = " + Javac.messages("A", wrongType));
        System.out.println("map that returns an Optional        = " + Javac.messages("A", mapToOptional));
        System.out.println("an Optional field compiles          = " + Javac.messages("A", optionalField));
    }
}
