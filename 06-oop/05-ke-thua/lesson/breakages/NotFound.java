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

    String fetch() {
        return "fetch";
    }
}

class Use {
    void run() {
        Animal pet = new Dog("Rex");
        pet.fetch();
    }
}
