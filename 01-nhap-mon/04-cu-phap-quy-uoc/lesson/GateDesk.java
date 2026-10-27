public class GateDesk {
    // Hằng số viết toàn chữ hoa, nối các từ bằng gạch dưới, và đặt tên theo ý nghĩa thay vì dùng số trần
    static final int ADULT_AGE = 18;
    static final int SENIOR_AGE = 60;

    static String label(int age) {
        String result = "child";
        if (age >= ADULT_AGE) {
            result = "adult";
        }
        // Hai if nối tiếp: người từ 60 tuổi qua cả hai, nhãn cuối cùng thắng
        if (age >= SENIOR_AGE) {
            result = "senior";
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("step 1: " + ADULT_AGE + " " + SENIOR_AGE);
        System.out.println("step 2: " + label(10) + " " + label(30));
        System.out.println("step 3: " + label(65));
    }
}
