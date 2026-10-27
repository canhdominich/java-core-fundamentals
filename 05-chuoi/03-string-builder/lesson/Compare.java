public class Compare {
    public static void main(String[] args) {
        StringBuilder a = new StringBuilder("hi");
        StringBuilder b = new StringBuilder("hi");
        System.out.println("equals " + a.equals(b));
        System.out.println("toString equals " + a.toString().equals(b.toString()));
        System.out.println("compareTo " + a.compareTo(b));
        System.out.println("contentEquals " + a.toString().contentEquals(b));
    }
}
