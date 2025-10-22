package org.example.mindplomv2.dao;

import org.example.mindplomv2.model.Score;
import org.example.mindplomv2.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ScoreDAO {
    private static final Logger logger = Logger.getLogger(ScoreDAO.class.getName());

    public static boolean saveUserScore(int userId, int quizId, int score) {
        String sql = "INSERT INTO Scores (user_id, quiz_id, score) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, quizId);
            ps.setInt(3, score);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error saving user score", e);
            return false;
        }
    }

    public static List<Score> getUserScores(int userId) {
        List<Score> scores = new ArrayList<>();
        String sql = "SELECT * FROM Scores WHERE user_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Score score = new Score();
                    score.setId(rs.getInt("id"));
                    score.setUserId();
                    score.setQuizId(rs.getInt("quiz_id"));
                    score.setScore(rs.getInt("score"));
                    score.setDateTaken(rs.getTimestamp("date_taken"));
                    scores.add(score);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error retrieving user scores", e);
        }
        return scores;
    }
}
