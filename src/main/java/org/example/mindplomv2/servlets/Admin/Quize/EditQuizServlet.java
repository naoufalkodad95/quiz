package org.example.mindplomv2.servlets.Admin.Quize;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.mindplomv2.dao.QuizDAO;
import org.example.mindplomv2.model.Quiz;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/Admin/Quize/EditQuizServlet")
public class EditQuizServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(EditQuizServlet.class.getName());

    public EditQuizServlet() {
        super();
    }

    // Handle GET request to show the quiz details in the edit form
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String quizIdStr = request.getParameter("id");

        // Validate quiz ID
        if (isQuizIdInvalid(quizIdStr)) {
            showErrorMessage(request, response, "Quiz ID is missing or invalid.");
            return;
        }

        int quizId = Integer.parseInt(quizIdStr);

        // Fetch the quiz from the database
        Quiz quiz = QuizDAO.getQuizById(quizId);

        // If quiz found, forward to the edit page, else show error
        if (quiz != null) {
            request.setAttribute("quiz", quiz);
            response.sendRedirect(request.getContextPath() + "/Admin/manage_quizzes.jsp");
        } else {
            showErrorMessage(request, response, "Quiz not found.");
        }
    }

    // Handle POST request to update the quiz details
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer les paramètres du formulaire
        String quizIdStr = request.getParameter("id");
        String quizName = request.getParameter("quizName");
        String level = request.getParameter("level");

        if (quizIdStr == null || quizName == null || level == null) {
            request.setAttribute("error", "Tous les champs sont obligatoires !");
            response.sendRedirect(request.getContextPath() + "/Admin/manage_quizzes.jsp");
            return;
        }

        int quizId = Integer.parseInt(quizIdStr);

        // Créer un objet Quiz avec les nouvelles données
        Quiz quiz = new Quiz();
        quiz.setId(quizId);
        quiz.setName(quizName);
        quiz.setLevel(level);


        // Mettre à jour le quiz dans la base de données
        boolean success = QuizDAO.updateQuiz(quiz);

        if (success) {
            response.sendRedirect(request.getContextPath() + "/Admin/manage_quizzes.jsp?message=quiz+a+ %C3%A9t%C3%A9+ mis+ %C3%A0 +jour+avec+succ%C3%A8s&messageType=success");

        } else {

            response.sendRedirect(request.getContextPath() + "/Admin/manage_quizzes.jsp?message=Erreur +lors+ de+ la+ modification +du +quiz&messageType=danger");

        }

    }

    /**
     * Checks if the quiz ID is invalid.
     * @param quizIdStr The quiz ID as a string.
     * @return true if invalid, false otherwise.
     */
    private boolean isQuizIdInvalid(String quizIdStr) {
        return quizIdStr == null || quizIdStr.isEmpty();
    }

    /**
     * Checks if the input data for quiz update is invalid.
     * @param quizIdStr The quiz ID as a string.
     * @param quizName  The name of the quiz.
     * @param level     The level of the quiz.
     * @return true if any field is invalid, false otherwise.
     */
    private boolean isInputInvalid(String quizIdStr, String quizName, String level) {
        return quizIdStr == null || quizIdStr.isEmpty() || quizName == null || quizName.isEmpty() || level == null || level.isEmpty();
    }

    /**
     * Helper method to show an error message and forward the request to the manage_quizzes.jsp page.
     * @param request The HTTP request.
     * @param response The HTTP response.
     * @param errorMessage The error message to display.
     * @throws ServletException If an error occurs during request forwarding.
     * @throws IOException If an error occurs during request forwarding.
     */
    private void showErrorMessage(HttpServletRequest request, HttpServletResponse response, String errorMessage) throws ServletException, IOException {
        request.setAttribute("error", errorMessage);
        request.getRequestDispatcher("../manage_quizzes.jsp").forward(request, response);
    }
}
