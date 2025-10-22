package org.example.mindplomv2.servlets.Admin.Quize;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.mindplomv2.dao.CourseDAO;
import org.example.mindplomv2.dao.QuizDAO;
import org.example.mindplomv2.model.Course;
import org.example.mindplomv2.model.Quiz;

import java.io.IOException;
import java.util.List;

@WebServlet("/Admin/Quize/AddQuizServlet")
public class AddQuizServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AddQuizServlet() {
        super();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Charger les cours
        CourseDAO courseDAO = new CourseDAO();
        List<Course> courses = courseDAO.getAllCourses(); // Récupérer les cours

        // Charger les quiz
        QuizDAO quizDAO = new QuizDAO();
        List<Quiz> quizzes = quizDAO.getAllQuizzes(); // Récupérer les quiz

        // Passer les cours et les quiz à la JSP
        request.setAttribute("courses", courses);
        request.setAttribute("quizzes", quizzes);

        // Rediriger vers la page de gestion des quiz
        request.getRequestDispatcher("../manage_quizzes.jsp").forward(request, response);
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer les paramètres du formulaire
        String quizName = request.getParameter("quizName");
        String level = request.getParameter("level");
        String courseIdStr = request.getParameter("courseId");

        // Valider les paramètres
        if (quizName == null || quizName.trim().isEmpty() || level == null || level.trim().isEmpty() || courseIdStr == null || courseIdStr.trim().isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/Admin/manage_quizzes.jsp?message=Paramètres invalides&messageType=error");
            return;
        }

        // Convertir courseId en entier
        int courseId;
        try {
            courseId = Integer.parseInt(courseIdStr);
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/Admin/manage_quizzes.jsp?message=ID de cours invalide&messageType=error");
            return;
        }

        // Créer un objet Quiz
        Quiz quiz = new Quiz();
        quiz.setName(quizName);
        quiz.setLevel(level);
        quiz.setCourseId(courseId);

        // Ajouter le quiz à la base de données
        boolean success = QuizDAO.addQuiz(quiz);

        // Rediriger avec un message de succès ou d'erreur
        if (success) {

            response.sendRedirect(request.getContextPath() + "/Admin/manage_quizzes.jsp?message=Quiz+ajout%C3%A8+avec+succ%C3%A8s&messageType=success");

        } else {
            response.sendRedirect(request.getContextPath() + "/Admin/manage_quizzes.jsp?message=Quiz+inexistant&messageType=danger");
        }
    }
}
