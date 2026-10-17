package dev.canhdominich.core.intro;

public class ClassFileInspector {

    public static void main(String[] args) {
        System.out.println("running on Java " + Runtime.version().feature());
        for (int release : new int[] {8, 11, 17}) {
            byte[] bytes = InMemoryCompiler.compile("Greeter", Sources.GREETER, release);
            ClassFileHeader header = ClassFileHeader.parse(bytes);
            System.out.printf("--release %-2d : size=%d bytes magic=%s minor=%d major=%d (Java %d)%n",
                    release, bytes.length, header.magicHex(), header.minor(), header.major(), header.javaVersion());
        }
    }
}
