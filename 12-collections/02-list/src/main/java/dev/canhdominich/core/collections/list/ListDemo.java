package dev.canhdominich.core.collections.list;

public class ListDemo {

    public static void main(String[] args) {
        System.out.println("basics            = " + ListOps.basics());
        System.out.println("remove(int)       = " + ListOps.removeByIndexOrByValue());
        System.out.println("out of range      = " + ListOps.outOfRange());
        System.out.println("list equality     = " + ListOps.listEqualsComparesElementsInOrder());
        System.out.println("subList           = " + ListOps.subListIsAView());
        System.out.println("ListIterator      = " + ListOps.listIteratorWorksBackwards());
        System.out.println("asList            = " + ListOps.fixedSizeAndImmutableLists());
        System.out.println("remove in for-each= " + Removal.removeInsideForEach());
        System.out.println("second to last   = " + Removal.removeTheSecondToLastInsideForEach());
        System.out.println("forward index loop= " + Removal.removeByIndexLoopSkipsElements());
        System.out.println("Iterator.remove   = " + Removal.removeWithIterator());
        System.out.println("removeIf          = " + Removal.removeIf());
        System.out.println("backward loop     = " + Removal.removeBackwards());
        System.out.println("iterate a copy    = " + Removal.copyThenRemove());
    }
}
