import java.io.*;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * StudentService.java
 *
 * @author Serena Gauri Ronanki
 * @version 11/14/2021
 */

public class StudentService {
    private static ArrayList<String> studentQuiz = new ArrayList<>();
    private static String username;

    public StudentService(String username) {
        this.username = username;
    }

    public static void main(String[] args) {
        username = args[0];
        if (username != null) {
            System.out.println("Welcome " + username);
        } else {
            System.out.println("Invalid user");//if a username does not exist it quits the class
            System.exit(0);
        }
        Scanner sc = new Scanner(System.in);
        boolean quizTaken = false;
        int choice;
        boolean runTest = true;
        do {
            System.out.println("Choose one of the options below");
            System.out.println("1) Take a quiz\n2) View Grade");
            choice = sc.nextInt();

            ArrayList<Quiz> quizzes = Quiz.getQuizzes();
            if (quizzes == null) {
                System.out.println("No quizzes available.Come back later!"); //if no quiz files are created by teacher
                runTest = false;
            } else {
                // to take quiz
                if (choice == 1) {
                    printQuizTitles(); //print quiz choices
                    System.out.println("Enter the quiz title"); //quiz title to take quiz from listed choices
                    String quizName = sc.nextLine(); //reads quiz name,
                    try {
                        StudentService.takeQuiz(quizName);// student takes the quiz
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    quizTaken = true; //if student takes the test
                    //to view grade
                } else if (choice == 2) {
                    printQuizTitles();
                    System.out.println("Enter quiz title: "); //quiz title of graded quiz
                    String quizName = sc.nextLine();

                    try {
                        StudentService.viewGradedQuiz(quizName);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else {
                    System.out.println("Invalid Input!");
                }
                System.out.println("Would you like to try again? Y/N");
                if (!sc.next().equalsIgnoreCase("y")) {
                    runTest = false;
                    break;

                }
            }
        }
        while (runTest) ;
    }


    public static ArrayList<String> getStudentSubmissions() {
        return studentQuiz;
    }

    //accessed if user wants to takes quiz
    public static void takeQuiz(String quizTitle) throws IOException {
        int i = 0;
        boolean found = false;
        ArrayList<Quiz> quizzes = Quiz.getQuizzes();
        while (quizzes != null && i < quizzes.size() && !found) {
            if (quizTitle.equalsIgnoreCase(quizzes.get(i).getQuizName())) {
                found = true;
            }
            i++;
        }
        Scanner sc = new Scanner(System.in);
        if (found) {
            System.out.println(quizTitle);
            if (quizzes.get(i).isRandomized()) {
                printRandomizedQuiz(quizzes.get(i), sc);
            } else {
                printQuiz(quizzes.get(i), sc);
            }
        } else {
            System.out.println("Invalid Quiz Title");
        }

        submitQuiz(quizTitle);

        System.out.println("Would you like to take another quiz? Y/N");
        if (sc.next().equalsIgnoreCase("y")) {
            System.out.println("Enter quiz title");
            takeQuiz(sc.next());
        }
    }
    // accesses this method when student takes quiz and submits response
    public static void submitQuiz(String quizTitle) throws IOException {
        String fileName = quizTitle + "_" + username;
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File(fileName)));

        writer.write(System.currentTimeMillis() + "\n");

        for (int i = 0; i < studentQuiz.size(); i = +3) {
            writer.write(studentQuiz.get(i) + "," + studentQuiz.get(i + 1) + "," + studentQuiz.get(i + 2) + "\n");
        }

    }
    // generates randomization
    public int getRandomNo(int len) {
        Random random = new Random();
        int questionNo = random.nextInt(len - 1);
        return questionNo;
    }
    //accesses this method if quiz is randomized
    public static void printRandomizedQuiz(Quiz q, Scanner sc) {
        int[] repeat = new int[q.getQuestions().size()];
        for (int i = 0; i < q.getQuestions().size(); i++) {
            Random random = new Random();
            int questionNo = random.nextInt(q.getQuestions().size() - 1);
            while (repeat[questionNo] == 1) {
                questionNo = random.nextInt(q.getQuestions().size() - 1);
            }
            studentQuiz.add(q.getQuestions().get(questionNo));
            studentQuiz.add(q.getCorrectAnswers().get(questionNo));
            System.out.println(q.getQuestions().get(questionNo));
            int pos = questionNo * 4;
            int[] randomNumsA = generateRandomNums();
            System.out.println(q.getAnswerChoices().get(pos + randomNumsA[0]));
            System.out.println(q.getAnswerChoices().get(pos + randomNumsA[1]));
            System.out.println(q.getAnswerChoices().get(pos + randomNumsA[2]));
            System.out.println(q.getAnswerChoices().get(pos + randomNumsA[3]));
            System.out.print("Enter your answer choice: ");
            studentQuiz.add(sc.nextLine());
            repeat[questionNo] = 1;

        }
    }
    // accesses this method if quiz is not randomized
    public static void printQuiz(Quiz q, Scanner sc) {
        int pos = 0;
        for (int i = 0; i < q.getQuestions().size(); i++) {
            studentQuiz.add(q.getQuestions().get(i));
            studentQuiz.add(q.getCorrectAnswers().get(i));
            System.out.println(q.getQuestions().get(i)); // print question
            System.out.println(q.getAnswerChoices().get(pos));
            System.out.println(q.getAnswerChoices().get(pos + 1));
            System.out.println(q.getAnswerChoices().get(pos + 2));
            System.out.println(q.getAnswerChoices().get(pos + 3));
            pos += 4;
            System.out.print("Enter your answer choice: ");
            studentQuiz.add(sc.nextLine());
        }
    }

    public static int[] generateRandomNums() {
        int i = 0;
        int[] randomArr = new int[4];
        int[] repeat = new int[4];
        Random random = new Random();
        while (i < 3) {
            int num = random.nextInt(3);
            while (repeat[num] == 1) {

                num = random.nextInt(3);
            }
            randomArr[i] = num;
            i++;
        }

        return randomArr;
    }
    // view graded quiz after teacher grades it
    public static void viewGradedQuiz(String quizTitle) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(quizTitle
                + "_" + username));
        int score = 0;
        String line = null;
        reader.readLine();
        while ((line = reader.readLine()) != null) {
            String[] tokens = line.split(",");
            System.out.println("Question: " + tokens[0]);
            System.out.println("Correct Answer: " + tokens[1]);
            System.out.println("Your Answer: " + tokens[2]);
            if (tokens[1].equalsIgnoreCase(tokens[2])) {
                score++;
            }
        }
        System.out.println("Your final score is: " + score + ".");
    }

    public static ArrayList<String> readFile(String quizTitle) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(quizTitle
                + "_" + username));
        ArrayList<String> fileContents = new ArrayList<>();
        String line = null;

        while ((line = reader.readLine()) != null) {
            String[] tokens = line.split(",");
            fileContents.add("Question: " + tokens[0]);
            fileContents.add("Correct Answer: " + tokens[1]);
            fileContents.add("Your Answer: " + tokens[2]);
        }
        return fileContents;
    }
    // displays all the available quiz titles
    public static void printQuizTitles() {
        if (Quiz.getQuizzes() != null) {
            for (Quiz quiz : Quiz.getQuizzes()) {
                System.out.println(quiz.getQuizName());
            }

        }
    }
}