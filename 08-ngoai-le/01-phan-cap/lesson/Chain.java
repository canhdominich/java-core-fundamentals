public class Chain {
    public static void main(String[] args) {
        // Chỉ dùng để kiểm chứng cây kế thừa, không đưa vào bài
        Object[] kinds = {new ArithmeticException(), new java.io.FileNotFoundException(), new OutOfMemoryError(), new NullPointerException(), new StackOverflowError(), new java.io.IOException()};
        for (Object k : kinds) {
            Class<?> c = k.getClass();
            StringBuilder sb = new StringBuilder(c.getSimpleName());
            for (Class<?> p = c.getSuperclass(); p != null; p = p.getSuperclass()) {
                sb.append(" extends ").append(p.getSimpleName());
            }
            System.out.println(sb);
        }
    }
}
