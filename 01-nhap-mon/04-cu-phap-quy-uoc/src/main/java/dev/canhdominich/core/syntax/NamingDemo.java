package dev.canhdominich.core.syntax;

import java.util.List;
import java.util.Map;

public class NamingDemo {

    record Sample(NamingConventions.Kind kind, String name) {
    }

    public static void main(String[] args) {
        List<Sample> samples = List.of(
                new Sample(NamingConventions.Kind.CLASS, "StudentAccount"),
                new Sample(NamingConventions.Kind.CLASS, "studentAccount"),
                new Sample(NamingConventions.Kind.CLASS, "Student_Account"),
                new Sample(NamingConventions.Kind.METHOD_OR_VARIABLE, "totalPrice"),
                new Sample(NamingConventions.Kind.METHOD_OR_VARIABLE, "TotalPrice"),
                new Sample(NamingConventions.Kind.METHOD_OR_VARIABLE, "total_price"),
                new Sample(NamingConventions.Kind.CONSTANT, "MAX_SIZE"),
                new Sample(NamingConventions.Kind.CONSTANT, "maxSize"),
                new Sample(NamingConventions.Kind.PACKAGE, "com.company.app"),
                new Sample(NamingConventions.Kind.PACKAGE, "Com.Company.App"));
        for (Sample sample : samples) {
            System.out.printf("%-20s %-18s %s%n", sample.kind(), sample.name(), NamingConventions.follows(sample.kind(), sample.name()) ? "follows" : "breaks the convention");
        }
        Map<String, Boolean> compiles = Map.of("studentAccount as a class name", SourceChecks.compiles("class studentAccount {}"), "my-var as a variable name", SourceChecks.compiles("class A { int my-var = 1; }"));
        compiles.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(e -> System.out.println("compiles? " + e.getKey() + " -> " + e.getValue()));
    }
}
