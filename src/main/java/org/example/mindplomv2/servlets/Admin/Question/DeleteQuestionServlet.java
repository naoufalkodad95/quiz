package org.example.mindplomv2.servlets.Admin.Question;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.mindplomv2.dao.QuestionDAO;

import java.io.IOException;

@WebServlet("/Admin/Question/DeleteQuestionServlet")
public class DeleteQuestionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DeleteQuestionServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Retrieve question ID and quiz ID from the request parameters
        String questionIdStr = request.getParameter("id");
        String quizIdStr = request.getParameter("quizId");

        if (questionIdStr == null || quizIdStr == null) {
            // Redirect back with an error if parameters are missing
            response.sendRedirect(request.getContextPath() + "../manage_questions.jsp?error=Missing parameters");
            return;
        }

        try {
            int questionId = Integer.parseInt(questionIdStr);
            int quizId = Integer.parseInt(quizIdStr);

            // Delete the question
            QuestionDAO questionDAO = new QuestionDAO();
            boolean success = questionDAO.deleteQuestion(questionId);

            if (success) {
                response.sendRedirect(request.getContextPath() + "../manage_questions.jsp?quizId=" + quizId + "&message=Question supprimée avec succès");
            } else {
                response.sendRedirect(request.getContextPath() + "../manage_questions.jsp?quizId=" + quizId + "&error=Erreur lors de la suppression de la question");
            }
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "../manage_questions.jsp?error=Invalid parameter format");
        } catch (Exception e) {
            throw new ServletException("Erreur lors de la suppression de la question", e);
        }
    }
}
