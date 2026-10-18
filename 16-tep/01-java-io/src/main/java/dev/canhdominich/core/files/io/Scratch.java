package dev.canhdominich.core.files.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public final class Scratch {

    private Scratch() {
    }

    public static File newDirectory() throws IOException {
        return Files.createTempDirectory("java-io-lab").toFile();
    }

    public static void deleteTree(File root) {
        File[] children = root.listFiles();
        if (children != null) {
            for (File child : children) {
                deleteTree(child);
            }
        }
        root.delete();
    }
}
