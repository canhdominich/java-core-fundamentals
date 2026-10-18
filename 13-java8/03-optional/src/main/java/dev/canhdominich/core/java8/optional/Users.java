package dev.canhdominich.core.java8.optional;

import java.util.List;
import java.util.Optional;

public final class Users {

    private static final List<User> ALL = List.of(
            new User("An", new Address("Hanoi", "Tran Hung Dao"), "annie"),
            new User("Binh", new Address(null, "Le Loi"), null),
            new User("Chi", null, null));

    private Users() {
    }

    public static Optional<User> findByName(String name) {
        for (User user : ALL) {
            if (user.name().equals(name)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    public static String cityWithNullChecks(User user) {
        if (user != null) {
            Address address = user.addressOrNull();
            if (address != null) {
                String city = address.city();
                if (city != null) {
                    return city.toUpperCase();
                }
            }
        }
        return "unknown";
    }

    public static String cityWithOptional(String name) {
        return findByName(name)
                .flatMap(User::address)
                .map(Address::city)
                .map(String::toUpperCase)
                .orElse("unknown");
    }
}
