import java.util.Arrays;

public class Check {
    static void reset(int[] a) {
        a = new int[] {0, 0};
    }

    static int depthSum(int n) {
        if (n == 0) {
            return 0;
        }
        return n + depthSum(n - 1);
    }

    public static void main(String[] args) {
        int x = 10;
        int[] box = {1, 2};
        int[] alias = box;
        alias[0] = 5;
        x = x + 1;
        int[] other = new int[] {1, 2};
        System.out.println(x + " " + (box == alias) + " " + (box == other) + " " + box[0] + " " + other[0]);
        int[] data = {1, 2};
        reset(data);
        System.out.println(Arrays.toString(data));
        System.out.println(depthSum(4));
    }
}
