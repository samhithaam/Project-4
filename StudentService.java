import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Scanner;

public class StudentService {
    String username;
    public StudentService(String username) {
        this.username=username;
    }

    public void takeQuiz(int quizTitle) throws IOException {
        quizToArray(quizTitle);
        System.out.println(quizTitle);
        Scanner sc = new Scanner(System.in);
        for (int i=0;i<15;i++) {
            Random random = new Random();
            int questionNo = random.nextInt(14);
            Question q = questions[questionNo];
            System.out.println(q.getQuestion());
            System.out.println(q.getChoices()[0]);
            System.out.println(q.getChoices()[1]);
            System.out.println(q.getChoices()[2]);
            System.out.println(q.getChoices()[3]);
            q.setAnswer(sc.next().charAt(0));
        }
        StudentQuiz sq=new StudentQuiz(username,new Quiz(quizTitle,questions),System.currentTimeMillis());
        submitQuiz (sq);
}
        public void submitQuiz(StudentQuiz sq) {
        // when we submit ..what shld we return .
           // a file or studentquiz object;
        }

        public Question[] quizToArray(int quizTitle) throws IOException {
            BufferedReader reader = new BufferedReader(new FileReader("Quiz" + quizTitle));
            Question[] questions = new Question[15];
            String line;
            while ((line = reader.readLine()) != null) {
                // assume mcq is 4 options?? and 15 questions
                Question question = new Question();
                question.setQuestion(line);
                String[] choices = new String[4];
                choices[0] = reader.readLine();
                choices[1] = reader.readLine();
                choices[2] = reader.readLine();
                choices[3] = reader.readLine();
                char correct = (reader.readLine()).charAt(0);
                question.setChoices(choices);
                question.setCorrect(correct);
            }

        }
        public int getRandomNo(int len) {
            Random random = new Random();
            int questionNo = random.nextInt(len-1);
        }

}

