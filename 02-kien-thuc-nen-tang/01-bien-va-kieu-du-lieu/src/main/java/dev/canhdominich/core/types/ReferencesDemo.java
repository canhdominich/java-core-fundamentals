package dev.canhdominich.core.types;

public class ReferencesDemo {

    static class Box {
        int value;

        Box(int value) {
            this.value = value;
        }
    }

    public static String nullMessage() {
        String name = null;
        try {
            return String.valueOf(name.length());
        } catch (NullPointerException e) {
            return e.getMessage();
        }
    }

    public static void main(String[] args) {
        Box a = new Box(1);
        Box b = a;
        b.value = 99;
        System.out.println("a.value after b.value = 99   = " + a.value + "  (a == b: " + (a == b) + ")");
        Box c = new Box(99);
        System.out.println("new Box(99) == a             = " + (c == a));
        String s1 = new String("java");
        String s2 = new String("java");
        String s3 = "java";
        String s4 = "java";
        System.out.println("new String == new String     = " + (s1 == s2) + ", equals = " + s1.equals(s2));
        System.out.println("literal == literal           = " + (s3 == s4));
        System.out.println("NullPointerException message = " + nullMessage());
        int[] numbers = {1, 2, 3};
        int[] copy = numbers;
        copy[0] = 100;
        System.out.println("numbers[0] after copy[0]=100 = " + numbers[0]);
    }
}
