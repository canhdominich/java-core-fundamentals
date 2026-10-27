import java.util.Arrays;

public class Shallow {
    public static void main(String[] args) {
        StringBuilder[] names = {new StringBuilder("An"), new StringBuilder("Binh")};
        StringBuilder[] copy = names.clone();
        copy[0].append("!");
        copy[1] = new StringBuilder("Chi");
        System.out.println("original " + Arrays.toString(names) + ", copy " + Arrays.toString(copy));
        System.out.println("same first object " + (names[0] == copy[0]));
        int[] a = new int[4];
        try {
            for (int i = 0; i <= 4; i++) {
                a[i] = i;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(Arrays.toString(a) + " " + e.getMessage());
        }
    }
}
