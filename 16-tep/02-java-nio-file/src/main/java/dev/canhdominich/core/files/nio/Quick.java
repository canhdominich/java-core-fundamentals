package dev.canhdominich.core.files.nio;

import java.io.IOException;
import java.nio.charset.MalformedInputException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public final class Quick {

    private Quick() {
    }

    public static String wholeFile(Path dir) throws IOException {
        Path f = dir.resolve("note.txt");
        Files.writeString(f, "Hello\nCafé\nLine 3");
        String text = Files.readString(f);
        List<String> lines = Files.readAllLines(f);
        return "readString " + text.length() + " characters, readAllLines " + lines + ", size " + Files.size(f) + " bytes";
    }

    public static String createNewRefusesToOverwrite(Path dir) throws IOException {
        Path f = dir.resolve("once.txt");
        Files.writeString(f, "first", StandardOpenOption.CREATE_NEW);
        try {
            Files.writeString(f, "second", StandardOpenOption.CREATE_NEW);
            return "accepted";
        } catch (FileAlreadyExistsException e) {
            return "second CREATE_NEW threw " + e.getClass().getSimpleName() + ", content still " + Files.readString(f);
        }
    }

    public static String appendVersusTruncate(Path dir) throws IOException {
        Path f = dir.resolve("log.txt");
        Files.writeString(f, "a\n");
        Files.writeString(f, "b\n");
        String overwritten = Files.readString(f).strip();
        Files.writeString(f, "c\n", StandardOpenOption.APPEND);
        return "default write replaces: " + overwritten + ", with APPEND: " + Files.readAllLines(f);
    }

    public static String missingFile(Path dir) {
        try {
            Files.readString(dir.resolve("missing.txt"));
            return "found";
        } catch (NoSuchFileException e) {
            return e.getClass().getSimpleName() + " with the path in the message " + e.getMessage().endsWith("missing.txt");
        } catch (IOException e) {
            return e.getClass().getSimpleName();
        }
    }

    public static String badUtf8IsRejected(Path dir) throws IOException {
        Path f = dir.resolve("latin1.txt");
        Files.write(f, "Café".getBytes(StandardCharsets.ISO_8859_1));
        try {
            Files.readString(f);
            return "accepted";
        } catch (MalformedInputException e) {
            String lenient = new String(Files.readAllBytes(f), StandardCharsets.UTF_8);
            return "readString threw " + e.getClass().getSimpleName() + ": " + e.getMessage() + ", decoding the same bytes by hand gives '" + lenient.replace('�', '?') + "'";
        }
    }

    public static String linesAreLazy(Path dir) throws IOException {
        Path f = dir.resolve("big.log");
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 100_000; i++) {
            sb.append(i % 25_000 == 0 ? "ERROR failure number " : "info line number ").append(i).append('\n');
        }
        Files.writeString(f, sb.toString());
        long errors;
        try (var lines = Files.lines(f)) {
            errors = lines.filter(l -> l.startsWith("ERROR")).count();
        }
        int[] seen = new int[1];
        String first;
        try (var lines = Files.lines(f)) {
            first = lines.peek(l -> seen[0]++).filter(l -> l.startsWith("ERROR")).findFirst().orElse("none");
        }
        return "100000 lines, ERROR lines " + errors + ", first match '" + first + "' found after reading " + seen[0] + " lines";
    }
}
