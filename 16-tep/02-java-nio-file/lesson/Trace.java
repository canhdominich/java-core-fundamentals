import java.nio.file.Path;

public class Trace {
    public static void main(String[] args) {
        // getParent không gọt .. và ., chỉ normalize mới làm việc đó
        Path p = Path.of("/srv/cafe/reports/../config/./app.yml");
        System.out.println("1: " + p.getFileName());
        System.out.println("2: " + p.getParent());
        System.out.println("3: " + p.normalize());
        System.out.println("4: " + Path.of("/srv/cafe").resolve("a/b.txt"));
        System.out.println("5: " + Path.of("/srv/cafe").relativize(Path.of("/srv/cafe/a/b.txt")));
        System.out.println("6: " + Path.of("/srv/cafe/reports").relativize(Path.of("/srv/cafe/config/app.yml")));
    }
}
