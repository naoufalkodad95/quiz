package org.example.mindplomv2.servlets.Admin.Question;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.mindplomv2.dao.QuestionDAO;
import org.example.mindplomv2.model.Question;

import java.io.IOException;

@WebServlet("/Admin/Question/AddQuestionServlet")
public class AddQuestionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AddQuestionServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve input parameters
        String questionText = request.getParameter("questionText");
        String optionA = request.getParameter("optionA");
        String optionB = request.getParameter("optionB");
        String optionC = request.getParameter("optionC");
        String optionD = request.getParameter("optionD");
        String correctAnswer = request.getParameter("correctAnswer");
        String quizIdStr = request.getParameter("quizId");

        // Validate inputs
        if (isInputInvalid(questionText, optionA, optionB, optionC, optionD, correctAnswer, quizIdStr)) {
            sendResponse(response, "Tous les champs sont obligatoires.", "error");
            return;
        }

        // Parse and validate quiz ID
        int quizId = parseQuizId(quizIdStr, response);
        if (quizId == -1) return; // Invalid quiz ID handled in parseQuizId

        // Create a Question object
        Question question = createQuestion(questionText, optionA, optionB, optionC, optionD, correctAnswer, quizId);

        // Attempt to add the question to the database
        handleQuestionAddition(response, question);
    }

    /**
     * Validates the input parameters.
     */
    private boolean isInputInvalid(String questionText, String optionA, String optionB, String optionC,
                                   String optionD, String correctAnswer, String quizIdStr) {
        return isNullOrEmpty(questionText) || isNullOrEmpty(optionA) || isNullOrEmpty(optionB) ||
               isNullOrEmpty(optionC) || isNullOrEmpty(optionD) || isNullOrEmpty(correctAnswer) ||
               isNullOrEmpty(quizIdStr);
    }

    /**
     * Checks if a string is null or empty.
     */
    private boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    /**
     * Parses and validates the quiz ID.
     */
    private int parseQuizId(String quizIdStr, HttpServletResponse response) throws IOException {
        try {
            return Integer.parseInt(quizIdStr);
        } catch (NumberFormatException e) {
            sendResponse(response, "L'ID du quiz est invalide.", "error");
            return -1;
        }
    }

    /**
     * Creates a new Question object.
     */
    private Question createQuestion(String questionText, String optionA, String optionB, String optionC,
                                    String optionD, String correctAnswer, int quizId) {
        Question question = new Question();
        question.setQuestionText(questionText);
        question.setOptionA(optionA);
        question.setOptionB(optionB);
        question.setOptionC(optionC);
        question.setOptionD(optionD);
        question.setCorrectAnswer(correctAnswer);
        question.setQuizId(quizId);
        return question;
    }

    /**
     * Handles the addition of the question to the database.
     */
    private void handleQuestionAddition(HttpServletResponse response, Question question) throws IOException {
        QuestionDAO questionDAO = new QuestionDAO();
        boolean success = questionDAO.addQuestion(question);

        String message = success ? "Question ajoutée avec succès !" : "Erreur lors de l'ajout de la question.";
        String messageType = success ? "success" : "error";

        sendResponse(response, message, messageType);
    }

    /**
     * Sends a JSON response with the message and message type.
     */
    private void sendResponse(HttpServletResponse response, String message, String messageType) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String jsonResponse = String.format("{\"message\":\"%s\", \"messageType\":\"%s\"}", message, messageType);
        response.getWriter().write(jsonResponse);
    }
}
