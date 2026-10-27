import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class Charsets {
    public static void main(String[] args) throws IOException {
        String text = "Café";
        // Chữ có dấu chiếm nhiều hơn một byte trong UTF-8
        byte[] bytes = text.getBytes(StandardCharsets.UTF_8);
        System.out.println(text.length() + " characters took " + bytes.length + " bytes");
        for (Charset charset : new Charset[] {StandardCharsets.UTF_8, StandardCharsets.ISO_8859_1}) {
            // Cùng dãy byte, đọc bằng bảng mã khác thì ra chữ khác
            String back = new String(bytes, charset);
            System.out.println(charset + ": " + back + " (" + back.length() + " characters)");
        }
    }
}
