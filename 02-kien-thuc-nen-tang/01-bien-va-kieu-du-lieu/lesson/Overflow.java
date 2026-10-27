public class Overflow {
    public static void main(String[] args) {
        int quantity = 2_000;
        int price = 1_500_000;
        int total = quantity * price; // hai int nhân nhau vẫn ra int, tích 3 tỷ bị tràn
        System.out.println("int total  = " + total);
        long safeTotal = (long) quantity * price; // ép trước dấu nhân để phép nhân chạy trong 64 bit
        System.out.println("long total = " + safeTotal);
        int max = Integer.MAX_VALUE; // hằng số int lớn nhất có sẵn trong thư viện
        System.out.println("max + 1    = " + (max + 1)); // quay vòng về số nhỏ nhất, không báo lỗi
    }
}
