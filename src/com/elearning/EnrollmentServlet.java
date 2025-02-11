package com.elearning;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

@WebServlet("/enroll")
public class EnrollmentServlet extends HttpServlet {
    // Assuming enrollments are stored in a database; using in-memory map for demo purposes
    private Map<String, List<String>> enrollments = new HashMap<>();

    // Handle enrollment requests
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = (String) request.getSession().getAttribute("user");  // Get user email from session
        String course = request.getParameter("course");  // Get the course the user wants to enroll in

        if (email != null) {  // User must be logged in to enroll
            // Add course to the user's list of enrolled courses
            enrollments.computeIfAbsent(email, k -> new ArrayList<>()).add(course);
            
            // Optionally, update the database with the new enrollment (use CourseDAO for actual persistence)
            // Example: userDAO.enrollUserInCourse(email, course);
            
            // Redirect to the user dashboard after enrolling
            response.sendRedirect("user-dashboard.html");  // Redirect to user dashboard HTML
        } else {
            // If the user is not logged in, redirect to the login page
            response.sendRedirect("login.html");  // Redirect to login HTML page
        }
    }
}
