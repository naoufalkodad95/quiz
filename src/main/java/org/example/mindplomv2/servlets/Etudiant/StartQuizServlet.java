package org.example.mindplomv2.servlets.Etudiant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import jakarta.servlet.http.HttpSession;
import org.example.mindplomv2.dao.QuizDAO;
import org.example.mindplomv2.model.Quiz;
import org.example.mindplomv2.model.Question;

@WebServlet("/startQuiz")
public class StartQuizServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            // Get quiz ID from the request parameter
            String quizIdParam = request.getParameter("quizId");

            // Check if a quizId was provided
            if (quizIdParam != null && !quizIdParam.isEmpty()) {
                int quizId;
                try {
                    quizId = Integer.parseInt(quizIdParam); // Parse the quizId to integer
                } catch (NumberFormatException e) {
                    // Handle invalid format for quizId
                    handleError(request, response, "Invalid Quiz ID format.");
                    return;
                }

                // Fetch quiz name and related questions from the database using QuizDAO
                String quizName = QuizDAO.getQuizNameById(quizId);
                List<Question> questions = QuizDAO.getQuestionsByQuizId(quizId);

                // Log information for debugging
                System.out.println("Quiz ID: " + quizId);
                System.out.println("Quiz Name: " + quizName);
                System.out.println("Questions Retrieved: " + (questions != null ? questions.size() : 0));

                // Validate that quizName and questions are properly fetched
                if (quizName == null || quizName.isEmpty()) {
                    handleError(request, response, "No quiz found for the provided ID.");
                    return;
                }

                if (questions == null || questions.isEmpty()) {
                    handleError(request, response, "No questions available for this quiz.");
                    return;
                }

                // Set attributes for quiz name and questions to be used in the JSP
                request.setAttribute("quizName", quizName);
                request.setAttribute("questions", questions);

                // Forward the request to the start_quiz.jsp (to display questions)
                request.getRequestDispatcher("start_quiz.jsp").forward(request, response);
            } else {
                // If no quizId is provided, show a list of all quizzes
                List<Quiz> quizzes = QuizDAO.getAllQuizzes();

                if (quizzes == null || quizzes.isEmpty()) {
                    handleError(request, response, "No quizzes available.");
                    return;
                }

                // Set the list of quizzes as an attribute for the JSP
                request.setAttribute("quizzes", quizzes);

                // Forward to the page that displays all quizzes (quiz_list.jsp)
                request.getRequestDispatcher("quiz_list.jsp").forward(request, response);
            }

        } catch (Exception e) {
            // Log any exception for debugging
            e.printStackTrace();
            // Send an internal server error response with a message
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while fetching the quiz.");
        }
    }

    /**
     * Helper method to handle error messages and forward to an error page.
     * 
     * @param request The HTTP request object.
     * @param response The HTTP response object.
     * @param errorMessage The error message to display.
     * @throws ServletException If an error occurs during request processing.
     * @throws IOException If an input-output error occurs.
     */
    private void handleError(HttpServletRequest request, HttpServletResponse response, String errorMessage) throws ServletException, IOException {
        request.setAttribute("errorMessage", errorMessage);
        request.getRequestDispatcher("errorPage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // The POST method can be used for form submission or other operations if needed.
        // Currently, it is left empty as no post actions are defined in the provided code.
    }
}
