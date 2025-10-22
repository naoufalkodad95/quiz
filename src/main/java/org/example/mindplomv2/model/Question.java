package org.example.mindplomv2.model;

public class Question {
    private int id;
    private String questionText;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String correctAnswer;
    private int quizId;

    // Constructor with all fields (except id, as it's often auto-generated)
    public Question(String questionText, String optionA, String optionB, 
                    String optionC, String optionD, String correctAnswer, int quizId) {
        this.questionText = questionText;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctAnswer = correctAnswer;
        this.quizId = quizId;
    }

    // Default constructor
    public Question() {}

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    // Validation to ensure correctAnswer matches one of the options
    public boolean isCorrectAnswerValid() {
        if (correctAnswer == null || correctAnswer.isEmpty()) {
            return false;
        }
        return optionA.equals(correctAnswer) || optionB.equals(correctAnswer) ||
               optionC.equals(correctAnswer) || optionD.equals(correctAnswer);
    }

    // Validation for question fields (ensure no empty fields)
    public boolean isValid() {
        return questionText != null && !questionText.isEmpty() &&
               optionA != null && !optionA.isEmpty() &&
               optionB != null && !optionB.isEmpty() &&
               optionC != null && !optionC.isEmpty() &&
               optionD != null && !optionD.isEmpty() &&
               correctAnswer != null && !correctAnswer.isEmpty();
    }

    // Override toString() for debugging or logging
    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", questionText='" + questionText + '\'' +
                ", optionA='" + optionA + '\'' +
                ", optionB='" + optionB + '\'' +
                ", optionC='" + optionC + '\'' +
                ", optionD='" + optionD + '\'' +
                ", correctAnswer='" + correctAnswer + '\'' +
                ", quizId=" + quizId +
                '}';
    }

    // Method to check if the question has all options and a valid correct answer
    public boolean hasValidOptions() {
        return optionA != null && optionB != null && optionC != null && optionD != null;
    }
}
