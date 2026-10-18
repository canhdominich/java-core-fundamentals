package dev.canhdominich.core.collections.setmap;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public final class Sets {

    private Sets() {
    }

    public static String algebra() {
        Set<Integer> a = new TreeSet<>(Set.of(1, 2, 3, 4));
        Set<Integer> b = new TreeSet<>(Set.of(3, 4, 5));
        Set<Integer> union = new TreeSet<>(a);
        union.addAll(b);
        Set<Integer> intersection = new TreeSet<>(a);
        intersection.retainAll(b);
        Set<Integer> difference = new TreeSet<>(a);
        difference.removeAll(b);
        return "a " + a + ", b " + b + ", union " + union + ", intersection " + intersection + ", a minus b " + difference;
    }

    public static String addReturnsWhetherItChanged() {
        Set<String> set = new HashSet<>();
        return "first add " + set.add("x") + ", second add of the same " + set.add("x") + ", size " + set.size();
    }

    public static String contractWithoutHashCode() {
        Set<BadPoint> bad = new HashSet<>();
        bad.add(new BadPoint(1, 2));
        Set<GoodPoint> good = new HashSet<>();
        good.add(new GoodPoint(1, 2));
        return "equals but no hashCode: contains " + bad.contains(new BadPoint(1, 2)) + ", size after adding an equal point " + addAndSize(bad)
                + " | equals and hashCode: contains " + good.contains(new GoodPoint(1, 2));
    }

    private static int addAndSize(Set<BadPoint> set) {
        set.add(new BadPoint(1, 2));
        return set.size();
    }

    public static String treeSetOperations() {
        TreeSet<Integer> numbers = new TreeSet<>(Set.of(10, 20, 30, 40));
        return "first " + numbers.first() + ", last " + numbers.last() + ", ceiling(25) " + numbers.ceiling(25) + ", floor(25) " + numbers.floor(25)
                + ", headSet(30) " + numbers.headSet(30) + ", tailSet(30) " + numbers.tailSet(30);
    }
}
