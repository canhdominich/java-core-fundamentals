package dev.canhdominich.core.files.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public final class TextFiles {

    private TextFiles() {
    }

    public static void write(File file, boolean append, String... lines) throws IOException {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(file, StandardCharsets.UTF_8, append))) {
            for (String line : lines) {
                out.write(line);
                out.newLine();
            }
        }
    }

    public static List<String> readLines(File file, Charset charset) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file, charset))) {
            String line;
            while ((line = in.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }

    public static String overwriteVersusAppend(File dir) throws IOException {
        File f = new File(dir, "log.txt");
        write(f, false, "one", "two");
        write(f, false, "three");
        int afterOverwrite = readLines(f, StandardCharsets.UTF_8).size();
        write(f, true, "four", "five");
        List<String> afterAppend = readLines(f, StandardCharsets.UTF_8);
        return "after a second write without append " + afterOverwrite + " line, after appending " + afterAppend;
    }

    public static String charsetMatters(File dir) throws IOException {
        File f = new File(dir, "greeting.txt");
        write(f, false, "Café");
        long bytes = f.length();
        String rightWay = readLines(f, StandardCharsets.UTF_8).get(0);
        String wrongWay = readLines(f, StandardCharsets.ISO_8859_1).get(0);
        return "4 characters took " + (bytes - System.lineSeparator().length()) + " bytes, read as UTF-8 '" + rightWay + "', read as ISO-8859-1 '" + wrongWay + "'";
    }

    public static String bufferingHidesData(File dir) throws IOException {
        File f = new File(dir, "buffered.txt");
        BufferedWriter out = new BufferedWriter(new FileWriter(f, StandardCharsets.UTF_8));
        out.write("hello");
        long beforeFlush = f.length();
        out.flush();
        long afterFlush = f.length();
        out.close();
        return "file length after write but before flush " + beforeFlush + ", after flush " + afterFlush;
    }

    public static String writeAfterClose(File dir) throws IOException {
        File f = new File(dir, "closed.txt");
        BufferedWriter out = new BufferedWriter(new FileWriter(f, StandardCharsets.UTF_8));
        out.close();
        try {
            out.write("late");
            return "accepted";
        } catch (IOException e) {
            return e.getClass().getSimpleName() + ": " + e.getMessage();
        }
    }

    public static String missingFile(File dir) throws IOException {
        File missing = new File(dir, "missing.txt");
        try {
            readLines(missing, StandardCharsets.UTF_8);
            return "found";
        } catch (FileNotFoundException e) {
            return e.getClass().getSimpleName() + ": " + e.getMessage().replace(dir.getPath(), "<dir>");
        }
    }

    public static String endOfFile(File dir) throws IOException {
        File f = new File(dir, "eof.txt");
        write(f, false, "only");
        try (BufferedReader in = new BufferedReader(new FileReader(f, StandardCharsets.UTF_8))) {
            String first = in.readLine();
            String second = in.readLine();
            return "first readLine " + first + ", second readLine " + second;
        }
    }
}
