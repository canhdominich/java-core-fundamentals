public class CapacityDemo {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        System.out.println("new StringBuilder(): length " + sb.length() + ", capacity " + sb.capacity());
        System.out.println("from text of 5: capacity " + new StringBuilder("hello").capacity());
        System.out.println("requested 100: capacity " + new StringBuilder(100).capacity());
        int last = sb.capacity();
        StringBuilder grow = new StringBuilder();
        StringBuilder seen = new StringBuilder("capacity as 1000 chars are appended one by one: " + last);
        for (int i = 0; i < 1000; i++) {
            grow.append('x');
            if (grow.capacity() != last) {
                last = grow.capacity();
                seen.append(", ").append(last);
            }
        }
        System.out.println(seen);
        StringBuilder seventeen = new StringBuilder();
        for (int i = 0; i < 17; i++) {
            seventeen.append('x');
        }
        System.out.println("17 chars: length " + seventeen.length() + ", capacity " + seventeen.capacity());
        StringBuilder sb3 = new StringBuilder("Report: 3 students");
        System.out.println("initial text of 18: capacity " + sb3.capacity());
    }
}
