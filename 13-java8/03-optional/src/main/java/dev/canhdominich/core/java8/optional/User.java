package dev.canhdominich.core.java8.optional;

import java.util.Optional;

public class User {

    private final String name;
    private final Address address;
    private final String nickname;

    public User(String name, Address address, String nickname) {
        this.name = name;
        this.address = address;
        this.nickname = nickname;
    }

    public String name() {
        return name;
    }

    public Address addressOrNull() {
        return address;
    }

    public Optional<Address> address() {
        return Optional.ofNullable(address);
    }

    public Optional<String> nickname() {
        return Optional.ofNullable(nickname);
    }
}
