import java.util.ArrayList;
import java.util.Scanner;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Student {
    
    private static final SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy HH:mm");
    // [question #1, answer to Q1, student answer, question #2, answer to Q2, ...]
    // updated in printQuiz, accessed in viewGradedQuiz()
    private static ArrayList<String> studentSubmissions = new ArrayList<>();

    public static void main (String [] args) {
        Scanner scanner = new Scanner(System.in);
        boolean takenQuiz = false;
        int choice;
        do {
            System.out.println("Would you like to (1) take a quiz, or (2) view your graded quiz/quizzes?");
            choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 1) {
                takeQuiz(scanner);
                takenQuiz = true;
            } else if (choice == 2) {
                if (takenQuiz) { // if student has already taken a quiz
                    viewGradedQuiz();
                } else {
                    System.out.println("Invalid input!");
                }
            } else {
                System.out.println("Invalid input!");
            }
            System.out.println("Would you like to (1) exit, or (2) try again?");
            choice = scanner.nextInt();
            scanner.nextLine();
        } while (choice != 1);
    }

    public static void takeQuiz(Scanner scanner) {
        boolean validInput = false;
        boolean takeQuizAgain;
        do {
            System.out.println("What quiz would you like to take?");
            for (Quiz quiz : Quiz.getQuizzes()) {
                System.out.println(quiz.getQuizName());
                // print all quiz names to allow student to choose the quiz they want to take
            }
            String quizChoice = scanner.nextLine();
            for (Quiz quiz : Quiz.getQuizzes()) {
                if (quizChoice.equalsIgnoreCase(quiz.getQuizName())) {
                    validInput = true;
                    System.out.println(quizChoice); // print quiz title
                    printQuiz(quiz.isRandomized(), quizChoice, scanner);
                    // referenced https://mkyong.com/java/how-to-get-current-timestamps-in-java/
                    System.out.println(sdf.format(new Timestamp(System.currentTimeMillis())));
                } else {
                    System.out.println("Invalid input!");
                }
            }
            System.out.println("Would you like to take another quiz? Type 'y' for yes, 'n' for no.");
            String input = scanner.nextLine();
            takeQuizAgain = input.equalsIgnoreCase("y");
        } while (!validInput || takeQuizAgain);
    }

    // Students can view their graded quizzes, the points for each individual question, and their total score
    public static void viewGradedQuiz() {
        for (int i = 0; i < studentSubmissions.size(); i += 3) {
            System.out.println("Question: " + studentSubmissions.get(i));
            System.out.println("Correct Answer: " + studentSubmissions.get(i + 1));
            System.out.println("Your Answer: " + studentSubmissions.get(i + 2));
            // System.out.println("Points Earned: " + Teacher.getPointValues().get(i));
            // assuming Teacher has method getPointValues()
        }
        // System.out.println("Total Points: " + Teacher.getTotalPoints());
        // assuming Teacher has method getTotalPoints()
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

    private static void printQuiz(boolean randomized, String quizTitle, Scanner scanner) {
        if (!randomized) {
            // Quiz questions will appear on the same page in the order they are added to the quiz
            for (Quiz quiz : Quiz.getQuizzes()) {
                if (quiz.getQuizName().equalsIgnoreCase(quizTitle)) {
                    int pos = 0;
                    for (int i = 0; i < quiz.getQuestions().size(); i++) {
                        studentSubmissions.add(quiz.getQuestions().get(i));
                        studentSubmissions.add(quiz.getCorrectAnswers().get(i));
                        // getCorrectAnswers().size() == getQuestions().size()
                        System.out.println(quiz.getQuestions().get(i)); // print question
                        System.out.println(quiz.getAnswerChoices().get(pos));
                        System.out.println(quiz.getAnswerChoices().get(pos + 1));
                        System.out.println(quiz.getAnswerChoices().get(pos + 2));
                        System.out.println(quiz.getAnswerChoices().get(pos + 3));
                        pos += 4;
                        System.out.print("Enter your answer choice: ");
                        studentSubmissions.add(scanner.nextLine());
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
                    for (int i = 0; i < randomNumsQ.size(); i++) {
                        index = randomNumsQ.get(i);
                        studentSubmissions.add(quiz.getQuestions().get(index));
                        studentSubmissions.add(quiz.getCorrectAnswers().get(index));
                        System.out.println(quiz.getQuestions().get(index));
                        // randomize order of answer choices when printed to terminal
                        pos = index * 4;
                        // for each question, a list of random numbers is required
                        ArrayList<Integer> randomNumsA = generateRandomNums(1, 4, 3);
                        System.out.println(quiz.getAnswerChoices().get(pos));
                        System.out.println(quiz.getAnswerChoices().get(pos + randomNumsA.get(0)));
                        System.out.println(quiz.getAnswerChoices().get(pos + randomNumsA.get(1)));
                        System.out.println(quiz.getAnswerChoices().get(pos + randomNumsA.get(2)));
                        System.out.print("Enter your answer choice: ");
                        studentSubmissions.add(scanner.nextLine());
                    }
                }
            }
        }
    }
}
