package dev.canhdominich.core.files.io;

import java.io.File;
import java.util.List;

public final class IoDemo {

    private IoDemo() {
    }

    public static void main(String[] args) throws Exception {
        File dir = Scratch.newDirectory();
        try {
            System.out.println("file basics         = " + FileBasics.describe(dir));
            System.out.println("mkdir vs mkdirs     = " + FileBasics.mkdirVersusMkdirs(dir));
            System.out.println("delete non empty    = " + FileBasics.deleteNonEmptyDirectory(dir));
            System.out.println("listing             = " + FileBasics.listing(dir));
            System.out.println("overwrite vs append = " + TextFiles.overwriteVersusAppend(dir));
            System.out.println("charset             = " + TextFiles.charsetMatters(dir));
            System.out.println("buffering           = " + TextFiles.bufferingHidesData(dir));
            System.out.println("write after close   = " + TextFiles.writeAfterClose(dir));
            System.out.println("missing file        = " + TextFiles.missingFile(dir));
            System.out.println("end of file         = " + TextFiles.endOfFile(dir));
            System.out.println("binary copy         = " + BinaryFiles.copyBlocksVersusBytes(dir));
            System.out.println("byte as int         = " + BinaryFiles.readReturnsByteAsInt());
        } finally {
            Scratch.deleteTree(dir);
        }
        List<String> unreported = Javac.messages("A", "import java.io.*;\nclass A { void f() { new FileReader(\"x\"); } }");
        List<String> notCloseable = Javac.messages("B", "class B { void f() { try (String s = \"x\") { } } }");
        List<String> wrongType = Javac.messages("C", "import java.io.*;\nclass C { void f() throws Exception { BufferedReader r = new FileInputStream(\"x\"); } }");
        System.out.println("compile reader      = " + unreported);
        System.out.println("compile twr         = " + notCloseable);
        System.out.println("compile stream type = " + wrongType);
    }
}
