package org.example.mindplomv2.model;

public class Quiz {
    private int id;
    private int courseId;
    private String level;
    private String name;

    // Default constructor
    public Quiz() {}

    // Parameterized constructor
    public Quiz(int id, int courseId, String level, String name) {
        this.id = id;
        this.courseId = courseId;
        this.level = level;
        this.name = name;
    }

    // Optional constructor with only level and name
    public Quiz(String level, String name) {
        this.level = level;
        this.name = name;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        if (courseId <= 0) {
            throw new IllegalArgumentException("Course ID must be greater than zero.");
        }
        this.courseId = courseId;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        if (level == null || level.trim().isEmpty()) {
            throw new IllegalArgumentException("Level cannot be null or empty.");
        }
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        this.name = name;
    }

    // toString method for easy printing
    @Override
    public String toString() {
        return String.format("Quiz [id=%d, courseId=%d, level='%s', name='%s']",
                             id, courseId, level, name);
    }

    // Validating that quiz has all required fields
    public boolean isValid() {
        return (level != null && !level.trim().isEmpty()) &&
               (name != null && !name.trim().isEmpty()) &&
               (courseId > 0);
    }
}
