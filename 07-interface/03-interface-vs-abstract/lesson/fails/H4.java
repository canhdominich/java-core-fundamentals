public class H4 {
    public static void main(String[] args) {
        Counter.count = 1;
    }
}

interface Counter {
    int count = 0;
}
