package dev.canhdominich.core.memory;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public final class Child {

    private Child() {
    }

    public static String run(String mainClass, String... jvmFlags) {
        try {
            List<String> command = new ArrayList<>();
            command.add(System.getProperty("java.home") + "/bin/java");
            command.addAll(List.of(jvmFlags));
            command.add("-classpath");
            command.add(System.getProperty("java.class.path"));
            command.add(mainClass);
            Process process = new ProcessBuilder(command).redirectErrorStream(true).start();
            String output = new String(process.getInputStream().readAllBytes()).trim();
            process.waitFor();
            return output;
        } catch (Exception e) {
            return "could not run: " + e;
        }
    }

    public static long bytesPerObject(Supplier<Object> factory, int count) {
        com.sun.management.ThreadMXBean bean = (com.sun.management.ThreadMXBean) java.lang.management.ManagementFactory.getThreadMXBean();
        long thread = Thread.currentThread().getId();
        Object[] keep = new Object[count];
        for (int i = 0; i < 2000; i++) {
            factory.get();
        }
        long before = bean.getThreadAllocatedBytes(thread);
        for (int i = 0; i < count; i++) {
            keep[i] = factory.get();
        }
        long after = bean.getThreadAllocatedBytes(thread);
        if (keep[count - 1] == null) {
            throw new IllegalStateException();
        }
        return (after - before) / count;
    }
}
