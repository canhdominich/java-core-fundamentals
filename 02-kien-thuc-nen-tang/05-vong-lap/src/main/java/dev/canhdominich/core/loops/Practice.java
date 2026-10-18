package dev.canhdominich.core.loops;

import java.util.ArrayList;
import java.util.List;

public final class Practice {

    private Practice() {
    }

    public static List<String> fizzBuzz(int limit) {
        List<String> out = new ArrayList<>();
        for (int i = 1; i <= limit; i++) {
            if (i % 15 == 0) {
                out.add("FizzBuzz");
            } else if (i % 3 == 0) {
                out.add("Fizz");
            } else if (i % 5 == 0) {
                out.add("Buzz");
            } else {
                out.add(String.valueOf(i));
            }
        }
        return out;
    }

    public static int digitSum(int number) {
        int sum = 0;
        int rest = Math.abs(number);
        while (rest > 0) {
            sum += rest % 10;
            rest /= 10;
        }
        return sum;
    }

    public static int reverseDigits(int number) {
        int reversed = 0;
        int rest = number;
        while (rest != 0) {
            reversed = reversed * 10 + rest % 10;
            rest /= 10;
        }
        return reversed;
    }

    public static int collatzSteps(long start) {
        if (start < 1) {
            throw new IllegalArgumentException("start must be positive: " + start);
        }
        int steps = 0;
        long n = start;
        while (n != 1) {
            n = n % 2 == 0 ? n / 2 : 3 * n + 1;
            steps++;
        }
        return steps;
    }

    public static int linearSearchSteps(int[] sorted, int target) {
        int steps = 0;
        for (int value : sorted) {
            steps++;
            if (value == target) {
                break;
            }
        }
        return steps;
    }

    public static int binarySearchSteps(int[] sorted, int target) {
        int low = 0;
        int high = sorted.length - 1;
        int steps = 0;
        while (low <= high) {
            steps++;
            int mid = (low + high) >>> 1;
            if (sorted[mid] == target) {
                break;
            }
            if (sorted[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return steps;
    }

    public static void main(String[] args) {
        System.out.println("fizzBuzz(15)             = " + fizzBuzz(15));
        System.out.println("digitSum(9875, -406)     = " + digitSum(9875) + ", " + digitSum(-406));
        System.out.println("reverseDigits(12340, -57)= " + reverseDigits(12340) + ", " + reverseDigits(-57));
        System.out.println("collatzSteps(6, 27)      = " + collatzSteps(6) + ", " + collatzSteps(27));
        int[] sorted = new int[1000];
        for (int i = 0; i < sorted.length; i++) {
            sorted[i] = i * 2;
        }
        System.out.println("search for 1998 in 1000 numbers: linear " + linearSearchSteps(sorted, 1998) + " steps, binary " + binarySearchSteps(sorted, 1998) + " steps");
        System.out.println("search for 7 (absent): linear " + linearSearchSteps(sorted, 7) + " steps, binary " + binarySearchSteps(sorted, 7) + " steps");
    }
}
