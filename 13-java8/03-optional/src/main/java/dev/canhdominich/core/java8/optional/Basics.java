package dev.canhdominich.core.java8.optional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public final class Basics {

    private Basics() {
    }

    public static String creating() {
        String missing = null;
        String ofNull;
        try {
            Optional.of(missing);
            ofNull = "no exception";
        } catch (NullPointerException e) {
            ofNull = "Optional.of(null) throws NullPointerException";
        }
        return ofNull + ", ofNullable(null) is " + Optional.ofNullable(missing) + ", of(\"x\") prints as " + Optional.of("x") + ", isPresent " + Optional.of("x").isPresent()
                + ", isEmpty " + Optional.empty().isEmpty();
    }

    public static String getOnEmpty() {
        try {
            Optional.empty().get();
            return "no exception";
        } catch (NoSuchElementException e) {
            return "get on an empty Optional: NoSuchElementException with message '" + e.getMessage() + "'";
        }
    }

    public static String eagerVersusLazyDefault() {
        List<String> trace = new ArrayList<>();
        Optional<String> present = Optional.of("real");
        present.orElse(expensive(trace));
        int afterOrElse = trace.size();
        present.orElseGet(() -> expensive(trace));
        int afterOrElseGet = trace.size();
        Optional<String> absent = Optional.empty();
        String lazy = absent.orElseGet(() -> expensive(trace));
        return "orElse on a present value built the default " + afterOrElse + " time, orElseGet built it " + (afterOrElseGet - afterOrElse) + " more times, and built it when the value was absent: " + lazy + " (" + (trace.size() - afterOrElseGet) + " time)";
    }

    private static String expensive(List<String> trace) {
        trace.add("built");
        return "default";
    }

    public static String orElseThrow() {
        try {
            Users.findByName("Zed").orElseThrow(() -> new IllegalArgumentException("no user called Zed"));
            return "found";
        } catch (IllegalArgumentException e) {
            return "orElseThrow raised: " + e.getMessage();
        }
    }

    public static String chaining() {
        Optional<User> an = Users.findByName("An");
        String nick = an.flatMap(User::nickname).map(String::toUpperCase).orElse("no nickname");
        String filtered = an.map(User::name).filter(name -> name.startsWith("B")).orElse("filtered out");
        String fallback = Users.findByName("Chi").flatMap(User::nickname).or(() -> Optional.of("guest")).get();
        StringBuilder side = new StringBuilder();
        an.ifPresent(user -> side.append("present:").append(user.name()));
        Users.findByName("Zed").ifPresentOrElse(user -> side.append("present"), () -> side.append(" absent"));
        return "nickname " + nick + ", filter " + filtered + ", or " + fallback + ", side effects " + side;
    }

    public static String nestedLookups() {
        return "An " + Users.cityWithOptional("An") + ", Binh (no city) " + Users.cityWithOptional("Binh") + ", Chi (no address) " + Users.cityWithOptional("Chi")
                + ", Zed (no user) " + Users.cityWithOptional("Zed")
                + " | with null checks: An " + Users.cityWithNullChecks(Users.findByName("An").orElse(null)) + ", Chi " + Users.cityWithNullChecks(Users.findByName("Chi").orElse(null))
                + ", null " + Users.cityWithNullChecks(null);
    }

    public static String optionalInStreams() {
        List<Optional<String>> nicknames = new ArrayList<>();
        for (String name : new String[] {"An", "Binh", "Chi"}) {
            nicknames.add(Users.findByName(name).flatMap(User::nickname));
        }
        List<String> present = nicknames.stream().flatMap(Optional::stream).toList();
        return "Optional.stream turns empty into nothing: " + present + ", equality: " + Optional.of("a").equals(Optional.of("a")) + " and " + Optional.empty().equals(Optional.empty());
    }

    public static String antiPattern() {
        Optional<User> user = Users.findByName("An");
        String clumsy;
        if (user.isPresent()) {
            clumsy = user.get().name();
        } else {
            clumsy = "none";
        }
        String idiomatic = user.map(User::name).orElse("none");
        return "isPresent then get gives " + clumsy + ", map then orElse gives " + idiomatic + " (same result, no way to forget the check)";
    }
}
