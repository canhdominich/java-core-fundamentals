package dev.canhdominich.core.jvm;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;

public class JvmFacts {

    public static List<String> collectorNames() {
        return ManagementFactory.getGarbageCollectorMXBeans().stream().map(GarbageCollectorMXBean::getName).sorted().toList();
    }

    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        System.out.println("vm name        = " + System.getProperty("java.vm.name"));
        System.out.println("vm version     = " + System.getProperty("java.vm.version"));
        System.out.println("vendor         = " + System.getProperty("java.vendor"));
        System.out.println("processors     = " + runtime.availableProcessors());
        System.out.println("max heap (MB)  = " + runtime.maxMemory() / (1024 * 1024));
        System.out.println("collectors     = " + collectorNames());
        System.out.println("jvm arguments  = " + ManagementFactory.getRuntimeMXBean().getInputArguments());
    }
}
