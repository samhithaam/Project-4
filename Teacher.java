import java.util.Scanner;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.IOException;

public class Teacher {

    private static ArrayList<String> teacherSubmissions = new ArrayList<>();
    private static ArrayList<Integer> pointValues = new ArrayList<>();
    private static int totalPoints;

    // Calculates and returns the total points earned by the student on all the quizzes
    public static int getTotalPoints() {
        for (int pointValue : pointValues) {
            totalPoints += pointValue;
        }
        return totalPoints;
    }

    public static ArrayList<String> getTeacherSubmissions() {
        return teacherSubmissions;
    }

    public static ArrayList<Integer> getPointValues() {
        return pointValues;
    }

    // Teachers can create new quizzes with a title, choice to randomize questions, 4 answer choices, and the correct
    // answer. The created quizzes are added to the teacherSubmissions Arraylist
    public static void createQuiz() {
        boolean anotherQ = true;
        // Accounts for if teacher wants to input more than one question, main method accounts for
        // if teacher wants to create more than one quiz
        boolean validInput = false;
        while (anotherQ) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter quiz title:");
            teacherSubmissions.add(scan.nextLine());
            while(!validInput) {
                System.out.println("Randomize questions? Enter 'y' for yes, 'n' for no.");
                String randomize = scan.nextLine();
                if (!(randomize.equalsIgnoreCase("y")) &&
                        !(randomize.equalsIgnoreCase("n"))) {
                    System.out.println("Invalid input! Enter 'y' or 'n'.");
                } else {
                    teacherSubmissions.add(randomize);
                    validInput = true;
                }
            }
            validInput = false;
            System.out.println("Enter question:");
            String question = scan.nextLine();
            teacherSubmissions.add(question);
            System.out.println("Enter answer choice 1:");
            String a1 = scan.nextLine();
            teacherSubmissions.add(a1);
            System.out.println("Enter answer choice 2:");
            String a2 = scan.nextLine();
            teacherSubmissions.add(a2);
            System.out.println("Enter answer choice 3:");
            String a3 = scan.nextLine();
            teacherSubmissions.add(a3);
            System.out.println("Enter answer choice 4:");
            String a4 = scan.nextLine();
            teacherSubmissions.add(a4);
            while(!validInput) {
                System.out.println("Enter correct answer choice:");
                String correctAnswer = scan.nextLine();
                if (!(correctAnswer.equalsIgnoreCase(a1)) &&
                        !(correctAnswer.equalsIgnoreCase(a2)) && !(correctAnswer.equalsIgnoreCase(a3))
                        && !(correctAnswer.equalsIgnoreCase(a4))) {
                    System.out.println("Invalid input. The correct answer must be one of the inputted answer choices");
                } else {
                    teacherSubmissions.add(correctAnswer);
                    validInput = true;
                }
            }
            validInput = false;
            while(!validInput) {
                System.out.println("Would you like to enter another question?");
                String response = scan.nextLine();
                if (response.equalsIgnoreCase("no")) {
                    anotherQ = false;
                    validInput = true;
                } else if (response.equalsIgnoreCase("yes")) {
                    validInput = true;
                } else if (!(response.equalsIgnoreCase("yes")) &&
                        !(response.equalsIgnoreCase("no"))) {
                    System.out.println("Invalid input. Enter 'yes' or 'no'.");
                }
            }
            validInput = false;
        }
    }
    // Teachers can edit the quiz information. The new quizzes are updated in the teacherSubmissions Arraylist
    public static void editQuiz() {
        Scanner scan = new Scanner(System.in);
        int noQuiz = 0;
        System.out.println("Enter quiz title: ");
        String title = scan.nextLine();
        for (int i = 0; i < teacherSubmissions.size(); i++) {
            if (teacherSubmissions.get(i).equals(title)) {
                boolean invalidInput = false;
                while (!invalidInput) {
                    System.out.println("Do you want to edit the question?");
                    String editQuestion = scan.nextLine();
                    if (editQuestion.equalsIgnoreCase("yes")){
                        System.out.println("What is the new question?");
                        String newQuestion = scan.nextLine();
                        teacherSubmissions.set(i+2, newQuestion);
                        invalidInput = true;
                    } else if (editQuestion.equalsIgnoreCase("no")) {
                        invalidInput = true;
                    } else if (!(editQuestion.equalsIgnoreCase("yes")) &&
                            !(editQuestion.equalsIgnoreCase("no"))) {
                        System.out.println("Invalid input. Enter 'yes' or 'no'.");
                    }
                }
                invalidInput = false;
                System.out.println("Do you want to edit the randomization?");
                String editRandom = scan.nextLine();
                if (editRandom.equalsIgnoreCase("yes")) {
                    while(!invalidInput) {
                        System.out.println("What is the new randomization option? Enter 'y' for yes, 'n' for no.");
                        String newRandom = scan.nextLine();
                        if (!(newRandom.equalsIgnoreCase("y")) &&
                                !(newRandom.equalsIgnoreCase("n"))) {
                            System.out.println("Invalid input! Enter 'y' or 'n'.");
                        } else {
                            teacherSubmissions.set(i+1, newRandom);
                            invalidInput = true;
                        }
                    }
                    invalidInput = false;
                }
                System.out.println("Do you want to edit the answer choices?");
                String editAnswers = scan.nextLine();
                if (editAnswers.equalsIgnoreCase("yes")) {
                    System.out.println("Do you want to edit answer choice 1?");
                    String editA1 = scan.nextLine();
                    if (editA1.equalsIgnoreCase("yes")) {
                        System.out.println("Enter new answer choice 1: ");
                        String newA1 = scan.nextLine();
                        teacherSubmissions.set(i + 3, newA1);
                    }
                    System.out.println("Do you want to edit answer choice 2?");
                    String editA2 = scan.nextLine();
                    if (editA2.equalsIgnoreCase("yes")) {
                        System.out.println("Enter new answer choice 2: ");
                        String newA2 = scan.nextLine();
                        teacherSubmissions.set(i + 4, newA2);
                    }
                    System.out.println("Do you want to edit answer choice 3?");
                    String editA3 = scan.nextLine();
                    if (editA3.equalsIgnoreCase("yes")) {
                        System.out.println("Enter new answer choice 3: ");
                        String newA3 = scan.nextLine();
                        teacherSubmissions.set(i + 5, newA3);
                    }
                    System.out.println("Do you want to edit answer choice 4?");
                    String editA4 = scan.nextLine();
                    if (editA4.equalsIgnoreCase("yes")) {
                        System.out.println("Enter new answer choice 4: ");
                        String newA4 = scan.nextLine();
                        teacherSubmissions.set(i + 6, newA4);
                    }
                    System.out.println("Do you want to edit the correct answer choice?");
                    if (scan.nextLine().equalsIgnoreCase("yes")) {
                        while(!invalidInput) {
                            System.out.println("Enter new answer choice: ");
                            String newCorrectAnswer = scan.nextLine();
                            if (!(newCorrectAnswer.equalsIgnoreCase(teacherSubmissions.get(i + 3))) &&
                                    !(newCorrectAnswer.equalsIgnoreCase(teacherSubmissions.get(i + 4)))
                                    && !(newCorrectAnswer.equalsIgnoreCase(teacherSubmissions.get(i + 5)))
                                    && !(newCorrectAnswer.equalsIgnoreCase(teacherSubmissions.get(i + 6)))) {
                                System.out.println("Invalid input. The correct answer must be one of the inputted " +
                                        "answer choices");
                            } else {
                                teacherSubmissions.set(i + 7, newCorrectAnswer);
                                invalidInput = true;
                            }
                        }
                        System.out.println("Quiz Edited!");
                    }
                } else if (editAnswers.equalsIgnoreCase("no")) {
                    System.out.println("Quiz Edited!");

                }
            } else if (!(teacherSubmissions.get(i).equals(title))) {
                noQuiz++;
            }
        }
        if (noQuiz == (teacherSubmissions.size())/8) {
            System.out.println("There is no quiz with that title!");
        }
    }

    // Teachers can delete a quiz. The quiz is removed from the teacherSubmissions ArrayList
    public static void deleteQuiz() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the name of the quiz you want to delete: ");
        String title = scan.nextLine();
        boolean quizExists = false;
        for (int i = 0; i < teacherSubmissions.size(); i++) {
            if (teacherSubmissions.get(i).equalsIgnoreCase(title)) {
                quizExists = true;
                for (int j = 0; j < 8; j++) {
                    teacherSubmissions.remove(i);
                }
            }
        }
        if (!quizExists) {
            System.out.println("That quiz doesn't exist!");
        }
    }

    // Teachers can view the student responses to each question and manually assign point values for each question.
    // The point values earned on each question are inputted into the pointValues Arraylist
    public static void assignPointValues() {
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < Student.getStudentSubmissions().size(); i += 3) {
            System.out.println("Question: " + Student.getStudentSubmissions().get(i));
            System.out.println("Correct Answer: " + Student.getStudentSubmissions().get(i + 1));
            System.out.println("Student Answer: " + Student.getStudentSubmissions().get(i + 2));
            System.out.println("How many points would you like to assign for their answer?");
            pointValues.add(scan.nextInt());
        }
    }
    public static void main (String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        boolean start = true;
        while (start) {
            System.out.println("[1]Create Quiz\n[2]Edit Quiz\n[3]Delete Quiz\n[4]View Submissions" +
                    "\n[5]Assign Point Values\n[6]Exit");
            int choice = scan.nextInt();
            scan.nextLine();
            if (choice == 1) {
                createQuiz();
            } else if (choice == 2) {
                editQuiz();
            } else if (choice == 3) {
                deleteQuiz();
            } else if (choice == 4) {
                if (Student.getStudentSubmissions().size() == 0) {
                    System.out.println("There are no student submissions.");
                } else {
                    for (int i = 0; i < Student.getStudentSubmissions().size(); i++) {
                        if (Student.getStudentSubmissions().get(i) == null) {
                            System.out.println("There is no input");
                        } else {
                            System.out.println(Student.getStudentSubmissions().get(i));
                        }
                    }
                }
            } else if (choice == 5) {
                assignPointValues();
            } else if (choice == 6) {
                start = false;
            } else {
                System.out.println("Invalid input! Please try again.");
            }
        }
        PrintWriter pw = new PrintWriter("src/quizList.txt");
        for (String teacherSubmission : teacherSubmissions) {
            pw.println(teacherSubmission);
        }
        pw.close();
    }
}
