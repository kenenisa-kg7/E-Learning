package com.elearning;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

@WebServlet("/user")
public class UserServlet extends HttpServlet {

    // Display user profile information or handle update requests
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the email from session to identify the logged-in user
        String email = (String) request.getSession().getAttribute("user");

        if (email != null) {
            // User is logged in, fetch user details
            UserDAO userDAO = new UserDAO();
            User user = userDAO.getUserByEmail(email); // Fetch user details from the database

            if (user != null) {
                // Set the user data as request attributes
                request.setAttribute("user", user);
                // Forward the request to the user profile page (user-dashboard.html)
                RequestDispatcher dispatcher = request.getRequestDispatcher("user-dashboard.html");
                dispatcher.forward(request, response);
            } else {
                // User not found in the database
                response.sendRedirect("error.html");
            }
        } else {
            // If not logged in, redirect to login page
            response.sendRedirect("login.html");
        }
    }

    // Handle form submissions for user profile updates
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the email from session to identify the logged-in user
        String email = (String) request.getSession().getAttribute("user");

        if (email != null) {
            // Get updated user information from the form
            String name = request.getParameter("name");
            String password = request.getParameter("password");

            // Create a UserDAO object to update user information in the database
            UserDAO userDAO = new UserDAO();

            // Update the user's details
            boolean isUpdated = userDAO.updateUserProfile(email, name, password);

            if (isUpdated) {
                // Successfully updated, redirect to the user dashboard
                response.sendRedirect("user-dashboard.html");
            } else {
                // Update failed, redirect to an error page
                response.sendRedirect("error.html");
            }
        } else {
            // If not logged in, redirect to login page
            response.sendRedirect("login.html");
        }
    }

    // Handle logout functionality
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Invalidate the session to log the user out
        request.getSession().invalidate();
        response.sendRedirect("login.html");
    }
}
