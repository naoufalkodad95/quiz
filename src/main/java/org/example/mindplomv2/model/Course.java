package org.example.mindplomv2.model;

import java.util.Objects;

public class Course {
    private int id;
    private String name;

    // Default constructor
    public Course() {}

    // Constructor with parameters
    public Course(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Course name cannot be null or empty.");
        }
    }

    // Override toString() method for better logging
    @Override
    public String toString() {
        return "Course{id=" + id + ", name='" + name + "'}";
    }

    // Override equals() method to compare Course objects based on id and name
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id == course.id && name.equals(course.name);
    }

    // Override hashCode() method for consistent hashing when using collections
    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
