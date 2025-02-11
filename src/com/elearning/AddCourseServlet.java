@WebServlet("/addCourse")
public class AddCourseServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        int instructorId = Integer.parseInt(request.getParameter("instructorId"));
        
        CourseDAO courseDAO = new CourseDAO();
        boolean success = courseDAO.addCourse(title, description, instructorId);

        if (success) {
            response.sendRedirect("courses"); // Redirect to list of courses
        } else {
            response.sendRedirect("add-course.html?error=unable_to_add_course");
        }
    }
}

