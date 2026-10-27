public class PriceDesk {
    static final int CHILD_PRICE = 30000;
    static final int ADULT_PRICE = 50000;
    static final int SENIOR_PRICE = 25000;

    // Giá vé theo tuổi: lấy giá trẻ em trước, rồi if sau ghi đè nếu đủ tuổi
    static int price(int age) {
        int result = CHILD_PRICE;
        if (age >= 18) {
            result = ADULT_PRICE;
        }
        if (age >= 60) {
            result = SENIOR_PRICE;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("age 10: " + price(10));
        System.out.println("age 30: " + price(30));
        System.out.println("age 70: " + price(70));
    }
}
