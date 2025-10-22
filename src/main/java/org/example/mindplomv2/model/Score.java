package org.example.mindplomv2.model;

import java.sql.Timestamp;

public class Score {
    private int id;
    private int userId;
    private int quizId;
    private int score;
    private Timestamp dateTaken;

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId() { this.userId = userId; }

    public int getQuizId() { return quizId; }
    public void setQuizId(int quizId) { this.quizId = quizId; }

    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }

    public Timestamp getDateTaken() { return dateTaken; }
    public void setDateTaken(Timestamp dateTaken) { this.dateTaken = dateTaken; }


}
