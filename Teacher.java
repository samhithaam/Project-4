import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Teacher.java
 * @author Ritu Atreyas and Rithvik Thiagu
 * @version 11/14/2021
 */

public class Teacher {

    private static ArrayList<String> teacherSubmissions = readFile();
    private static ArrayList<Integer> pointValues = new ArrayList<>();
    private static int totalPoints = 0;

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

    public static void createQuiz() throws FileNotFoundException {
        boolean anotherQ = true;
        ArrayList<String> teacherSubmissionsLocal = readFile();
        if (teacherSubmissionsLocal == null) {
            teacherSubmissionsLocal = new ArrayList<>();
        }

        Scanner scan = new Scanner(System.in);
        System.out.println("Enter quiz title:");
        teacherSubmissionsLocal.add(scan.nextLine());
        boolean invalidInput = true;
        String randomize = new String("");
        while (invalidInput) {
            System.out.println("Randomize questions? Enter 'y' for yes, 'n' for no.");
            randomize = scan.nextLine();
            if (randomize.equalsIgnoreCase("y") || randomize.equalsIgnoreCase("n")) {
                invalidInput = false;
                teacherSubmissionsLocal.add(randomize);
            }
        }

        // the following loop allows teacher to input multiple questions, main method allows teacher to create
        // multiple quizzes
        while (anotherQ) {
            System.out.print("Enter question: ");
            teacherSubmissionsLocal.add(scan.nextLine());
            System.out.print("\nEnter answer choice 1: ");
            teacherSubmissionsLocal.add(scan.nextLine());
            System.out.print("\nEnter answer choice 2: ");
            teacherSubmissionsLocal.add(scan.nextLine());
            System.out.print("\nEnter answer choice 3: ");
            teacherSubmissionsLocal.add(scan.nextLine());
            System.out.print("\nEnter answer choice 4: ");
            teacherSubmissionsLocal.add(scan.nextLine());
            System.out.print("\nEnter correct answer: ");
            teacherSubmissionsLocal.add(scan.nextLine());
            System.out.println("Would you like to enter another question?");
            if (scan.nextLine().equalsIgnoreCase("no")) {
                anotherQ = false;
            }
        }
        PrintWriter pw = new PrintWriter("src/quizList.txt");
        for (String teacherSubmission : teacherSubmissionsLocal) {
            pw.println(teacherSubmission);
        }
        pw.close();
    }

