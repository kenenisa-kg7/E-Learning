package com.elearning;

public class Course {
    private int id;
    private String title;
    private String description;
    private int instructorId;

    // Constructor
    public Course(int id, String title, String description, int instructorId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.instructorId = instructorId;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(int instructorId) {
        this.instructorId = instructorId;
    }
}
