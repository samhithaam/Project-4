import java.io.*;
import java.util.Random;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class StudentService {
    String username;

    public StudentService(String username) {
        this.username = username;
    }
//quiz title is string
    public void takeQuiz(int quizTitle) throws IOException {
        Question[] questions = quizToArray(quizTitle);
        System.out.println(quizTitle);
        int[] repeat = new int[questions.length];
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < questions.length; i++) {
            Random random = new Random();
            int questionNo = random.nextInt(questions.length - 1);
            while (repeat[questionNo] == 1) {
                questionNo = random.nextInt(questions.length - 1);
            }
            Question q = questions[questionNo];
            System.out.println(q.getQuestion());
            System.out.println(q.getChoices()[0]);
            System.out.println(q.getChoices()[1]);
            System.out.println(q.getChoices()[2]);
            System.out.println(q.getChoices()[3]);
            q.setAnswer(sc.next().charAt(0));
            repeat[questionNo] = 1;

        }
        StudentQuiz sq = new StudentQuiz(username, new Quiz(quizTitle, questions), System.currentTimeMillis());
        submitQuiz(sq);
        System.out.println("Would you like to take another quiz?");
        if (sc.next().charAt(0) == 'Y') {
            System.out.println("Enter quiz title");
            takeQuiz(sc.nextInt());
        }

    }

    public void submitQuiz(StudentQuiz sq) throws IOException {
        String fileName = sq.getQuiz().getQuizTitle() + "_" + username + "_" + System.currentTimeMillis();
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File(fileName)));
        Question[] questions = sq.getQuiz().getQuestions();
        for (Question q : questions) {
            writer.write(q.getQuestion() + "," + q.getCorrect() + "," + q.getAnswer());
        }

    }

    public Question[] quizToArray(int quizTitle) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("Quiz" + quizTitle));
        List<Question> questions = new ArrayList<Question>();
        String line;
        while ((line = reader.readLine()) != null) {
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
            questions.add(question);
        }
        Question[] result = questions.toArray(new Question[questions.size()]);
        return result;


    }

    public int getRandomNo(int len) {
        Random random = new Random();
        int questionNo = random.nextInt(len - 1);
        return questionNo;
    }

}

