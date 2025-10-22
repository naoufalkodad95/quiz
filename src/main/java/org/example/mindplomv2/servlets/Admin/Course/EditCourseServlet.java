package org.example.mindplomv2.servlets.Admin.Course;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.mindplomv2.dao.CourseDAO;
import org.example.mindplomv2.model.Course;

import java.io.IOException;

@WebServlet("/Admin/Course/EditCourseServlet")
public class EditCourseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EditCourseServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Retrieve parameters from the JSP form
            String idParam = request.getParameter("id");
            String name = request.getParameter("name");

            // Validate parameters
            if (idParam == null || idParam.trim().isEmpty() || name == null || name.trim().isEmpty()) {
                request.getSession().setAttribute("message", "Erreur: Paramètres manquants ou invalides.");
                request.getSession().setAttribute("messageType", "error");
                response.sendRedirect(request.getContextPath() + "/Admin/manage_courses.jsp");
                return;
            }

            // Parse course ID and create the course object
            int id = Integer.parseInt(idParam);
            Course course = new Course();
            course.setId(id);
            course.setName(name);

            // Initialize CourseDAO and update the course in the database
            CourseDAO courseDAO = new CourseDAO();
            boolean updated = courseDAO.updateCourse(course);

            // Set the message for the user based on the update result

            if (updated) {
                response.sendRedirect(request.getContextPath() + "/Admin/manage_courses.jsp?message=Le+Cours+a+ %C3%A9t%C3%A9+ mis+ %C3%A0 +jour+avec+succ%C3%A8s&messageType=success");

            } else {
                response.sendRedirect(request.getContextPath() + "/Admin/manage_courses.jsp?message=Erreur +lors+ de+ la+ modification +du +quiz&messageType=danger");
            }

            // Redirect to the manage_courses.jsp page with the appropriate message

        } catch (NumberFormatException e) {
            // Handle invalid ID format (not a number)
            request.getSession().setAttribute("message", "Erreur: L'ID doit être un entier.");
            request.getSession().setAttribute("messageType", "error");
            response.sendRedirect(request.getContextPath() + "/Admin/manage_courses.jsp");
        } catch (Exception e) {
            // Handle unexpected exceptions
            request.getSession().setAttribute("message", "Une erreur inattendue s'est produite.");
            request.getSession().setAttribute("messageType", "error");
            response.sendRedirect(request.getContextPath() + "/Admin/manage_courses.jsp");
        }
    }
}
