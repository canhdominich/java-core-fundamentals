package dev.canhdominich.core.files.nio;

import java.nio.file.Path;
import java.util.List;

public final class NioDemo {

    private NioDemo() {
    }

    public static void main(String[] args) throws Exception {
        System.out.println("path parts          = " + PathOps.parts());
        System.out.println("resolve             = " + PathOps.resolveAndRelativize());
        System.out.println("normalize           = " + PathOps.normalize());
        System.out.println("traversal           = " + PathOps.traversal());
        System.out.println("ghost path          = " + PathOps.pathDoesNotNeedToExist());
        Path dir = Scratch.newDirectory();
        try {
            System.out.println("whole file          = " + Quick.wholeFile(dir));
            System.out.println("create new          = " + Quick.createNewRefusesToOverwrite(dir));
            System.out.println("append              = " + Quick.appendVersusTruncate(dir));
            System.out.println("missing file        = " + Quick.missingFile(dir));
            System.out.println("bad utf8            = " + Quick.badUtf8IsRejected(dir));
            System.out.println("lazy lines          = " + Quick.linesAreLazy(dir));
            System.out.println("create directories  = " + Tree.createDirectories(dir));
            System.out.println("copy and move       = " + Tree.copyAndMove(dir));
            System.out.println("delete              = " + Tree.deleteBehaviour(dir));
            System.out.println("walk                = " + Tree.walkAndFilter(dir));
            System.out.println("atomic replace      = " + Tree.atomicReplace(dir));
        } finally {
            Scratch.deleteTree(dir);
        }
        List<String> noSuchMethod = Javac.messages("A", "import java.nio.file.*;\nclass A { void f() { Path p = new Path(\"x\"); } }");
        List<String> unreported = Javac.messages("B", "import java.nio.file.*;\nclass B { void f() { Files.readString(Path.of(\"x\")); } }");
        List<String> listType = Javac.messages("C", "import java.nio.file.*;\nimport java.util.*;\nclass C { void f() throws Exception { String s = Files.readAllLines(Path.of(\"x\")); } }");
        System.out.println("compile new path    = " + noSuchMethod);
        System.out.println("compile unreported  = " + unreported);
        System.out.println("compile lines type  = " + listType);
    }
}
