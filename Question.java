public class Question {
    public String question;
    public String[] choices;
    public char correct;
    public char answer;

    public Question(String question,String[]choices,char correct,char answer){
        this.question = question;
        this.choices=choices;
        this.correct=correct;
        this.answer=answer;
    }

    public Question() {
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String[] getChoices() {
        return choices;
    }

    public void setChoices(String[] choices) {
        this.choices = choices;
    }

    public char getCorrect() {
        return correct;
    }

    public void setCorrect(char correct) {
        this.correct = correct;
    }

    public char getAnswer() {
        return answer;
    }

    public void setAnswer(char answer) {
        this.answer = answer;
    }
}




