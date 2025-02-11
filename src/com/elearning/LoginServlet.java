package com.elearning;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get parameters from the login form
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        // Create an instance of UserDAO to authenticate
        UserDAO userDAO = new UserDAO();
        
        // Authenticate the user and get their role
        String role = userDAO.authenticateAndGetRole(email, password);
        
        if (role != null) {
            // Set the user role and email in the session
            HttpSession session = request.getSession();
            session.setAttribute("email", email);
            session.setAttribute("role", role);
            
            // Redirect based on the role
            if (role.equals("admin")) {
                response.sendRedirect("admin-dashboard.html"); // Admin-specific page
            } else if (role.equals("user")) {
                response.sendRedirect("dashboard.html"); // User-specific page
            } else {
                // Unknown role, redirect to login with an error
                response.sendRedirect("login.html?error=unknown_role");
            }
        } else {
            // Authentication failed, redirect to login.html with an error parameter
            response.sendRedirect("login.html?error=invalid_credentials");
        }
    }
}
