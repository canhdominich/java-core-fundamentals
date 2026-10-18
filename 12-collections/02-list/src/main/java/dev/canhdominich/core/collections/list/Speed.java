package dev.canhdominich.core.collections.list;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public final class Speed {

    private Speed() {
    }

    interface Job {
        void run(List<Integer> list);
    }

    static long best(List<Integer> prototype, boolean linked, Job job) {
        long best = Long.MAX_VALUE;
        for (int round = 0; round < 3; round++) {
            List<Integer> list = linked ? new LinkedList<>(prototype) : new ArrayList<>(prototype);
            long start = System.nanoTime();
            job.run(list);
            best = Math.min(best, System.nanoTime() - start);
        }
        return best / 1_000_000;
    }

    public static void main(String[] args) {
        List<Integer> base = new ArrayList<>();
        for (int i = 0; i < 50_000; i++) {
            base.add(i);
        }
        Job readByIndex = list -> {
            long sum = 0;
            for (int i = 0; i < list.size(); i++) {
                sum += list.get(i);
            }
            if (sum < 0) {
                throw new IllegalStateException();
            }
        };
        Job insertAtFront = list -> {
            for (int i = 0; i < 50_000; i++) {
                list.add(0, i);
            }
        };
        Job appendAtEnd = list -> {
            for (int i = 0; i < 200_000; i++) {
                list.add(i);
            }
        };
        Job iterate = list -> {
            long sum = 0;
            for (int value : list) {
                sum += value;
            }
            if (sum < 0) {
                throw new IllegalStateException();
            }
        };
        report("read 50000 elements by index", base, readByIndex);
        report("insert 50000 at the front   ", base, insertAtFront);
        report("append 200000 at the end    ", new ArrayList<>(), appendAtEnd);
        report("iterate with for-each       ", base, iterate);
    }

    static void report(String label, List<Integer> prototype, Job job) {
        long array = best(prototype, false, job);
        long linked = best(prototype, true, job);
        System.out.println(label + ": ArrayList " + array + " ms, LinkedList " + linked + " ms");
    }
}
