package org.example.mindplomv2.dao;

import org.example.mindplomv2.model.Question;
import org.example.mindplomv2.model.Quiz;
import org.example.mindplomv2.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QuizDAO {

    private static final Logger logger = Logger.getLogger(QuizDAO.class.getName());

    // Fetch the quiz name by ID
    public static String getQuizNameById(int quizId) {
        String sql = "SELECT nom_quiz FROM Quiz WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, quizId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("nom_quiz");
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching quiz name by ID: " + quizId, e);
        }
        return null;
    }

    // Fetch all questions for a given quiz ID
    public static List<Question> getQuestionsByQuizId(int quizId) {
        List<Question> questions = new ArrayList<>();
        String sql = "SELECT * FROM Questions WHERE id_quiz = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, quizId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Question question = new Question();
                    question.setId(rs.getInt("id"));
                    question.setQuestionText(rs.getString("question"));
                    question.setOptionA(rs.getString("option_a"));
                    question.setOptionB(rs.getString("option_b"));
                    question.setOptionC(rs.getString("option_c"));
                    question.setOptionD(rs.getString("option_d"));
                    question.setCorrectAnswer(rs.getString("option_correcte"));
                    questions.add(question);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching questions for quiz ID: " + quizId, e);
        }
        return questions;
    }


        public static boolean addQuiz(Quiz quiz) {
            String sql = "INSERT INTO Quiz (nom_quiz, id_cours, niveau) VALUES (?, ?, ?)";
            try (Connection conn = DBConnection.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {

                // Préparation des paramètres
                ps.setString(1, quiz.getName());
                ps.setInt(2, quiz.getCourseId());
                ps.setString(3, quiz.getLevel());

                // Exécuter l'insertion
                return ps.executeUpdate() > 0;

            } catch (SQLException e) {
                logger.log(Level.SEVERE, "Erreur lors de l'ajout du quiz : " + quiz.getName(), e);
            }
            return false;
        }


    // Read all quizzes
    public static List<Quiz> getAllQuizzes() {
        List<Quiz> quizzes = new ArrayList<>();
        String sql = "SELECT * FROM Quiz";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Quiz quiz = new Quiz();
                quiz.setId(rs.getInt("id"));
                quiz.setName(rs.getString("nom_quiz"));
                quiz.setCourseId(rs.getInt("id_cours"));
                quiz.setLevel(rs.getString("niveau"));
                quizzes.add(quiz);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching all quizzes", e);
        }
        return quizzes;
    }

    // Update an existing quiz
    public static boolean updateQuiz(Quiz quiz) {
        String sql = "UPDATE Quiz SET nom_quiz = ?, niveau = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, quiz.getName());
            ps.setString(2, quiz.getLevel());
            ps.setInt(3, quiz.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error updating quiz with ID: " + quiz.getId(), e);
        }
        return false;
    }



    // Delete a quiz by ID
    public static boolean deleteQuiz(int quizId) {
        String sql = "DELETE FROM Quiz WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, quizId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error deleting quiz with ID: " + quizId, e);
        }
        return false;
    }

    // Fetch a quiz by ID
    public static Quiz getQuizById(int quizId) {
        String sql = "SELECT id, nom_quiz, niveau FROM Quiz WHERE id = ?";
        Quiz quiz = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, quizId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                quiz = new Quiz();
                quiz.setId(rs.getInt("id"));
                quiz.setName(rs.getString("nom_quiz"));
                quiz.setLevel(rs.getString("niveau"));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching quiz with ID: " + quizId, e);
        }

        return quiz;
    }


    // Fetch the course name by ID
    public static String getCourseById(int courseId) {
        String sql = "SELECT nom_cours FROM Cours WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, courseId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("nom_cours");
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching course by ID: " + courseId, e);
        }
        return null;
    }

    // Helper method to close resources
    private static void closeResources(AutoCloseable... resources) {
        for (AutoCloseable resource : resources) {
            try {
                if (resource != null) {
                    resource.close();
                }
            } catch (Exception e) {
                logger.log(Level.WARNING, "Error closing resource", e);
            }
        }
    }
}
