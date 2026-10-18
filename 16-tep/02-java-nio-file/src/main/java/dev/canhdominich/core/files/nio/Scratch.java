package dev.canhdominich.core.files.nio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.stream.Stream;

public final class Scratch {

    private Scratch() {
    }

    public static Path newDirectory() throws IOException {
        return Files.createTempDirectory("java-nio-lab");
    }

    public static void deleteTree(Path root) throws IOException {
        try (Stream<Path> all = Files.walk(root)) {
            all.sorted(Comparator.reverseOrder()).forEach(p -> p.toFile().delete());
        }
    }
}
