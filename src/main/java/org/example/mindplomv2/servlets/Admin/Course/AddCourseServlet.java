package org.example.mindplomv2.servlets.Admin.Course;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.example.mindplomv2.dao.CourseDAO;
import org.example.mindplomv2.model.Course;

import java.io.IOException;

@WebServlet("/Admin/Course/AddCourseServlet")
public class AddCourseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AddCourseServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer le nom du cours
        String courseName = request.getParameter("courseName");

        // Vérifier si le nom du cours est valide
        if (courseName == null || courseName.trim().isEmpty()) {
            // Redirection avec un message d'erreur
        // Exemple de redirection
            response.flushBuffer();
            response.sendRedirect(request.getContextPath() + "/Admin/manage_courses.jsp?message=Erreur+lors+de+l'ajout+du+cours&messageType=danger");
            return; // Terminer l'exécution ici
        }

        // Créer un nouvel objet Course
        Course course = new Course();
        course.setName(courseName);

        // Ajouter le cours via le DAO
        CourseDAO courseDAO = new CourseDAO();
        boolean courseAdded = courseDAO.addCourse(course);

        // Rediriger avec un message approprié
        if (courseAdded) {
            response.sendRedirect(request.getContextPath() + "/Admin/manage_courses.jsp?message=Cours+ajout%C3%A8+avec+succ%C3%A8s&messageType=success");

        } else {
            response.sendRedirect(request.getContextPath() + "/Admin/manage_courses.jsp?message=Cours+inexistant&messageType=danger");
        }
    }
}
