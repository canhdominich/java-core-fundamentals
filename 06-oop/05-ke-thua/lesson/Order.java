public class Order {
    public static void main(String[] args) {
        new Puppy();
    }
}

class Animal {
    Animal() {
        System.out.println("Animal");
    }
}

class Dog extends Animal {
    Dog() {
        System.out.println("Dog");
    }
}

class Puppy extends Dog {
    Puppy() {
        System.out.println("Puppy");
    }
}
