package com.elearning;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        
        // Check if the passwords match
        if (!password.equals(confirmPassword)) {
            // If passwords don't match, redirect to register page with error message
            response.sendRedirect("register.html?error=true");
            return;
        }
        
        // Create an instance of UserDAO to register the user
        UserDAO userDAO = new UserDAO();
        
        // Assuming UserDAO has a method to register a new user
        if (userDAO.register(email, password)) {
            // Registration successful, redirect to login page
            response.sendRedirect("login.html");
        } else {
            // Registration failed, show an error
            response.sendRedirect("register.html?error=true");
        }
    }
}

