package dev.canhdominich.core.oop.constructors;

public class Person {

    private final String name;
    private final int age;

    public Person(String name, int age) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name must not be blank");
        }
        if (age < 0 || age > 150) {
            throw new IllegalArgumentException("age out of range: " + age);
        }
        this.name = name;
        this.age = age;
    }

    public Person(String name) {
        this(name, 18);
    }

    public Person(Person other) {
        this(other.name, other.age);
    }

    public String name() {
        return name;
    }

    public int age() {
        return age;
    }
}
