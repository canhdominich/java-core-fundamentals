package dev.canhdominich.core.java8.lambda;

import java.util.function.Supplier;

public class ThisMeaning {

    private final String name = "outer object";

    public String insideLambda() {
        Supplier<String> supplier = () -> "this is the " + this.name;
        return supplier.get();
    }

    public String insideAnonymousClass() {
        Supplier<String> supplier = new Supplier<>() {
            private final String name = "anonymous object";

            @Override
            public String get() {
                return "this is the " + this.name;
            }
        };
        return supplier.get();
    }
}
