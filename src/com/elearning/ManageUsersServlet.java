package com.elearning;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/manage-users")
public class ManageUsersServlet extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Fetch users from the database and display
        UserDAO userDAO = new UserDAO();
        request.setAttribute("users", userDAO.getAllUsers());
        request.getRequestDispatcher("manage-users.html").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("delete".equals(action)) {
            String userId = request.getParameter("userId");
            UserDAO userDAO = new UserDAO();
            userDAO.deleteUser(userId);
            response.sendRedirect("manage-users");
        }
    }
}
