import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Teacher.java
 * Student.java
 * @author Ritu Atreyas and Serena Gauri Ronanki
 * @version 11/14/2021
 */
public class Student {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy HH:mm");
    // [question #1, answer to Q1, student answer, question #2, answer to Q2, ...]
    // updated in printQuiz, accessed in viewGradedQuiz()
    private static ArrayList<String> studentSubmissions = readFile("src/StudentSubmissions.txt");
    public static void main (String [] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        do {
            boolean notNumber = true;
            while (notNumber) {
                try {
                    System.out.println("Would you like to (1) take a quiz, or (2) view your graded quiz/quizzes?");
                    choice = Integer.parseInt(scanner.nextLine());
                    notNumber = false;
                } catch (NumberFormatException e) {
                    System.out.println("You didn't enter a number! Try again.");
                }
            }
            if (choice == 1) {
                takeQuiz(scanner);
            } else if (choice == 2) {
                viewGradedQuiz();
            } else {
                System.out.println("Invalid input!");
            }
            notNumber = true;
            while (notNumber) {
                try {
                    System.out.println("Would you like to (1) exit, or (2) try again?");
                    choice = Integer.parseInt(scanner.nextLine());
                    notNumber = false;
                } catch (NumberFormatException e) {
                    System.out.println("You didn't enter a number! Try again.");
                }
            }
        } while (choice != 1);
    }
    public static void takeQuiz(Scanner scanner) throws FileNotFoundException {
        boolean validInput = false;
        boolean takeQuizAgain;
        if (Quiz.getQuizzes() == null || Quiz.getQuizzes().size() == 0) {
            System.out.println("There are currently no quizzes for you to take!");
            return;
        }
        do {
            System.out.println("What quiz would you like to take?");
            for (Quiz quiz : Quiz.getQuizzes()) {
                System.out.println(quiz.getQuizName());
                // print all quiz names so student can choose the quiz they want to take
            }
            String quizChoice = scanner.nextLine();
            for (Quiz quiz : Quiz.getQuizzes()) {
                if (quizChoice.equalsIgnoreCase(quiz.getQuizName())) {
                    validInput = true;
                    printQuiz(quiz.isRandomized(), quizChoice, scanner);
                    // referenced https://mkyong.com/java/how-to-get-current-timestamps-in-java/
                    System.out.println(sdf.format(new Timestamp(System.currentTimeMillis())));
                }
            } if (!validInput) {
                System.out.println("Invalid input");
            }
            System.out.println("Would you like to take another quiz? Type 'y' for yes, 'n' for no.");
            String input = scanner.nextLine();
            takeQuizAgain = input.equalsIgnoreCase("y");
        } while (!validInput || takeQuizAgain);
    }
    // Students can view their graded quizzes, the points for each individual question, and their total score
    public static void viewGradedQuiz() {
        /*
        if (studentSubmissions == null || studentSubmissions.size() == 0) {
            System.out.println("There aren't any submissions for you to view!");
            return;
        }
        if (Teacher.getPointValues().size() == 0) {
            System.out.println("Your teacher hasn't graded your quiz(zes) yet!");
            return;
        }
        for (int i = 0; i < studentSubmissions.size(); i += 3) {
            System.out.println("\nQuestion: " + studentSubmissions.get(i));
            System.out.println("Correct Answer: " + studentSubmissions.get(i + 1));
            System.out.println("Your Answer: " + studentSubmissions.get(i + 2));
            System.out.println("Points Earned: " + Teacher.getPointValues().get(i / 3));
        }
        System.out.println("\nTotal Points: " + Teacher.getTotalPoints());
         */
        // read from file instead of accessing variable directly
        ArrayList<String> studentSubmissionsLocal= Student.readFile("src/StudentSubmissions.txt");
        if (studentSubmissionsLocal == null || studentSubmissionsLocal.size() == 0) {
            System.out.println("There aren't any submissions yet!");
            return;
        }
        if (Teacher.getPointValues().size() == 0) {
            System.out.println("Your teacher hasn't graded your quiz(zes) yet!");
            return;
        }
        for (int i = 0; i < studentSubmissionsLocal.size(); i += 3) {
            System.out.println("\nQuestion: " + studentSubmissionsLocal.get(i));
            System.out.println("Correct Answer: " + studentSubmissionsLocal.get(i + 1));
            System.out.println("Your Answer: " + studentSubmissionsLocal.get(i + 2));
            System.out.println("Points Earned: " + Teacher.getPointValues().get(i / 3));
        }
        System.out.println("\nTotal Points: " + Teacher.getTotalPoints());
    }
    public static ArrayList<Integer> generateRandomNums(int start, int end, int length) {
        ArrayList<Integer> list = new ArrayList<>();
        int num = 0;
        while (list.size() != length) {
            num = (int)(Math.random() * end + start);
            if (list.size() == 0) {
                list.add(num);
            } else {
                if (!list.contains(num)) {
                    list.add(num);
                }
            }
        }
        return list;
    }
    private static void printQuiz(boolean randomized, String quizTitle, Scanner scanner) throws FileNotFoundException {
        ArrayList<String> studentSubmissionsLocal = readFile("src/StudentSubmissions.txt");
        if (studentSubmissionsLocal == null) {
            studentSubmissionsLocal = new ArrayList<>();
        }
        if (!randomized) {
            // Quiz questions will appear on the same page in the order they are added to the quiz
            for (Quiz quiz : Quiz.getQuizzes()) {
                if (quiz.getQuizName().equalsIgnoreCase(quizTitle)) {
                    int pos = 0;
                    for (int i = 0; i < quiz.getQuestions().size(); i++) {
                        studentSubmissionsLocal.add(quiz.getQuestions().get(i));
                        studentSubmissionsLocal.add(quiz.getCorrectAnswers().get(i));
                        // getCorrectAnswers().size() == getQuestions().size()
                        System.out.println(quiz.getQuestions().get(i)); // print question
                        System.out.println(quiz.getAnswerChoices().get(pos));
                        System.out.println(quiz.getAnswerChoices().get(pos + 1));
                        System.out.println(quiz.getAnswerChoices().get(pos + 2));
                        System.out.println(quiz.getAnswerChoices().get(pos + 3));
                        pos += 4;
                        System.out.print("Enter your answer choice: ");
                        studentSubmissionsLocal.add(scanner.nextLine());
                    }
                }
            }
        } else {
            // Teachers can choose to randomize the order of questions and the order of potential options for a question
            for (Quiz quiz : Quiz.getQuizzes()) {
                if (quiz.getQuizName().equalsIgnoreCase(quizTitle)) {
                    int pos = 0;
                    int index = 0;
                    ArrayList<Integer> randomNumsQ = generateRandomNums(0, quiz.getQuestions().size(), quiz.getQuestions().size());
                    // for each quiz, a list of random numbers is required
                    // randomNumsQ.size() == quiz.getQuestions().size()
                    for (Integer integer : randomNumsQ) {
                        index = integer;
                        studentSubmissionsLocal.add(quiz.getQuestions().get(index));
                        studentSubmissionsLocal.add(quiz.getCorrectAnswers().get(index));
                        System.out.println(quiz.getQuestions().get(index));
                        // randomize order of answer choices when printed to terminal
                        pos = index * 4;
                        // for each question, a list of random numbers is required
                        ArrayList<Integer> randomNumsA = generateRandomNums(1, 3, 3);
                        System.out.println(quiz.getAnswerChoices().get(pos));
                        System.out.println(quiz.getAnswerChoices().get(pos + randomNumsA.get(0)));
                        System.out.println(quiz.getAnswerChoices().get(pos + randomNumsA.get(1)));
                        System.out.println(quiz.getAnswerChoices().get(pos + randomNumsA.get(2)));
                        System.out.print("Enter your answer choice: ");
                        studentSubmissionsLocal.add(scanner.nextLine());
                    }
                }
            }
        }
        PrintWriter pw = new PrintWriter("src/StudentSubmissions.txt");
        for (String studentSubmission : studentSubmissionsLocal) {
            pw.println(studentSubmission);
        }
        pw.close();
    }
    public static ArrayList<String> readFile(String fileName) {
        try (BufferedReader bfr = new BufferedReader(new FileReader(fileName))) {
            ArrayList<String> fileContents = new ArrayList<>();
            String line = new String("");
            while ((line = bfr.readLine()) != null) {
                fileContents.add(line);
            }
            if (fileContents.size() == 0) {
                return null;
            } else {
                return fileContents;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static ArrayList<String> getStudentSubmissions() {
        return studentSubmissions;
    }
}