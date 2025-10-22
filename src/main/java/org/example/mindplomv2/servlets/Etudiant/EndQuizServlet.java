package org.example.mindplomv2.servlets.Etudiant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.example.mindplomv2.dao.QuizDAO;
import org.example.mindplomv2.model.Question;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/Etudiant/endQuiz")
public class EndQuizServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EndQuizServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Retrieve and validate quiz ID
            String quizIdParam = request.getParameter("quizId");
            if (quizIdParam == null || quizIdParam.isEmpty()) {
                request.setAttribute("errorMessage", "ID de quiz manquant.");
                request.getRequestDispatcher("/error.jsp").forward(request, response);
                return;
            }
            int quizId = Integer.parseInt(quizIdParam);

            // Fetch quiz questions
            List<Question> questions = QuizDAO.getQuestionsByQuizId(quizId);
            if (questions == null || questions.isEmpty()) {
                request.setAttribute("errorMessage", "Aucune question trouvée pour ce quiz.");
                request.getRequestDispatcher("/error.jsp").forward(request, response);
                return;
            }

            // Calculate score and collect detailed results
            int score = 0;
            List<String> results = new ArrayList<>();

            for (Question question : questions) {
                String userAnswer = request.getParameter("question_" + question.getId());
                boolean isCorrect = userAnswer != null && userAnswer.equals(question.getCorrectAnswer());
                if (isCorrect) {
                    score++;
                }
                results.add("Question: " + question.getQuestionText() + " | Votre réponse: " + (userAnswer == null ? "Non répondu" : userAnswer)
                        + " | Réponse correcte: " + question.getCorrectAnswer() + " | " + (isCorrect ? "Correct" : "Incorrect"));
            }

            // Set attributes for the results page
            request.setAttribute("score", score);
            request.setAttribute("totalQuestions", questions.size());
            request.setAttribute("results", results);

            // Forward to the results JSP
            request.getRequestDispatcher("../Etudiant//quizResults.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "ID de quiz invalide.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Une erreur est survenue. Veuillez réessayer.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
