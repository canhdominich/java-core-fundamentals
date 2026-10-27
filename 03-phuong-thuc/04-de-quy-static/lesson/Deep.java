import java.util.HashMap;
import java.util.Map;

public class Deep {
    static long calls;

    static long factorial(int n) {
        if (n <= 1) {
            return 1;
        }
        return n * factorial(n - 1); // long tràn số từ n bằng 21 nên ra số âm
    }

    static long fibNaive(int n) {
        calls++;
        if (n < 2) {
            return n;
        }
        return fibNaive(n - 1) + fibNaive(n - 2); // hai nhánh đệ quy tính lại cùng một giá trị nhiều lần
    }

    static long fibMemo(int n, Map<Integer, Long> cache) {
        calls++;
        if (n < 2) {
            return n;
        }
        Long known = cache.get(n);
        if (known != null) { // đã tính rồi thì dùng lại, không đệ quy nữa
            return known;
        }
        long value = fibMemo(n - 1, cache) + fibMemo(n - 2, cache);
        cache.put(n, value);
        return value;
    }

    static int depthReached;

    static void dive(int depth) {
        depthReached = depth;
        dive(depth + 1);
    }

    static int measureDepth(long stackBytes) throws InterruptedException {
        depthReached = 0;
        Thread worker = new Thread(null, () -> {
            try {
                dive(1);
            } catch (StackOverflowError expected) {
            }
        }, "deep", stackBytes);
        worker.start(); // chạy trên luồng riêng có kích thước ngăn xếp tự chọn
        worker.join();
        return depthReached;
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("factorial(20) = " + factorial(20));
        System.out.println("factorial(21) = " + factorial(21));
        calls = 0;
        long slow = fibNaive(30);
        long slowCalls = calls;
        calls = 0;
        long fast = fibMemo(30, new HashMap<>());
        System.out.println("fib(30) naive    = " + slow + " in " + slowCalls + " calls");
        System.out.println("fib(30) memoized = " + fast + " in " + calls + " calls");
        System.out.println("depth with a 256 KB stack = " + measureDepth(256 * 1024));
        System.out.println("depth with a 1 MB stack   = " + measureDepth(1024 * 1024));
    }
}
