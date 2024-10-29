package models;

public class Activity {
    private int activityID;
    private String question;
    private String correctAnswer;
    private String incorrectAnswer1;
    private String incorrectAnswer2;
    private String incorrectAnswer3;
    private String explanationCorrect;
    private String explanationIncorrect1;
    private String explanationIncorrect2;
    private String explanationIncorrect3;
    private int contentBlockID;

    // Default Constructor
    public Activity() {
    }

    // Parameterized Constructor
    public Activity(int activityID, String question, String correctAnswer, String incorrectAnswer1, String incorrectAnswer2,
                    String incorrectAnswer3, String explanationCorrect, String explanationIncorrect1,
                    String explanationIncorrect2, String explanationIncorrect3, int contentBlockID) {
        this.activityID = activityID;
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.incorrectAnswer1 = incorrectAnswer1;
        this.incorrectAnswer2 = incorrectAnswer2;
        this.incorrectAnswer3 = incorrectAnswer3;
        this.explanationCorrect = explanationCorrect;
        this.explanationIncorrect1 = explanationIncorrect1;
        this.explanationIncorrect2 = explanationIncorrect2;
        this.explanationIncorrect3 = explanationIncorrect3;
        this.contentBlockID = contentBlockID;
    }

    // Getters and Setters
    public int getActivityID() {
        return activityID;
    }

    public void setActivityID(int activityID) {
        this.activityID = activityID;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getIncorrectAnswer1() {
        return incorrectAnswer1;
    }

    public void setIncorrectAnswer1(String incorrectAnswer1) {
        this.incorrectAnswer1 = incorrectAnswer1;
    }

    public String getIncorrectAnswer2() {
        return incorrectAnswer2;
    }

    public void setIncorrectAnswer2(String incorrectAnswer2) {
        this.incorrectAnswer2 = incorrectAnswer2;
    }

    public String getIncorrectAnswer3() {
        return incorrectAnswer3;
    }

    public void setIncorrectAnswer3(String incorrectAnswer3) {
        this.incorrectAnswer3 = incorrectAnswer3;
    }

    public String getExplanationCorrect() {
        return explanationCorrect;
    }

    public void setExplanationCorrect(String explanationCorrect) {
        this.explanationCorrect = explanationCorrect;
    }

    public String getExplanationIncorrect1() {
        return explanationIncorrect1;
    }

    public void setExplanationIncorrect1(String explanationIncorrect1) {
        this.explanationIncorrect1 = explanationIncorrect1;
    }

    public String getExplanationIncorrect2() {
        return explanationIncorrect2;
    }

    public void setExplanationIncorrect2(String explanationIncorrect2) {
        this.explanationIncorrect2 = explanationIncorrect2;
    }

    public String getExplanationIncorrect3() {
        return explanationIncorrect3;
    }

    public void setExplanationIncorrect3(String explanationIncorrect3) {
        this.explanationIncorrect3 = explanationIncorrect3;
    }

    public int getContentBlockID() {
        return contentBlockID;
    }

    public void setContentBlockID(int contentBlockID) {
        this.contentBlockID = contentBlockID;
    }
}
