package com.elearning;

import java.io.IOException;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;

public class CourseServlet extends HttpServlet {
    
    // Handle GET request to show all courses
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Create an instance of CourseDAO to fetch the courses from the database
        CourseDAO courseDAO = new CourseDAO();
        List<Course> courses = courseDAO.getAllCourses(); // Assuming Course is a class representing a course entity

        // Set response content type
        response.setContentType("text/html");
        
        // Get the PrintWriter to send HTML output to the client
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Available Courses</h1>");
        out.println("<ul>");

        // Loop through the courses and display each one
        for (Course course : courses) {
            out.println("<li>" + course.getTitle() + " - " + course.getDescription() + "</li>");
        }

        out.println("</ul>");
        out.println("</body></html>");
    }
}
