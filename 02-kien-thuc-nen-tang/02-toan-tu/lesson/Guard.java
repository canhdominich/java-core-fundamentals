public class Guard {
    public static void main(String[] args) {
        int calls = 0;
        boolean first = false && (++calls > 0); // vế trái false nên vế phải bị bỏ qua
        System.out.println("&& right side ran " + calls + " times");
        boolean second = false & (++calls > 0); // một dấu & vẫn chạy vế phải
        System.out.println("& right side ran " + calls + " times");
        String note = null;
        boolean safe = note != null && note.length() > 0; // kiểm tra null ở vế trái để length() không bị gọi
        System.out.println("safe = " + safe);
    }
}
