import java.util.List;

public class ZooPlus {
    public static void main(String[] args) {
        Cat tom = new Cat("Tom");
        System.out.println(tom.describe());

        List<Animal> pets = List.of(new Dog("Rex"), tom, new Animal("Generic"));
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

class Cat extends Animal {
    Cat(String name) {
        super(name);
    }

    @Override
    String sound() {
        return "meow";
    }

    @Override
    String describe() {
        // Chạy describe() của Animal rồi nối thêm phần của Cat
        return super.describe() + " and purrs";
    }
}
