public class Varargs {
    static int sum(int... numbers) {
        int total = 0;
        for (int number : numbers) { // numbers thực chất là một mảng int[]
            total += number;
        }
        return total;
    }

    public static void main(String[] args) {
        System.out.println("sum()               = " + sum());
        System.out.println("sum(7)              = " + sum(7));
        System.out.println("sum(1, 2, 3)        = " + sum(1, 2, 3));
        System.out.println("sum(new int[] {4,5}) = " + sum(new int[] {4, 5}));
    }
}
