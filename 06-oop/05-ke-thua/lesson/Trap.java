public class Trap {
    public static void main(String[] args) {
        new Dog();
    }
}

class Animal {
    Animal() {
        System.out.println("constructor sees: " + sound());
    }

    String sound() {
        return "...";
    }
}

class Dog extends Animal {
    private String voice = "woof";

    @Override
    String sound() {
        return voice;
    }
}
