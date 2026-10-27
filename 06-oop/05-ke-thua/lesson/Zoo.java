// Lớp có main phải đứng đầu file khi chạy bằng java Zoo.java
public class Zoo {
    public static void main(String[] args) {
        Dog rex = new Dog("Rex");
        // Hai biến cùng trỏ vào một đối tượng Dog, không có bản sao
        Animal pet = rex;
        System.out.println(rex.describe());
        System.out.println(pet.describe());
        System.out.println(new Animal("Generic").describe());
    }
}

class Animal {
    // private: chỉ code bên trong Animal đọc được name
    private final String name;

    Animal(String name) {
        this.name = name;
    }

    String sound() {
        return "...";
    }

    String describe() {
        // sound() ở đây chạy bản của đối tượng thật, không phải của Animal
        return name + " says " + sound();
    }
}

class Dog extends Animal {
    Dog(String name) {
        // Nhờ Animal dựng phần của nó trước, bắt buộc là dòng đầu
        super(name);
    }

    // Thay sound() của Animal bằng tiếng kêu riêng của Dog
    @Override
    String sound() {
        return "woof";
    }
}
