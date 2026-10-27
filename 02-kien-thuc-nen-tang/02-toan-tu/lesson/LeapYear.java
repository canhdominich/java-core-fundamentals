public class LeapYear {
    public static void main(String[] args) {
        int year = 1900;
        System.out.println(year + " " + ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)); // && gắn chặt hơn ||
        year = 2000;
        System.out.println(year + " " + ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0));
        year = 2024;
        System.out.println(year + " " + ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0));
        year = 2023;
        System.out.println(year + " " + ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0));
    }
}
