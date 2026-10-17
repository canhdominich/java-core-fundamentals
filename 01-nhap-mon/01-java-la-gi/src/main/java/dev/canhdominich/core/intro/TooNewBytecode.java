package dev.canhdominich.core.intro;

public class TooNewBytecode {

    static final class Loader extends ClassLoader {
        Class<?> define(String name, byte[] bytes) {
            return defineClass(name, bytes, 0, bytes.length);
        }
    }

    public static String tryLoad(int major) {
        byte[] bytes = ClassFileHeader.withMajor(InMemoryCompiler.compile("Greeter", Sources.GREETER, 17), major);
        try {
            Class<?> loaded = new Loader().define("Greeter", bytes);
            return "loaded " + loaded.getName();
        } catch (UnsupportedClassVersionError e) {
            return e.getClass().getSimpleName() + ": " + e.getMessage();
        }
    }

    public static void main(String[] args) {
        int supported = Runtime.version().feature() + 44;
        System.out.println("this JVM supports class file versions up to " + supported);
        System.out.println("major " + supported + " -> " + tryLoad(supported));
        System.out.println("major " + (supported + 1) + " -> " + tryLoad(supported + 1));
        System.out.println("major 52 -> " + tryLoad(52));
    }
}
