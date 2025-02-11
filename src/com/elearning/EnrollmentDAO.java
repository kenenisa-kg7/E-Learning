package com.elearning;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EnrollmentDAO {
    public boolean enrollUser(int userId, int courseId) {
        try (Connection connection = JDBCUtil.getConnection()) {
            String query = "INSERT INTO enrollments (user_id, course_id) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            statement.setInt(2, courseId);

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

