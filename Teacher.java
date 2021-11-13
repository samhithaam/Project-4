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
        for (int i = 0; i < pointValues.size(); i++) {
            totalPoints = totalPoints + pointValues.get(i);
        }
        return totalPoints;
    }
    public static ArrayList<String> getTeacherSubmissions() {
        return teacherSubmissions;
    }

    public static ArrayList<Integer> getPointValues() {
        return pointValues;
    }

    // Teachers can create new quizzes with a title, choice to randomize questions, each answer choice, and the correct
    // answer. The created quizzes are inputted into the teacherSubmissions Arraylist
    public static void createQuiz() {
        boolean anotherQ = true;
        while (anotherQ) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter Quiz Title:");
            String quizTitle = scan.nextLine();
            teacherSubmissions.add(quizTitle);
            System.out.println("Randomize Questions? (Y/N)");
            String random = scan.nextLine();
            teacherSubmissions.add(random);
            System.out.println("Enter Question:");
            String question = scan.nextLine();
            teacherSubmissions.add(question);
            System.out.println("Enter Answer Choice 1:");
            String answer1 = scan.nextLine();
            teacherSubmissions.add(answer1);
            System.out.println("Enter Answer Choice 2:");
            String answer2 = scan.nextLine();
            teacherSubmissions.add(answer2);
            System.out.println("Enter Answer Choice 3:");
            String answer3 = scan.nextLine();
            teacherSubmissions.add(answer3);
            System.out.println("Enter Answer Choice 4:");
            String answer4 = scan.nextLine();
            teacherSubmissions.add(answer4);
            System.out.println("Enter Correct Answer Choice:");
            String answer = scan.nextLine();
            teacherSubmissions.add(answer);
            System.out.println("Do you want to enter another question?");
            String keepGoing = scan.nextLine();
            if (keepGoing.equalsIgnoreCase("No")) {
                anotherQ = false;
            }
        }
    }

    // Teachers can edit the quiz information. The new quizzes are updated in the teacherSubmissions Arraylist
    public static void editQuiz() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter Quiz Title");
        String title = scan.nextLine();
        for (int i = 0; i < teacherSubmissions.size(); i++) {
            if (teacherSubmissions.get(i).equals(title)) {
                System.out.println("Do you want to edit the question?");
                String editQuestion = scan.nextLine();
                if (editQuestion.equalsIgnoreCase("Yes")) {
                    System.out.println("What is the new question?");
                    String editedQuestion = scan.nextLine();
                    teacherSubmissions.set(i + 2, editedQuestion);
                }
                System.out.println("Do you want to edit the randomization?");
                String editRandom = scan.nextLine();
                if (editRandom.equalsIgnoreCase("Y")) {
                    System.out.println("What is the new randomization option?");
                    String editedRandom = scan.nextLine();
                    teacherSubmissions.set(i + 1, editedRandom);
                }
                System.out.println("Do you want to edit the answer choices?");
                String editAnswer = scan.nextLine();
                if (editAnswer.equalsIgnoreCase("Yes")) {
                    System.out.println("Do you want to edit answer choice 1?");
                    String editAnswer1 = scan.nextLine();
                    if (editAnswer1.equalsIgnoreCase("Yes")) {
                        System.out.println("Enter new answer choice");
                        String newAnswer1 = scan.nextLine();
                        teacherSubmissions.set(i + 3, newAnswer1);
                    }
                    System.out.println("Do you want to edit answer choice 2?");
                    String editAnswer2 = scan.nextLine();
                    if (editAnswer2.equalsIgnoreCase("Yes")) {
                        System.out.println("Enter new answer choice");
                        String newAnswer2 = scan.nextLine();
                        teacherSubmissions.set(i + 4, newAnswer2);
                    }
                    System.out.println("Do you want to edit answer choice 3?");
                    String editAnswer3 = scan.nextLine();
                    if (editAnswer3.equalsIgnoreCase("Yes")) {
                        System.out.println("Enter new answer choice");
                        String newAnswer3 = scan.nextLine();
                        teacherSubmissions.set(i + 5, newAnswer3);
                    }
                    System.out.println("Do you want to edit answer choice 4?");
                    String editAnswer4 = scan.nextLine();
                    if (editAnswer4.equalsIgnoreCase("Yes")) {
                        System.out.println("Enter new answer choice");
                        String newAnswer4 = scan.nextLine();
                        teacherSubmissions.set(i + 6, newAnswer4);
                    }
                }
                System.out.println("Do you want to edit the correct answer choice?");
                String editCorrectAnswer = scan.nextLine();
                if (editCorrectAnswer.equalsIgnoreCase("Yes")) {
                    System.out.println("Enter new answer choice");
                    String newCorrectAnswer = scan.nextLine();
                    teacherSubmissions.set(i + 7, newCorrectAnswer);
                }
            }
        }
    }

    // Teachers can delete a quiz. The quiz is removed from the teacherSubmissions ArrayList
    public static void deleteQuiz() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter Quiz Title");
        String title = scan.nextLine();
        for (int i = 0; i < teacherSubmissions.size(); i++) {
            if (teacherSubmissions.get(i).equalsIgnoreCase(title)) {
                teacherSubmissions.remove(i);
                teacherSubmissions.remove(i);
                teacherSubmissions.remove(i);
                teacherSubmissions.remove(i);
                teacherSubmissions.remove(i);
                teacherSubmissions.remove(i);
                teacherSubmissions.remove(i);
                teacherSubmissions.remove(i);
            }
        }
    }

    // Teachers can view the student responses to each question and manually assign point values for each question.
    // The point values earned on each question are inputted into the pointValues Arraylist
    public static void assignPointValues() {
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < Student.getStudentSubmissions().size(); i+=3) {
            System.out.println("Question:" + Student.getStudentSubmissions().get(i));
            System.out.println("Correct Answer:" + Student.getStudentSubmissions().get(i+1));
            System.out.println("Student Answer:" + Student.getStudentSubmissions().get(i+2));
            System.out.println("How many points to this question?");
            int points = scan.nextInt();
            pointValues.add(points);
        }
    }

    public static void main (String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        boolean start = true;
        while (start) {
            System.out.println("[1]Create Quiz, [2]Edit Quiz, [3]Delete Quiz, [4]View Submissions, " +
                    "[5]Assign Point Values, [6]Exit");
            int choice = scan.nextInt();
            scan.nextLine();
            if (choice == 1) {
                createQuiz();
            }
            if (choice == 2) {
                editQuiz();
            }
            if (choice == 3) {
                deleteQuiz();
            }
            if (choice == 4) {
                for (int i = 0; i < Student.getStudentSubmissions().size(); i++) {
                    System.out.println(Student.getStudentSubmissions().get(i));
                }
            }
            if (choice == 5) {
                assignPointValues();
            }
            if (choice == 6) {
                start = false;
            }
        }
        PrintWriter pw = new PrintWriter("src/quizList.txt");
        for (int i = 0; i < teacherSubmissions.size(); i++) {
            pw.println(teacherSubmissions.get(i));
        }
        pw.close();
    }
}
