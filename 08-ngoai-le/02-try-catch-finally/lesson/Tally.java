public class Tally {
    public static void main(String[] args) {
        String[] lines = {"latte,2", "mocha,x", "tea", "cake,0"};
        int ok = 0;
        int bad = 0;
        int checked = 0;
        for (String line : lines) {
            try {
                String[] parts = line.split(",");
                int qty = Integer.parseInt(parts[1]);
                System.out.println(line + " total " + 300 / qty);
                ok++;
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException | ArithmeticException e) {
                // Multi-catch: một khối xử lý chung cho ba loại, in e sẽ ra tên loại lỗi
                System.out.println(line + " rejected: " + e);
                bad++;
            } finally {
                checked++;
            }
        }
        System.out.println("ok " + ok + ", bad " + bad + ", checked " + checked);
    }
}
