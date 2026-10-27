import java.util.List;

public class Legs {
    public static void main(String[] args) {
        List<Animal> pets = List.of(new Dog("Rex"), new Bird("Tweety"), new Animal("Generic"));
        for (Animal pet : pets) {
            System.out.println(pet.describe());
        }
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

    int legs() {
        return 4;
    }

    String describe() {
        return name + " says " + sound() + ", " + legs() + " legs";
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

class Bird extends Animal {
    Bird(String name) {
        super(name);
    }

    @Override
    String sound() {
        return "tweet";
    }

    @Override
    int legs() {
        return 2;
    }
}
