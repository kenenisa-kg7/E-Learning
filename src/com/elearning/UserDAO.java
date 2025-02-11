package com.elearning;

import java.sql.*;

public class UserDAO {

    // Method to authenticate the user based on email and password
    public String authenticate(String email, String password) {
        String role = null;
        try (Connection connection = JDBCUtil.getConnection()) {
            String query = "SELECT role FROM users WHERE email = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                role = resultSet.getString("role");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return role;
    }

    // Method to get user details by email
    public User getUserByEmail(String email) {
        User user = null;
        try (Connection connection = JDBCUtil.getConnection()) {
            String query = "SELECT * FROM users WHERE email = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.setEmail(resultSet.getString("email"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(resultSet.getString("role"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    // Method to update user profile information (name and password)
    public boolean updateUserProfile(String email, String name, String password) {
        boolean updated = false;
        try (Connection connection = JDBCUtil.getConnection()) {
            String query = "UPDATE users SET name = ?, password = ? WHERE email = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, password);
            statement.setString(3, email);
            int rowsAffected = statement.executeUpdate();

            updated = rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updated;
    }
}
