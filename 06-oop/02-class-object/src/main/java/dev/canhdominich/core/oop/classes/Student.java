package dev.canhdominich.core.oop.classes;

public class Student {

    private static int created;

    String name;
    int score;
    boolean passed;
    double average;
    int[] marks;

    public Student() {
        created++;
    }

    public Student(String name, int score) {
        this();
        this.name = name;
        this.score = score;
    }

    public void improve(int points) {
        score += points;
        passed = score >= 50;
    }

    public String describe() {
        return name + " has " + score + (passed ? " (passed)" : " (not passed)");
    }

    public static int created() {
        return created;
    }

    public static void resetCount() {
        created = 0;
    }
}
