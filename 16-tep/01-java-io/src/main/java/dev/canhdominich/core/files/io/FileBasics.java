package dev.canhdominich.core.files.io;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public final class FileBasics {

    private FileBasics() {
    }

    public static String describe(File dir) throws IOException {
        File f = new File(dir, "data.txt");
        boolean before = f.exists();
        boolean created = f.createNewFile();
        boolean createdAgain = f.createNewFile();
        boolean after = f.exists();
        long length = f.length();
        String name = f.getName();
        String parentName = f.getParentFile().getName().startsWith("java-io-lab") ? "the lab directory" : "other";
        boolean deleted = f.delete();
        boolean deletedAgain = f.delete();
        return "exists before " + before + ", createNewFile " + created + ", again " + createdAgain + ", exists after " + after + ", length " + length + ", name " + name + ", parent is " + parentName + ", delete " + deleted + ", delete again " + deletedAgain;
    }

    public static String mkdirVersusMkdirs(File dir) {
        File deep = new File(dir, "a/b/c");
        boolean mkdir = deep.mkdir();
        boolean mkdirs = deep.mkdirs();
        boolean isDir = deep.isDirectory();
        return "mkdir on a missing parent " + mkdir + ", mkdirs " + mkdirs + ", isDirectory " + isDir;
    }

    public static String deleteNonEmptyDirectory(File dir) throws IOException {
        File folder = new File(dir, "full");
        folder.mkdir();
        new File(folder, "x.txt").createNewFile();
        boolean deleted = folder.delete();
        return "delete a directory that still holds a file returns " + deleted + ", directory still exists " + folder.exists() + ", no exception was thrown";
    }

    public static String listing(File dir) throws IOException {
        File folder = new File(dir, "listing");
        folder.mkdir();
        new File(folder, "b.txt").createNewFile();
        new File(folder, "a.txt").createNewFile();
        new File(folder, "sub").mkdir();
        String[] names = folder.list();
        Arrays.sort(names);
        File[] onlyFiles = folder.listFiles(File::isFile);
        return "list sorted " + Arrays.toString(names) + ", files only " + onlyFiles.length;
    }
}
