package com.elearning;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    // Handle GET request to log out the user
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Invalidate the session to log out the user
        request.getSession().invalidate();  // Remove the user's session

        // Redirect the user to the login page after logging out
        response.sendRedirect("login.html");  // Redirect to login page
    }
}
