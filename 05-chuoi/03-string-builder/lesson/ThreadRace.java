public class ThreadRace {
    static int[] race(Appendable target, int perThread) throws InterruptedException {
        int[] failures = new int[1];
        Runnable work = () -> {
            for (int i = 0; i < perThread; i++) {
                try {
                    target.append('x');
                } catch (Throwable error) {
                    failures[0]++;
                    return;
                }
            }
        };
        Thread first = new Thread(work);
        Thread second = new Thread(work);
        first.start();
        second.start();
        first.join();
        second.join();
        return new int[] {((CharSequence) target).length(), failures[0]};
    }

    public static void main(String[] args) throws InterruptedException {
        int perThread = 1_000_000;
        for (int attempt = 1; attempt <= 3; attempt++) {
            int[] builder = race(new StringBuilder(), perThread);
            int[] buffer = race(new StringBuffer(), perThread);
            System.out.println("attempt " + attempt + ": StringBuilder length " + builder[0] + ", StringBuffer length " + buffer[0] + ", expected " + (2 * perThread));
        }
    }
}
