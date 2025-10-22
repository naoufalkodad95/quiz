package org.example.mindplomv2.servlets.Admin.Quize;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.example.mindplomv2.dao.QuizDAO;

@WebServlet("/Admin/Quize/DeleteQuizServlet")
public class DeleteQuizServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DeleteQuizServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String quizIdStr = request.getParameter("id");

        // Validate quiz ID
        if (quizIdStr == null || quizIdStr.isEmpty()) {
            request.getSession().setAttribute("error", "ID de quiz manquant.");
            response.sendRedirect("../manage_quizzes.jsp");
            return;
        }

        int quizId;
        try {
            quizId = Integer.parseInt(quizIdStr); // Convert ID to integer
        } catch (NumberFormatException e) {
            request.getSession().setAttribute("error", "ID de quiz invalide.");
            response.sendRedirect("../manage_quizzes.jsp");
            return;
        }

        // Delete the quiz using QuizDAO
        boolean success = QuizDAO.deleteQuiz(quizId);

        // Set a message based on whether the quiz was deleted successfully
        if (success) {
            response.sendRedirect(request.getContextPath() + "/Admin/manage_quizzes.jsp?message=Le+Quiz+a+%C3%A9t%C3%A9+supprim%C3%A9+avec+succ%C3%A8s&messageType=success");


        } else {
            request.getSession().setAttribute("error", "Erreur lors de la suppression du quiz.");
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Not used in this servlet
    }
}
