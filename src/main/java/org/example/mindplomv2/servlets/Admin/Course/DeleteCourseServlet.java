package org.example.mindplomv2.servlets.Admin.Course;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.mindplomv2.dao.CourseDAO;

import java.io.IOException;
@WebServlet("/Admin/Course/DeleteCourseServlet")
public class DeleteCourseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DeleteCourseServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Retrieve the course ID from the request
            String idParam = request.getParameter("id");
            if (idParam == null || idParam.isEmpty()) {
                request.setAttribute("message", "ID manquant. Veuillez fournir un ID valide.");
                request.setAttribute("messageType", "error");
                request.getRequestDispatcher("../manage_courses.jsp").forward(request, response);
                return;
            }

            int id = Integer.parseInt(idParam);

            // Perform the deletion using DAO
            CourseDAO courseDAO = new CourseDAO();
            boolean deleted = courseDAO.deleteCourse(id);

            if (deleted) {
                // Redirect to the admin page after successful deletion
                response.sendRedirect(request.getContextPath() + "/Admin/manage_courses.jsp?message=Le+cours+a+%C3%A9t%C3%A9+supprim%C3%A9+avec+succ%C3%A8s&messageType=success");

            } else {
                // Set error message and refresh the course list
                request.setAttribute("message", "Erreur lors de la suppression du cours. Veuillez réessayer.");
                request.setAttribute("messageType", "error");
                request.setAttribute("courses", courseDAO.getAllCourses());
                request.getRequestDispatcher("../manage_courses.jsp").forward(request, response);
            }


        } catch (NumberFormatException e) {
            // Handle invalid ID format
            request.setAttribute("message", "ID invalide. Veuillez fournir un ID numérique valide.");
            request.setAttribute("messageType", "error");
            request.getRequestDispatcher("../manage_courses.jsp").forward(request, response);

        } catch (Exception e) {
            // Handle unexpected errors
            request.setAttribute("message", "Une erreur inattendue s'est produite. Veuillez réessayer plus tard.");
            request.setAttribute("messageType", "error");
            request.getRequestDispatcher("../manage_courses.jsp").forward(request, response);
        }
    }
}
