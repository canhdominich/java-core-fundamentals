public class Defaults {
    public static void main(String[] args) {
        Cup cup = new Cup();
        System.out.println(cup.full + " " + cup.label + " " + cup.ml);
    }
}

class Cup {
    boolean full;
    String label;
    double ml;
}
