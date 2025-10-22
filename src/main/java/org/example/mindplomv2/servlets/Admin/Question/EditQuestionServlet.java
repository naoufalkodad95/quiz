package org.example.mindplomv2.servlets.Admin.Question;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.mindplomv2.dao.QuestionDAO;
import org.example.mindplomv2.model.Question;

import java.io.IOException;

@WebServlet("/Admin/Question/EditQuestionServlet")
public class EditQuestionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EditQuestionServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Retrieve question details from the request
        String questionIdStr = request.getParameter("id");
        String quizIdStr = request.getParameter("quizId");
        String questionText = request.getParameter("question");
        String optionA = request.getParameter("option_a");
        String optionB = request.getParameter("option_b");
        String optionC = request.getParameter("option_c");
        String optionD = request.getParameter("option_d");
        String correctAnswer = request.getParameter("correct_option");

        // Validate input
        if (questionIdStr == null || quizIdStr == null || questionText == null ||
            optionA == null || optionB == null || optionC == null || optionD == null || correctAnswer == null) {
            response.sendRedirect(request.getContextPath() + "../manage_questions.jsp?error=Invalid input");
            return;
        }

        try {
            int questionId = Integer.parseInt(questionIdStr);
            int quizId = Integer.parseInt(quizIdStr);

            // Create and populate the Question object
            Question question = new Question();
            question.setId(questionId);
            question.setQuestionText(questionText);
            question.setOptionA(optionA);
            question.setOptionB(optionB);
            question.setOptionC(optionC);
            question.setOptionD(optionD);
            question.setCorrectAnswer(correctAnswer);

            // Update the question in the database
            QuestionDAO questionDAO = new QuestionDAO();
            boolean success = questionDAO.updateQuestion(question);

            if (success) {
                response.sendRedirect(request.getContextPath() + "../manage_questions.jsp?quizId=" + quizId + "&message=Question mise à jour avec succès");
            } else {
                response.sendRedirect(request.getContextPath() + "../manage_questions.jsp?quizId=" + quizId + "&error=Erreur lors de la mise à jour de la question");
            }
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "../manage_questions.jsp?error=Invalid parameter format");
        } catch (Exception e) {
            throw new ServletException("Erreur lors de la mise à jour de la question", e);
        }
    }
}
