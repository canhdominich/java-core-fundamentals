package dev.canhdominich.core.collections.ordering;

public class Student implements Comparable<Student> {

    private final String name;
    private final int score;

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String name() {
        return name;
    }

    public int score() {
        return score;
    }

    @Override
    public int compareTo(Student other) {
        return Integer.compare(score, other.score);
    }

    @Override
    public String toString() {
        return name + ":" + score;
    }
}