    // Teachers can edit the quiz information. The new quizzes are updated in the teacherSubmissions Arraylist
    public static void editQuiz() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter quiz title: ");
        String title = scan.nextLine();
        boolean quizExists = false;
        for (int i = 0; i < teacherSubmissions.size(); i++) {
            if (teacherSubmissions.get(i).equals(title)) {
                quizExists = true;
                while (true) {
                    System.out.println("Do you want to edit the question?");
                    if (scan.nextLine().equalsIgnoreCase("yes")) {
                        System.out.println("What is the new question?");
                        teacherSubmissions.set(i + 2, scan.nextLine());
                    }
                    System.out.println("Do you want to edit the answer choices?");
                    if (scan.nextLine().equalsIgnoreCase("yes")) {
                        System.out.println("Do you want to edit answer choice 1?");
                        if (scan.nextLine().equalsIgnoreCase("yes")) {
                            System.out.print("Enter new answer choice: ");
                            teacherSubmissions.set(i + 3, scan.nextLine());
                        }
                        System.out.println("Do you want to edit answer choice 2?");
                        if (scan.nextLine().equalsIgnoreCase("yes")) {
                            System.out.print("Enter new answer choice: ");
                            teacherSubmissions.set(i + 4, scan.nextLine());
                        }
                        System.out.println("Do you want to edit answer choice 3?");
                        String editAnswer3 = scan.nextLine();
                        if (editAnswer3.equalsIgnoreCase("yes")) {
                            System.out.print("Enter new answer choice: ");
                            teacherSubmissions.set(i + 5, scan.nextLine());
                        }
                        System.out.println("Do you want to edit answer choice 4?");
                        if (scan.nextLine().equalsIgnoreCase("yes")) {
                            System.out.print("Enter new answer choice: ");
                            teacherSubmissions.set(i + 6, scan.nextLine());
                        }
                        System.out.println("Do you want to edit the correct answer choice?");
                        if (scan.nextLine().equalsIgnoreCase("yes")) {
                            System.out.print("Enter new answer choice: ");
                            teacherSubmissions.set(i + 7, scan.nextLine());
                        }
                    }
                    try {
                        if (teacherSubmissions.get(i + 9).equals("y") || teacherSubmissions.get(i + 9).equals("n"))
                            break;
                        else
                            i += 6;
                    } catch (IndexOutOfBoundsException e) {
                        return;
                    }
                }
            }
        }
        if (!quizExists) {
            System.out.println("That quiz doesn't exist!");
        }
    }

    public static void deleteQuiz() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the name of the quiz you want to delete: ");
        for (Quiz quiz : Quiz.getQuizzes()) {
            System.out.println(quiz.getQuizName());
            // print all quiz names so teacher can choose the quiz they want to delete
        }
        String title = scan.nextLine();
        boolean quizExists = false;
        int counter = 0;
        for (int i = 0; i < teacherSubmissions.size(); i++) {
            if (teacherSubmissions.get(i).equalsIgnoreCase(title)) {
                quizExists = true;
                while (i < teacherSubmissions.size() && (!teacherSubmissions.get(i).equals("y") || !teacherSubmissions.get(counter).equals("n"))) {
                    if (teacherSubmissions.get(i) != null) {
                        teacherSubmissions.remove(i);
                    }
                }
            }
        }

        if (!quizExists) {
            System.out.println("That quiz doesn't exist!");
        }
    }

    // update pointValues ArrayList
    public static void assignPointValues() {
        ArrayList<String> studentSubmissionsLocal= Student.readFile("src/StudentSubmissions.txt");
        if (studentSubmissionsLocal == null || studentSubmissionsLocal.size() == 0) {
            System.out.println("There aren't any submissions yet!");
            return;
        }

        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < studentSubmissionsLocal.size(); i += 3) {
            System.out.println("\nQuestion: " + studentSubmissionsLocal.get(i));
            System.out.println("Correct Answer: " + studentSubmissionsLocal.get(i + 1));
            System.out.println("Student Answer: " + studentSubmissionsLocal.get(i + 2));
            System.out.println("How many points would you like to assign for their answer?");
            pointValues.add(scan.nextInt());
        }
    }

    public static void main (String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        boolean start = true;
        while (start) {
            int choice = 0;
            boolean notNumber = true;
            while (notNumber) {
                try {
                    System.out.println("1. Create Quiz\n2. Edit Quiz\n3. Delete Quiz\n4. View Submissions" +
                            "\n5. Assign Point Values\n6. Exit");
                    choice = Integer.parseInt(scan.nextLine());
                    notNumber = false;
                } catch (NumberFormatException e) {
                    System.out.println("You didn't enter a number! Try again.");
                }
            }
            if (choice == 1) {
                createQuiz();
            } else if (choice == 2) {
                editQuiz();
            } else if (choice == 3) {
                deleteQuiz();
            } else if (choice == 4) { // view submissions
                // read from file
                ArrayList<String> studentSubmissionsLocal= Student.readFile("src/StudentSubmissions.txt");
                if (studentSubmissionsLocal == null || studentSubmissionsLocal.size() == 0) {
                    System.out.println("There aren't any submissions yet!");
                } else {
                    for (int i = 0; i < studentSubmissionsLocal.size(); i += 3) {
                        System.out.println("\nQuestion: " + studentSubmissionsLocal.get(i));
                        System.out.println("Correct Answer: " + studentSubmissionsLocal.get(i + 1));
                        System.out.println("Student Answer: " + studentSubmissionsLocal.get(i + 2));
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
    }

    public static ArrayList<String> readFile() {
        try (BufferedReader bfr = new BufferedReader(new FileReader("src/quizList.txt"))) {
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
}
