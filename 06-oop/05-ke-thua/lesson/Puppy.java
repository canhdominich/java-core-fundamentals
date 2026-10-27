public class Puppy {
    public static void main(String[] args) {
        Dog rex = new Dog("Rex");
        Dog pup = new Pup("Rex Jr");
        System.out.println(rex.describe());
        System.out.println(pup.describe());
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

    String describe() {
        return name + " says " + sound();
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

class Pup extends Dog {
    Pup(String name) {
        super(name);
    }

    @Override
    String sound() {
        return super.sound() + "!";
    }
}
