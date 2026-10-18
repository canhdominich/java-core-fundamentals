package dev.canhdominich.core.java8.lambda;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public final class Syntax {

    private Syntax() {
    }

    public static String forms() {
        Function<Integer, Integer> square = n -> n * n;
        BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
        Supplier<String> hello = () -> "hello";
        Consumer<String> sink = text -> { };
        UnaryOperator<String> shout = text -> text.toUpperCase() + "!";
        Predicate<String> empty = String::isEmpty;
        Function<Integer, Integer> block = n -> {
            int doubled = n * 2;
            return doubled + 1;
        };
        sink.accept("ignored");
        return "square(7) " + square.apply(7) + ", add(2, 3) " + add.apply(2, 3) + ", hello " + hello.get() + ", shout " + shout.apply("hey")
                + ", empty(\"\") " + empty.test("") + ", block(5) " + block.apply(5);
    }

    public static String methodReferences() {
        Function<String, Integer> staticRef = Integer::parseInt;
        String prefix = "Dr. ";
        Function<String, String> boundRef = prefix::concat;
        Function<String, Integer> unboundRef = String::length;
        Supplier<List<String>> constructorRef = ArrayList::new;
        BiFunction<String, String, Boolean> unboundTwo = String::equalsIgnoreCase;
        return "static " + staticRef.apply("42") + ", bound instance " + boundRef.apply("Who") + ", unbound instance " + unboundRef.apply("four")
                + ", constructor " + constructorRef.get() + ", unbound with an argument " + unboundTwo.apply("A", "a");
    }

    public static String composition() {
        Function<Integer, Integer> plusOne = n -> n + 1;
        Function<Integer, Integer> timesTwo = n -> n * 2;
        Predicate<Integer> positive = n -> n > 0;
        Predicate<Integer> even = n -> n % 2 == 0;
        return "andThen(plusOne, timesTwo) of 5 is " + plusOne.andThen(timesTwo).apply(5) + ", compose is " + plusOne.compose(timesTwo).apply(5)
                + ", positive and even for 4 " + positive.and(even).test(4) + ", positive and even for -4 " + positive.and(even).test(-4)
                + ", negate of even for 3 " + even.negate().test(3);
    }

    public static String capturingVariables() {
        int base = 10;
        Function<Integer, Integer> addBase = n -> n + base;
        int[] counter = {0};
        Runnable increment = () -> counter[0]++;
        increment.run();
        increment.run();
        return "captured a final value: " + addBase.apply(5) + ", changed a captured array element twice: " + counter[0];
    }

    public static String laziness() {
        int[] calls = {0};
        Supplier<String> expensive = () -> {
            calls[0]++;
            return "computed";
        };
        String eagerArgument = pick(true, "cheap", expensive.get());
        int afterEager = calls[0];
        String lazyArgument = pickLazy(true, "cheap", expensive);
        int afterLazy = calls[0];
        return "eager argument ran the expensive code " + afterEager + " time, lazy supplier added " + (afterLazy - afterEager) + " more";
    }

    static String pick(boolean useFirst, String first, String second) {
        return useFirst ? first : second;
    }

    static String pickLazy(boolean useFirst, String first, Supplier<String> second) {
        return useFirst ? first : second.get();
    }

    public static String sortWithLambdas() {
        List<String> words = new ArrayList<>(List.of("kiwi", "fig", "banana", "apple"));
        words.sort((a, b) -> Integer.compare(a.length(), b.length()));
        String byLength = words.toString();
        words.sort(Comparator.naturalOrder());
        return "by length " + byLength + ", natural " + words;
    }
}
