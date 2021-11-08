public class StudentQuiz {
    private String username;
    private Quiz quiz;
    private long timeStamp;

    public StudentQuiz(String username, Quiz quiz, long timeStamp) {
        this.username = username;
        this.quiz = quiz;
        this.timeStamp = timeStamp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
