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
        while (anotherQ) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter quiz title:");
            // TODO: Make sure that they enter a string, as opposed to an int (for example)
            teacherSubmissions.add(scan.nextLine());
            System.out.println("Randomize questions? Enter 'y' for yes, 'n' for no.");
            // TODO: If they don't input either 'y' or 'n', prompt user again
            teacherSubmissions.add(scan.nextLine());
            System.out.println("Enter question:");
            // TODO: Make sure that they enter a string, as opposed to an int (for example)
            teacherSubmissions.add(scan.nextLine());
            System.out.println("Enter answer choice 1:");
            // TODO: Make sure that they enter a string, as opposed to an int (for example)
            teacherSubmissions.add(scan.nextLine());
            System.out.println("Enter answer choice 2:");
            // TODO: Make sure that they enter a string, as opposed to an int (for example)
            teacherSubmissions.add(scan.nextLine());
            System.out.println("Enter answer choice 3:");
            // TODO: Make sure that they enter a string, as opposed to an int (for example)
            teacherSubmissions.add(scan.nextLine());
            System.out.println("Enter answer choice 4:");
            // TODO: Make sure that they enter a string, as opposed to an int (for example)
            teacherSubmissions.add(scan.nextLine());
            System.out.println("Enter correct answer choice:");
            // TODO: Make sure that they enter a string, as opposed to an int (for example)
            teacherSubmissions.add(scan.nextLine());
            System.out.println("Would you like to enter another question?");
            if (scan.nextLine().equalsIgnoreCase("no")) {
                anotherQ = false;
            }
        }
    }

    // Teachers can edit the quiz information. The new quizzes are updated in the teacherSubmissions Arraylist
    // TODO: Handle exceptions, if the user enters 'n' or invalid input, for example.
    public static void editQuiz() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter quiz title: ");
        String title = scan.nextLine();
        for (int i = 0; i < teacherSubmissions.size(); i++) {
            if (teacherSubmissions.get(i).equals(title)) {
                System.out.println("Do you want to edit the question?");
                String editQuestion = scan.nextLine();
                if (editQuestion.equalsIgnoreCase("yes")) {
                    System.out.println("What is the new question?");
                    teacherSubmissions.set(i + 2, scan.nextLine());
                } else {
                    continue;
                }
                System.out.println("Do you want to edit the randomization?");
                if (scan.nextLine().equalsIgnoreCase("yes")) {
                    System.out.println("What is the new randomization option?");
                    teacherSubmissions.set(i + 1, scan.nextLine());
                } else {
                    continue;
                }
                System.out.println("Do you want to edit the answer choices?");
                if (scan.nextLine().equalsIgnoreCase("yes")) {
                    System.out.println("Do you want to edit answer choice 1?");
                    if (scan.nextLine().equalsIgnoreCase("yes")) {
                        System.out.println("Enter new answer choice: ");
                        teacherSubmissions.set(i + 3, scan.nextLine());
                    } else {
                        continue;
                    }
                    System.out.println("Do you want to edit answer choice 2?");
                    if (scan.nextLine().equalsIgnoreCase("yes")) {
                        System.out.println("Enter new answer choice: ");
                        teacherSubmissions.set(i + 4, scan.nextLine());
                    } else {
                        continue;
                    }
                    System.out.println("Do you want to edit answer choice 3?");
                    String editAnswer3 = scan.nextLine();
                    if (editAnswer3.equalsIgnoreCase("yes")) {
                        System.out.println("Enter new answer choice: ");
                        teacherSubmissions.set(i + 5, scan.nextLine());
                    } else {
                        continue;
                    }
                    System.out.println("Do you want to edit answer choice 4?");
                    if (scan.nextLine().equalsIgnoreCase("yes")) {
                        System.out.println("Enter new answer choice: ");
                        teacherSubmissions.set(i + 6, scan.nextLine());
                    } else {
                        continue;
                    }
                    System.out.println("Do you want to edit the correct answer choice?");
                    if (scan.nextLine().equalsIgnoreCase("yes")) {
                        System.out.println("Enter new answer choice: ");
                        teacherSubmissions.set(i + 7, scan.nextLine());
                    }
                }
            }
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
                // TODO: check if studentSubmissions() ArrayList is null or size is 0, print appropriate error response
                for (int i = 0; i < Student.getStudentSubmissions().size(); i++) {
                    System.out.println(Student.getStudentSubmissions().get(i));
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
