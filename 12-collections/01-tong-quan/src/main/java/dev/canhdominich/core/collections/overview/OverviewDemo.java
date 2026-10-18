package dev.canhdominich.core.collections.overview;

public class OverviewDemo {

    public static void main(String[] args) {
        for (String line : Overview.interfacesOf()) {
            System.out.println("interfaces: " + line);
        }
        System.out.println("hierarchy    = " + Overview.subInterfaces());
        System.out.println("orders       = " + Overview.iterationOrders());
        System.out.println("duplicates   = " + Overview.duplicatesAndNulls());
        System.out.println("immutable    = " + Overview.immutableFactories());
        System.out.println("lookup 1000  = " + Overview.lookupCost(1000));
        System.out.println("lookup 100000= " + Overview.lookupCost(100_000));
        System.out.println("utilities    = " + Overview.utilityMethods());
        String rawAdd = "import java.util.*; class A { void f() { Collection<String> c = new ArrayList<>(); c.get(0); } }";
        String mapIsNotCollection = "import java.util.*; class A { Collection<String> f(Map<String, Integer> m) { return m; } }";
        String instantiateInterface = "import java.util.*; class A { List<String> l = new List<>(); }";
        String setIndex = "import java.util.*; class A { String f(Set<String> s) { return s.get(0); } }";
        String addWrongType = "import java.util.*; class A { void f(Set<String> s) { s.add(1); } }";
        System.out.println("get on Collection        = " + Javac.messages("A", rawAdd));
        System.out.println("Map as Collection        = " + Javac.messages("A", mapIsNotCollection));
        System.out.println("new List<>()             = " + Javac.messages("A", instantiateInterface));
        System.out.println("index into a Set         = " + Javac.messages("A", setIndex));
        System.out.println("wrong element type       = " + Javac.messages("A", addWrongType));
    }
}
