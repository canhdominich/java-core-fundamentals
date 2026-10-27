import java.util.Arrays;

public class Extras {
    public static void main(String[] args) {
        System.out.println("split a,b,, " + Arrays.toString("a,b,,".split(",")) + " parts " + "a,b,,".split(",").length);
        System.out.println("split limit -1 " + "a,b,,".split(",", -1).length + " parts");
        System.out.println("split empty string " + "".split(",").length + " part");
        System.out.println("replace dot " + "a.b.c".replace(".", "-") + ", replaceAll dot " + "a.b.c".replaceAll(".", "-"));
        String wide = "  hi  ";
        System.out.println("trim length " + wide.trim().length() + ", strip length " + wide.strip().length());
        System.out.println("isEmpty of spaces " + "   ".isEmpty() + ", isBlank " + "   ".isBlank());
        System.out.println("compareTo apple banana " + "apple".compareTo("banana") + ", Zebra apple " + "Zebra".compareTo("apple"));
    }
}
