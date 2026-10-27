public class NameFixer {
    public static void main(String[] args) {
        String raw = "  nguyen van An  ";
        // trim và toUpperCase đều tạo ra chuỗi mới, raw không đổi
        String trimmed = raw.trim();
        String upper = trimmed.toUpperCase();
        System.out.println("raw [" + raw + "] length " + raw.length());
        System.out.println("trimmed [" + trimmed + "] length " + trimmed.length());
        System.out.println("upper " + upper + ", first char " + trimmed.charAt(0));
        // substring(0, 6) lấy ô 0 đến ô 5, ô 6 không được lấy
        System.out.println("family " + trimmed.substring(0, 6) + ", index of van " + trimmed.indexOf("van"));
        // Gọi mà không gán kết quả thì kết quả bị bỏ đi
        raw.toUpperCase();
        System.out.println("raw after ignored call [" + raw + "]");
    }
}
