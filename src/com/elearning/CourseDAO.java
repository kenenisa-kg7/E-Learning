package com.elearning;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {

    // Database connection method
    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/elearning_db";
        String username = "root"; // Database username
        String password = "fj3952"; // Database password
        return DriverManager.getConnection(url, username, password);
    }

    // Method to insert a new course into the database
    public boolean addCourse(String title, String description, int instructorId) {
        String query = "INSERT INTO courses (title, description, instructor_id) VALUES (?, ?, ?)";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, title);
            stmt.setString(2, description);
            stmt.setInt(3, instructorId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Method to fetch all courses from the database
    public List<Course> getCourses() {
        List<Course> courses = new ArrayList<>();
        String query = "SELECT * FROM courses";
        
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                int instructorId = rs.getInt("instructor_id");
                courses.add(new Course(id, title, description, instructorId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    // Method to fetch a specific course by its ID
    public Course getCourseById(int id) {
        Course course = null;
        String query = "SELECT * FROM courses WHERE id = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                String title = rs.getString("title");
                String description = rs.getString("description");
                int instructorId = rs.getInt("instructor_id");
                course = new Course(id, title, description, instructorId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return course;
    }
    
    // Method to update a course's information
    public boolean updateCourse(int id, String title, String description) {
        String query = "UPDATE courses SET title = ?, description = ? WHERE id = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, title);
            stmt.setString(2, description);
            stmt.setInt(3, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Method to delete a course
    public boolean deleteCourse(int id) {
        String query = "DELETE FROM courses WHERE id = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
