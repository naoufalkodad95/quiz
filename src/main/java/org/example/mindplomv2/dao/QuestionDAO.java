package org.example.mindplomv2.dao;

import org.example.mindplomv2.model.Question;
import org.example.mindplomv2.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionDAO {

    // Add a new question to the database
    public boolean addQuestion(Question question) {
        String sql = "INSERT INTO Questions (question_text, option_a, option_b, option_c, option_d, correct_answer, quiz_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Validate and set the parameters
            if (question == null || question.getQuestionText() == null || question.getOptionA() == null || 
                question.getOptionB() == null || question.getOptionC() == null || question.getOptionD() == null || 
                question.getCorrectAnswer() == null) {
                throw new SQLException("Invalid question data.");
            }
            stmt.setString(1, question.getQuestionText());
            stmt.setString(2, question.getOptionA());
            stmt.setString(3, question.getOptionB());
            stmt.setString(4, question.getOptionC());
            stmt.setString(5, question.getOptionD());
            stmt.setString(6, question.getCorrectAnswer());
            stmt.setInt(7, question.getQuizId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error while adding question: " + e.getMessage());
            return false;
        }
    }

    // Retrieve all questions for a specific quiz
    public List<Question> findByQuizId(int quizId) {
        List<Question> questions = new ArrayList<>();
        String sql = "SELECT * FROM Questions WHERE quiz_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, quizId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    questions.add(mapResultSetToQuestion(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error while fetching questions by quiz ID: " + e.getMessage());
        }
        return questions;
    }

    // Retrieve all questions from the database
    public List<Question> findAll() {
        List<Question> questions = new ArrayList<>();
        String sql = "SELECT * FROM Questions";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                questions.add(mapResultSetToQuestion(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error while fetching all questions: " + e.getMessage());
        }
        return questions;
    }

    // Update an existing question
    public boolean updateQuestion(Question question) {
        String sql = "UPDATE Questions SET question_text = ?, option_a = ?, option_b = ?, option_c = ?, option_d = ?, correct_answer = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (question == null || question.getQuestionText() == null || question.getOptionA() == null || 
                question.getOptionB() == null || question.getOptionC() == null || question.getOptionD() == null || 
                question.getCorrectAnswer() == null) {
                throw new SQLException("Invalid question data.");
            }

            stmt.setString(1, question.getQuestionText());
            stmt.setString(2, question.getOptionA());
            stmt.setString(3, question.getOptionB());
            stmt.setString(4, question.getOptionC());
            stmt.setString(5, question.getOptionD());
            stmt.setString(6, question.getCorrectAnswer());
            stmt.setInt(7, question.getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error while updating question: " + e.getMessage());
            return false;
        }
    }

    // Delete a question by its ID
    public boolean deleteQuestion(int questionId) {
        String sql = "DELETE FROM Questions WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, questionId);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error while deleting question: " + e.getMessage());
            return false;
        }
    }

    // Map a ResultSet row to a Question object
    private Question mapResultSetToQuestion(ResultSet rs) throws SQLException {
        Question question = new Question();
        question.setId(rs.getInt("id"));
        question.setQuestionText(rs.getString("question_text"));
        question.setOptionA(rs.getString("option_a"));
        question.setOptionB(rs.getString("option_b"));
        question.setOptionC(rs.getString("option_c"));
        question.setOptionD(rs.getString("option_d"));
        question.setCorrectAnswer(rs.getString("correct_answer"));
        question.setQuizId(rs.getInt("quiz_id"));
        return question;
    }
}
