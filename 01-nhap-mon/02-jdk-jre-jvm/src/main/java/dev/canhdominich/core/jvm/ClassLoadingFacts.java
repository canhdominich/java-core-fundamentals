package dev.canhdominich.core.jvm;

import java.lang.management.ManagementFactory;

public class ClassLoadingFacts {

    static int loaded() {
        return ManagementFactory.getClassLoadingMXBean().getLoadedClassCount();
    }

    public static int loadedByFirstUse(String className) throws ClassNotFoundException {
        int before = loaded();
        Class.forName(className);
        return loaded() - before;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println("loaded at start            = " + loaded());
        System.out.println("java.util.logging.Logger   : +" + loadedByFirstUse("java.util.logging.Logger") + " classes");
        System.out.println("java.util.logging.Logger   : +" + loadedByFirstUse("java.util.logging.Logger") + " classes (second time)");
        System.out.println("java.util.concurrent.ForkJoinPool : +" + loadedByFirstUse("java.util.concurrent.ForkJoinPool") + " classes");
        System.out.println("loaded now                 = " + loaded());
    }
}
