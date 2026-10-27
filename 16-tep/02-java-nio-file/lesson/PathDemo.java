import java.nio.file.Files;
import java.nio.file.Path;

public class PathDemo {
    public static void main(String[] args) {
        Path base = Path.of("/srv/cafe/reports");
        Path file = base.resolve("2026-10/day1.txt");  // resolve chỉ ghép chữ, không hỏi đĩa
        System.out.println("file: " + file);
        System.out.println("name: " + file.getFileName() + ", parent: " + file.getParent());
        System.out.println("relative: " + base.relativize(file));
        Path messy = Path.of("/srv/cafe/reports/../config/./app.yml");  // Đường dẫn có đoạn thừa .. và .
        System.out.println("messy: " + messy);
        System.out.println("normalized: " + messy.normalize());  // normalize gọt các đoạn thừa
        System.out.println("absolute argument: " + base.resolve("/etc/hosts"));  // Đối số tuyệt đối thì resolve bỏ phần đứng trước
        System.out.println("exists on disk: " + Files.exists(file));  // Lệnh duy nhất chạm vào đĩa trong chương trình này
    }
}
