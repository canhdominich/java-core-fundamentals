public class UsernameCheck {
    public static void main(String[] args) {
        String stored = "an";
        String typedLiteral = "an";
        System.out.println("step 1: " + (stored == typedLiteral));

        String typed = new String("an");
        System.out.println("step 2: " + (stored == typed) + " " + stored.equals(typed));

        String interned = typed.intern();
        System.out.println("step 3: " + (stored == interned));

        String name = "an";
        String copy = name;
        name = name + "!";
        System.out.println("step 4: " + name + " " + copy + " " + (name == copy));

        System.out.println("step 5: " + (stored.hashCode() == typed.hashCode()) + " " + ("Aa".hashCode() == "BB".hashCode()) + " " + "Aa".equals("BB"));
    }
}
