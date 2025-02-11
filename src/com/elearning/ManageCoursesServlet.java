package com.elearning;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/manage-courses")
public class ManageCoursesServlet extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Fetch courses from the database and display
        CourseDAO courseDAO = new CourseDAO();
        request.setAttribute("courses", courseDAO.getAllCourses());
        request.getRequestDispatcher("manage-courses.html").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("delete".equals(action)) {
            String courseId = request.getParameter("courseId");
            CourseDAO courseDAO = new CourseDAO();
            courseDAO.deleteCourse(courseId);
            response.sendRedirect("manage-courses");
        }
    }
}
