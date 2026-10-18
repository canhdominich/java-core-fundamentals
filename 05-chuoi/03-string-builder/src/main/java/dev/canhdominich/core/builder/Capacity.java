package dev.canhdominich.core.builder;

import java.util.ArrayList;
import java.util.List;

public class Capacity {

    public static String initial() {
        return "default " + new StringBuilder().capacity() + ", from text of 5 " + new StringBuilder("hello").capacity()
                + ", requested 100 " + new StringBuilder(100).capacity();
    }

    public static List<Integer> growth(int appends) {
        StringBuilder sb = new StringBuilder();
        List<Integer> capacities = new ArrayList<>();
        int last = -1;
        for (int i = 0; i < appends; i++) {
            sb.append('x');
            if (sb.capacity() != last) {
                last = sb.capacity();
                capacities.add(last);
            }
        }
        return capacities;
    }

    public static String trimAndEnsure() {
        StringBuilder sb = new StringBuilder();
        sb.append("hello");
        int before = sb.capacity();
        sb.trimToSize();
        int trimmed = sb.capacity();
        sb.ensureCapacity(500);
        return "before " + before + ", after trimToSize " + trimmed + ", after ensureCapacity(500) " + sb.capacity() + ", length stays " + sb.length();
    }

    public static void main(String[] args) {
        System.out.println("initial capacity       = " + initial());
        System.out.println("capacity as 1000 chars are appended one by one = " + growth(1000));
        System.out.println("trim and ensure        = " + trimAndEnsure());
    }
}
