package dev.canhdominich.core.recursion;

import java.util.HashMap;
import java.util.Map;

public class Recursion {

    static long calls;

    public static long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must not be negative: " + n);
        }
        if (n <= 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    public static long factorialExact(int n) {
        if (n <= 1) {
            return 1;
        }
        return Math.multiplyExact(n, factorialExact(n - 1));
    }

    public static long fibNaive(int n) {
        calls++;
        if (n < 2) {
            return n;
        }
        return fibNaive(n - 1) + fibNaive(n - 2);
    }

    public static long fibMemo(int n, Map<Integer, Long> cache) {
        calls++;
        if (n < 2) {
            return n;
        }
        Long known = cache.get(n);
        if (known != null) {
            return known;
        }
        long value = fibMemo(n - 1, cache) + fibMemo(n - 2, cache);
        cache.put(n, value);
        return value;
    }

    public static long fibIterative(int n) {
        long previous = 0;
        long current = 1;
        for (int i = 0; i < n; i++) {
            long next = previous + current;
            previous = current;
            current = next;
        }
        return previous;
    }

    public static int sumDigits(int n) {
        if (n < 10) {
            return n;
        }
        return n % 10 + sumDigits(n / 10);
    }

    public static long powerLinear(long base, int exponent) {
        calls++;
        if (exponent == 0) {
            return 1;
        }
        return base * powerLinear(base, exponent - 1);
    }

    public static long powerFast(long base, int exponent) {
        calls++;
        if (exponent == 0) {
            return 1;
        }
        long half = powerFast(base, exponent / 2);
        return exponent % 2 == 0 ? half * half : half * half * base;
    }

    public static long hanoiMoves(int disks) {
        if (disks == 0) {
            return 0;
        }
        return 2 * hanoiMoves(disks - 1) + 1;
    }

    public static int neverStops(int depth) {
        return neverStops(depth + 1) + 1;
    }

    static int depthReached;

    static void dive(int depth) {
        depthReached = depth;
        dive(depth + 1);
    }

    public static int measureDepth(long stackBytes) throws InterruptedException {
        depthReached = 0;
        Thread worker = new Thread(null, () -> {
            try {
                dive(1);
            } catch (StackOverflowError expected) {
            }
        }, "deep", stackBytes);
        worker.start();
        worker.join();
        return depthReached;
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("factorial(5), factorial(20)        = " + factorial(5) + ", " + factorial(20));
        System.out.println("factorial(21) wraps around          = " + factorial(21));
        try {
            factorialExact(21);
        } catch (ArithmeticException e) {
            System.out.println("factorialExact(21)                  = ArithmeticException: " + e.getMessage());
        }
        calls = 0;
        long slow = fibNaive(30);
        long slowCalls = calls;
        calls = 0;
        long fast = fibMemo(30, new HashMap<>());
        long fastCalls = calls;
        System.out.println("fib(30) naive                       = " + slow + " in " + slowCalls + " calls");
        System.out.println("fib(30) memoized                    = " + fast + " in " + fastCalls + " calls");
        System.out.println("fib(30) iterative                   = " + fibIterative(30));
        System.out.println("sumDigits(9875)                     = " + sumDigits(9875));
        calls = 0;
        long linear = powerLinear(2, 30);
        long linearCalls = calls;
        calls = 0;
        long quick = powerFast(2, 30);
        long quickCalls = calls;
        System.out.println("2 to the 30th, linear recursion     = " + linear + " in " + linearCalls + " calls");
        System.out.println("2 to the 30th, halving recursion    = " + quick + " in " + quickCalls + " calls");
        System.out.println("hanoiMoves(3), (10), (20)           = " + hanoiMoves(3) + ", " + hanoiMoves(10) + ", " + hanoiMoves(20));
        try {
            neverStops(1);
        } catch (StackOverflowError e) {
            System.out.println("neverStops(1)                       = " + e.getClass().getSimpleName() + ", message " + e.getMessage());
        }
        int small = measureDepth(256 * 1024);
        int large = measureDepth(64L * 1024 * 1024);
        System.out.println("depth with a 256 KB stack           = " + small);
        System.out.println("depth with a 64 MB stack            = " + large);
        System.out.println("ratio                               = " + (large / small));
    }
}
