package dev.canhdominich.core.oop.inheritance;

import java.util.List;

public class InheritanceDemo {

    public static String inheritedMembers() {
        Dog dog = new Dog("Rex");
        dog.eat();
        String fetch = dog.fetch();
        return "name " + dog.name() + " (inherited), energy " + dog.energy() + " after eat and fetch, " + fetch;
    }

    public static String overriding() {
        List<Animal> animals = List.of(new Animal("Generic"), new Dog("Rex"), new Cat("Tom"));
        StringBuilder out = new StringBuilder();
        for (Animal animal : animals) {
            out.append(animal.describe()).append(" | ");
        }
        return out.toString().trim();
    }

    public static String fieldsAreNotPolymorphic() {
        Animal viaAnimal = new Dog("Rex");
        Dog viaDog = new Dog("Rex");
        return "viaAnimal.label " + viaAnimal.label + ", viaDog.label " + viaDog.label + ", ((Dog) viaAnimal).label " + ((Dog) viaAnimal).label
                + ", but sound() is " + viaAnimal.sound() + " either way";
    }

    public static String casting() {
        Animal animal = new Cat("Tom");
        String result = "instanceof Cat " + (animal instanceof Cat) + ", instanceof Dog " + (animal instanceof Dog);
        try {
            Dog dog = (Dog) animal;
            result += ", cast worked " + dog.name();
        } catch (ClassCastException e) {
            result += ", cast to Dog throws " + e.getClass().getSimpleName() + ", message mentions the two classes " + e.getMessage().contains("Cat cannot be cast to class");
        }
        if (animal instanceof Cat cat) {
            result += ", pattern match gives " + cat.sound();
        }
        return result;
    }

    public static String everythingIsAnObject() {
        Animal animal = new Dog("Rex");
        Object asObject = animal;
        return "superclass of Dog " + Dog.class.getSuperclass().getSimpleName() + ", of Animal " + Animal.class.getSuperclass().getSimpleName()
                + ", toString starts with the class name " + asObject.toString().startsWith("dev.canhdominich.core.oop.inheritance.Dog@")
                + ", equals is identity " + asObject.equals(new Dog("Rex"));
    }

    public static String fragileBaseClass() {
        CountingHashSet<String> inherited = new CountingHashSet<>();
        inherited.addAll(List.of("a", "b", "c"));
        CountingSet<String> composed = new CountingSet<>();
        composed.addAll(List.of("a", "b", "c"));
        return "inheritance counts " + inherited.attempts() + " attempts for 3 elements, composition counts " + composed.attempts()
                + "; both hold " + inherited.size() + " and " + composed.size() + " elements";
    }

    public static void main(String[] args) {
        System.out.println("inherited members   = " + inheritedMembers());
        System.out.println("overriding          = " + overriding());
        System.out.println("fields are static   = " + fieldsAreNotPolymorphic());
        System.out.println("casting             = " + casting());
        System.out.println("everything is Object= " + everythingIsAnObject());
        System.out.println("fragile base class  = " + fragileBaseClass());
        String finalClass = "final class A { } class B extends A { }";
        String weakerAccess = "class A { public String f() { return \"\"; } } class B extends A { String f() { return \"\"; } }";
        String finalMethod = "class A { final void f() { } } class B extends A { void f() { } }";
        String twoParents = "class A { } class B { } class C extends A, B { }";
        String superNotFirst = "class A { A(int x) { } } class B extends A { B() { int y = 1; super(y); } }";
        String privateOfParent = "class A { private int secret; } class B extends A { int f() { return secret; } }";
        String missingSuper = "class A { A(String name) { } } class B extends A { }";
        System.out.println("extend a final class      = " + Javac.messages("B", finalClass));
        System.out.println("override with weaker access= " + Javac.messages("B", weakerAccess));
        System.out.println("override a final method   = " + Javac.messages("B", finalMethod));
        System.out.println("two superclasses          = " + Javac.messages("C", twoParents));
        System.out.println("super() not first         = " + Javac.messages("B", superNotFirst));
        System.out.println("private member of parent  = " + Javac.messages("B", privateOfParent));
        System.out.println("no matching super()       = " + Javac.messages("B", missingSuper));
    }
}
