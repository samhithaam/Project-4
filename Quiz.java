public class Quiz {
    public int quizTitle;
    public Question[] questions;

    public Quiz(int quizTitle, Question[] questions) {
        this.quizTitle = quizTitle;
        this.questions = questions;
    }

    public int getQuizTitle() {
        return quizTitle;
    }

    public void setQuizTitle(int quizTitle) {
        this.quizTitle = quizTitle;
    }

    public Question[] getQuestions() {
        return questions;
    }

    public void setQuestions(Question[] questions) {
        this.questions = questions;
    }
}
