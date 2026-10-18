package dev.canhdominich.core.oop.encapsulation;

import java.util.ArrayList;
import java.util.List;

public class SafeCourse {

    private final List<String> students = new ArrayList<>();
    private final int capacity;

    public SafeCourse(int capacity) {
        this.capacity = capacity;
    }

    public boolean enrol(String student) {
        if (students.size() >= capacity) {
            return false;
        }
        return students.add(student);
    }

    public List<String> students() {
        return List.copyOf(students);
    }

    public int capacity() {
        return capacity;
    }
}
