public class SafeReader {
    public static void main(String[] args) {
        String[] lines = {"latte,2", "mocha,x", "tea", "cake,0"};
        for (String line : lines) {
            process(line);
        }
        System.out.println("end");
    }

    static void process(String line) {
        try {
            String[] parts = line.split(",");
            int qty = Integer.parseInt(parts[1]);
            // Dòng cuối của try: chỉ chạy khi hai dòng trên không ném lỗi
            System.out.println(line + " total " + 300 / qty);
        } catch (NumberFormatException e) {
            System.out.println(line + " bad number");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(line + " missing quantity");
        } catch (ArithmeticException e) {
            System.out.println(line + " zero quantity");
        } finally {
            // finally chạy ở cả bốn trường hợp, có lỗi hay không
            System.out.println("checked " + line);
        }
    }
}
