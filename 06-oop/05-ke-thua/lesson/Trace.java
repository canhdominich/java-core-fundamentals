public class Trace {
    public static void main(String[] args) {
        Animal a = new Dog("Rex");
        Animal b = new Animal("Tom");
        System.out.println(a.sound());
        System.out.println(b.sound());
        a = b;
        System.out.println(a.sound());
    }
}

class Animal {
    private final String name;

    Animal(String name) {
        this.name = name;
    }

    String sound() {
        return "...";
    }
}

class Dog extends Animal {
    Dog(String name) {
        super(name);
    }

    @Override
    String sound() {
        return "woof";
    }
}
