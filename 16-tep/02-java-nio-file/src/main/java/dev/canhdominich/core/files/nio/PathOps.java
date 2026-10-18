package dev.canhdominich.core.files.nio;

import java.nio.file.Path;

public final class PathOps {

    private PathOps() {
    }

    public static String parts() {
        Path p = Path.of("data", "reports", "out.txt");
        return "path " + p + ", file name " + p.getFileName() + ", parent " + p.getParent() + ", name count " + p.getNameCount() + ", absolute " + p.isAbsolute() + ", root " + p.getRoot();
    }

    public static String resolveAndRelativize() {
        Path base = Path.of("/srv/app");
        Path file = base.resolve("logs/today.log");
        Path other = Path.of("/srv/app/config/app.yml");
        return "resolve " + file + ", relativize " + file.relativize(other) + ", resolve with an absolute path gives " + base.resolve("/etc/hosts");
    }

    public static String normalize() {
        Path messy = Path.of("/srv/app/logs/../config/./app.yml");
        return "normalize " + messy.normalize() + ", same without normalizing " + messy.equals(messy.normalize());
    }

    public static boolean isInside(Path base, String userInput) {
        Path target = base.resolve(userInput).normalize();
        return target.startsWith(base.normalize());
    }

    public static String traversal() {
        Path base = Path.of("/srv/app/uploads");
        return "report.pdf inside " + isInside(base, "report.pdf") + ", ../../etc/passwd inside " + isInside(base, "../../etc/passwd") + ", /etc/passwd inside " + isInside(base, "/etc/passwd") + ", naive startsWith on the raw text " + base.resolve("../../etc/passwd").startsWith(base);
    }

    public static String pathDoesNotNeedToExist() {
        Path ghost = Path.of("no", "such", "place.txt");
        return "path built " + ghost + ", exists " + java.nio.file.Files.exists(ghost) + ", notExists " + java.nio.file.Files.notExists(ghost);
    }
}
