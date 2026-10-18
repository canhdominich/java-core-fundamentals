package dev.canhdominich.core.gc;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public final class Reachability {

    private static final List<Object> CACHE = new ArrayList<>();

    private Reachability() {
    }

    static void collect() {
        for (int i = 0; i < 5; i++) {
            System.gc();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static String strongAndWeak() {
        Object strong = new byte[1024];
        WeakReference<Object> weak = new WeakReference<>(strong);
        collect();
        boolean aliveWhileReferenced = weak.get() != null;
        strong = null;
        collect();
        boolean aliveAfterRelease = weak.get() != null;
        return "while a strong reference exists the object survives " + aliveWhileReferenced + ", after the last strong reference is gone it survives " + aliveAfterRelease;
    }

    public static String softVersusWeak() {
        Object soft = new byte[1024];
        Object weak = new byte[1024];
        SoftReference<Object> softRef = new SoftReference<>(soft);
        WeakReference<Object> weakRef = new WeakReference<>(weak);
        soft = null;
        weak = null;
        collect();
        return "soft reference survives " + (softRef.get() != null) + ", weak reference survives " + (weakRef.get() != null);
    }

    static class Node {
        Node other;
        final byte[] payload = new byte[1024];
    }

    public static String cycles() {
        Node a = new Node();
        Node b = new Node();
        a.other = b;
        b.other = a;
        WeakReference<Node> watcherA = new WeakReference<>(a);
        WeakReference<Node> watcherB = new WeakReference<>(b);
        a = null;
        b = null;
        collect();
        return "two objects pointing at each other: first survives " + (watcherA.get() != null) + ", second survives " + (watcherB.get() != null);
    }

    public static String staticCacheLeak() {
        Object value = new byte[1024];
        WeakReference<Object> watcher = new WeakReference<>(value);
        CACHE.add(value);
        value = null;
        collect();
        boolean whileCached = watcher.get() != null;
        CACHE.clear();
        collect();
        boolean afterClearing = watcher.get() != null;
        return "held by a static list it survives " + whileCached + ", after the list is cleared it survives " + afterClearing;
    }

    public static String usedMemoryShrinks() {
        Runtime runtime = Runtime.getRuntime();
        byte[][] bulk = new byte[50][];
        for (int i = 0; i < bulk.length; i++) {
            bulk[i] = new byte[1024 * 1024];
        }
        collect();
        long held = runtime.totalMemory() - runtime.freeMemory();
        if (bulk[0].length == 0) {
            return "unreachable";
        }
        bulk = null;
        collect();
        long released = runtime.totalMemory() - runtime.freeMemory();
        return "after dropping 50 MB of arrays, used memory went down by at least 40 MB: " + (held - released >= 40L * 1024 * 1024);
    }
}
