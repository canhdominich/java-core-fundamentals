package dev.canhdominich.core.oop.encapsulation;

import java.util.ArrayList;
import java.util.List;

public class LeakyCourse {

    private final List<String> students = new ArrayList<>();
    private final int capacity;

    public LeakyCourse(int capacity) {
        this.capacity = capacity;
    }

    public boolean enrol(String student) {
        if (students.size() >= capacity) {
            return false;
        }
        return students.add(student);
    }

    public List<String> students() {
        return students;
    }

    public int capacity() {
        return capacity;
    }
}
