public class Hide {
    public static void main(String[] args) {
        Animal pet = new Dog();
        System.out.println(pet.label + " " + pet.sound() + " " + ((Dog) pet).label);
    }
}

class Animal {
    String label = "animal";

    String sound() {
        return "...";
    }
}

class Dog extends Animal {
    String label = "dog";

    @Override
    String sound() {
        return "woof";
    }
}
