public class Churn {
    public static void main(String[] args) {
        long checksum = 0;
        for (int i = 0; i < 200; i++) {
            // Mỗi mảng bị bỏ ngay sau vòng lặp nên là rác ngắn hạn
            byte[] chunk = new byte[1024 * 1024];
            chunk[0] = (byte) i;
            checksum += chunk[0];
        }
        System.out.println("allocated 200 short-lived arrays of 1 MB, checksum " + checksum);
    }
}
