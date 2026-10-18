package dev.canhdominich.core.files.nio;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Stream;

public final class Tree {

    private Tree() {
    }

    public static String createDirectories(Path dir) throws IOException {
        Path deep = dir.resolve("a/b/c");
        Files.createDirectories(deep);
        Files.createDirectories(deep);
        return "createDirectories built the chain, second call did not fail, isDirectory " + Files.isDirectory(deep);
    }

    public static String copyAndMove(Path dir) throws IOException {
        Path src = dir.resolve("src.txt");
        Path copy = dir.resolve("copy.txt");
        Files.writeString(src, "payload");
        Files.copy(src, copy);
        String refused;
        try {
            Files.copy(src, copy);
            refused = "accepted";
        } catch (FileAlreadyExistsException e) {
            refused = e.getClass().getSimpleName();
        }
        Files.copy(src, copy, StandardCopyOption.REPLACE_EXISTING);
        Path moved = dir.resolve("moved.txt");
        Files.move(copy, moved);
        return "second copy without REPLACE_EXISTING threw " + refused + ", after move the old path exists " + Files.exists(copy) + " and the new path holds " + Files.readString(moved);
    }

    public static String deleteBehaviour(Path dir) throws IOException {
        Path missing = dir.resolve("ghost.txt");
        String strict;
        try {
            Files.delete(missing);
            strict = "deleted";
        } catch (NoSuchFileException e) {
            strict = e.getClass().getSimpleName();
        }
        boolean quiet = Files.deleteIfExists(missing);
        Path folder = dir.resolve("full");
        Files.createDirectory(folder);
        Files.writeString(folder.resolve("x.txt"), "x");
        String nonEmpty;
        try {
            Files.delete(folder);
            nonEmpty = "deleted";
        } catch (DirectoryNotEmptyException e) {
            nonEmpty = e.getClass().getSimpleName();
        }
        return "delete on a missing file threw " + strict + ", deleteIfExists returned " + quiet + ", delete on a non-empty directory threw " + nonEmpty;
    }

    public static String walkAndFilter(Path dir) throws IOException {
        Path src = dir.resolve("project/src/main");
        Files.createDirectories(src.resolve("util"));
        Files.writeString(src.resolve("App.java"), "class App {}");
        Files.writeString(src.resolve("util/Helper.java"), "class Helper {}");
        Files.writeString(src.resolve("util/notes.md"), "notes");
        Path root = dir.resolve("project");
        List<String> javaFiles;
        try (Stream<Path> all = Files.walk(root)) {
            javaFiles = all.filter(Files::isRegularFile).filter(p -> p.toString().endsWith(".java")).map(p -> root.relativize(p).toString()).sorted().toList();
        }
        List<String> oneLevel;
        try (Stream<Path> children = Files.list(src)) {
            oneLevel = children.map(p -> p.getFileName().toString()).sorted().toList();
        }
        return "java files " + javaFiles + ", Files.list shows only the first level " + oneLevel;
    }

    public static String atomicReplace(Path dir) throws IOException {
        Path target = dir.resolve("config.json");
        Files.writeString(target, "{\"version\":1}");
        Path temp = Files.createTempFile(dir, "config", ".tmp");
        Files.writeString(temp, "{\"version\":2}");
        Files.move(temp, target, StandardCopyOption.ATOMIC_MOVE, StandardCopyOption.REPLACE_EXISTING);
        return "after the swap config holds " + Files.readString(target) + ", temp file still exists " + Files.exists(temp);
    }
}
