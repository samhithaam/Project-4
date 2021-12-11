import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.IOException;
import javax.swing.*;
import javax.swing.JFrame;
import java.io.*;
import java.io.FileNotFoundException;


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

    public static ArrayList<String> studentSubmissions = new ArrayList<>();


    // Teachers can create new quizzes with a title, choice to randomize questions, 4 answer choices, and the correct
    // answer. The created quizzes are added to the teacherSubmissions Arraylist
    public static void createQuiz() {
        boolean validInput = false;
        int exit;
        // Accounts for if teacher wants to input more than one question, main method accounts for
        // if teacher wants to create more than one quiz
        String quizTitle = JOptionPane.showInputDialog(null,
                "Enter quiz title:",
                "QUIZ TITLE", JOptionPane.QUESTION_MESSAGE);
        teacherSubmissions.add(quizTitle);
        while (!validInput) {
            int randomizeQuestions = JOptionPane.showConfirmDialog(null,
                    "Would you like to randomize answers?", "RANDOMIZE",
                    JOptionPane.YES_NO_OPTION);
            if (randomizeQuestions == JOptionPane.NO_OPTION) {
                teacherSubmissions.add("n");
                validInput = true;
            } else if (randomizeQuestions == JOptionPane.YES_OPTION) {
                teacherSubmissions.add("y");
                validInput = true;
            }
        }
        do {
            validInput = false;
            String quizQuestion = JOptionPane.showInputDialog(null,
                    "Enter question:",
                    "QUIZ QUESTION", JOptionPane.QUESTION_MESSAGE);
            teacherSubmissions.add(quizQuestion);
            String a1 = JOptionPane.showInputDialog(null,
                    "Enter answer choice 1:",
                    "ANSWER 1", JOptionPane.QUESTION_MESSAGE);
            teacherSubmissions.add(a1);
            String a2 = JOptionPane.showInputDialog(null,
                    "Enter answer choice 2:",
                    "ANSWER 2", JOptionPane.QUESTION_MESSAGE);
            teacherSubmissions.add(a2);
            String a3 = JOptionPane.showInputDialog(null,
                    "Enter answer choice 3:",
                    "ANSWER 3", JOptionPane.QUESTION_MESSAGE);
            teacherSubmissions.add(a3);
            String a4 = JOptionPane.showInputDialog(null,
                    "Enter answer choice 4:",
                    "ANSWER 4", JOptionPane.QUESTION_MESSAGE);
            teacherSubmissions.add(a4);
            while (!validInput) {
                String correctAnswer = JOptionPane.showInputDialog(null,
                        "Enter correct answer choice:",
                        "CORRECT ANSWER", JOptionPane.QUESTION_MESSAGE);
                if (correctAnswer == null) {
                    validInput = true;
                } else if (!(correctAnswer.equalsIgnoreCase(a1)) &&
                        !(correctAnswer.equalsIgnoreCase(a2)) && !(correctAnswer.equalsIgnoreCase(a3))
                        && !(correctAnswer.equalsIgnoreCase(a4))) {
                    JOptionPane.showMessageDialog(null,
                            "Invalid input. The correct answer must be one of the inputted answer choices!",
                            "QUIZ CORRECT ANSWER ERROR MESSAGE",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    teacherSubmissions.add(correctAnswer);
                    validInput = true;
                }
            }
            validInput = false;
            exit = JOptionPane.showConfirmDialog(null,
                    "Would you like to enter another question?", "CONTINUE",
                    JOptionPane.YES_NO_OPTION);
        } while (exit == JOptionPane.YES_OPTION);
        JOptionPane.showMessageDialog(null, "Successfully Created!");
    }

    // Teachers can edit the quiz information. The new quizzes are updated in the teacherSubmissions Arraylist
    public static void editQuiz() {
        int exit;
        do {
            boolean validInput = false;
            String quizTitle = "";
            while (!validInput) {
                quizTitle = JOptionPane.showInputDialog(null,
                        "Enter quiz title:",
                        "QUIZ TITLE", JOptionPane.QUESTION_MESSAGE);
                int noQuiz = 0;
                for (int i = 0; i < teacherSubmissions.size(); i++) {
                    if (teacherSubmissions.get(i).equals(quizTitle)) {
                        noQuiz++;
                    }
                }
                if (noQuiz == 0) {
                    JOptionPane.showMessageDialog(null, "There is no quiz with that title!",
                            "QUIZ TO EDIT NOT FOUND",
                            JOptionPane.INFORMATION_MESSAGE);
                }
                if (noQuiz > 0) {
                    validInput = true;
                }
            }

            for (int i = 0; i < teacherSubmissions.size(); i++) {
                if (teacherSubmissions.get(i).equals(quizTitle)) {
                    boolean invalidInput = false;
                    while (!invalidInput) {
                        int editQuestion = JOptionPane.showConfirmDialog(null,
                                "Would you like to edit the question?", "EDIT",
                                JOptionPane.YES_NO_OPTION);
                        if (editQuestion == JOptionPane.NO_OPTION) {
                            invalidInput = true;
                        } else if (editQuestion == JOptionPane.YES_OPTION) {
                            String newQuestion = JOptionPane.showInputDialog(null,
                                    "What is the new question?",
                                    "NEW QUESTION", JOptionPane.QUESTION_MESSAGE);
                            teacherSubmissions.set(i + 2, newQuestion);
                            invalidInput = true;
                        }
                        invalidInput = false;
                        if (JOptionPane.showConfirmDialog(null,
                                "Would you like to edit the randomization?", "EDIT RANDOMIZE",
                                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                            while (!invalidInput) {
                                int newRandom = JOptionPane.showConfirmDialog(null,
                                        "What is the new randomization option?", "EDITED RANDOMIZE",
                                        JOptionPane.YES_NO_OPTION);
                                if (newRandom == JOptionPane.NO_OPTION) {
                                    teacherSubmissions.set(i + 1, "n");
                                    invalidInput = true;
                                } else if (newRandom == JOptionPane.YES_OPTION) {
                                    teacherSubmissions.set(i + 1, "y");
                                    invalidInput = true;
                                }
                            }
                            invalidInput = false;
                        }
                        boolean editMore = false;
                        int editAnswers = JOptionPane.showConfirmDialog(null,
                                "Would you like to edit the answer choices?", "EDIT ANSWER CHOICES",
                                JOptionPane.YES_NO_OPTION);
                        if (editAnswers == JOptionPane.YES_OPTION) {
                            while (!editMore) {
                                Integer[] choices = {1, 2, 3, 4};
                                int userChoice;
                                userChoice = (int) JOptionPane.showInputDialog(null,
                                        "Which answer choice?",
                                        "WHICH ANSWER", JOptionPane.QUESTION_MESSAGE,
                                        null, choices, choices[0]);
                                if (userChoice == 1) {
                                    String newA1 = JOptionPane.showInputDialog(null,
                                            "Enter new answer choice 1:",
                                            "NEW ANSWER 1", JOptionPane.QUESTION_MESSAGE);
                                    teacherSubmissions.set(i + 3, newA1);
                                }
                                if (userChoice == 2) {
                                    String newA2 = JOptionPane.showInputDialog(null,
                                            "Enter new answer choice 2:",
                                            "NEW ANSWER 2", JOptionPane.QUESTION_MESSAGE);
                                    teacherSubmissions.set(i + 4, newA2);
                                }
                                if (userChoice == 3) {
                                    String newA3 = JOptionPane.showInputDialog(null,
                                            "Enter new answer choice 3:",
                                            "NEW ANSWER 3", JOptionPane.QUESTION_MESSAGE);
                                    teacherSubmissions.set(i + 5, newA3);
                                }
                                if (userChoice == 4) {
                                    String newA4 = JOptionPane.showInputDialog(null,
                                            "Enter new answer choice 4:",
                                            "NEW ANSWER 4", JOptionPane.QUESTION_MESSAGE);
                                    teacherSubmissions.set(i + 6, newA4);
                                }
                                int editCorrectAnswer = JOptionPane.showConfirmDialog(null,
                                        "Would you like to edit the correct answer?", "EDIT CORRECT ANSWER",
                                        JOptionPane.YES_NO_OPTION);
                                if (editCorrectAnswer == JOptionPane.YES_OPTION) {
                                    while (!invalidInput) {
                                        String newCorrectAnswer = JOptionPane.showInputDialog(null,
                                                "Enter new correct answer choice:",
                                                "NEW CORRECT ANSWER", JOptionPane.QUESTION_MESSAGE);
                                        if (!(newCorrectAnswer.equalsIgnoreCase(teacherSubmissions.get(i + 3))) &&
                                                !(newCorrectAnswer.equalsIgnoreCase(teacherSubmissions.get(i + 4)))
                                                && !(newCorrectAnswer.equalsIgnoreCase(teacherSubmissions.get(i + 5)))
                                                && !(newCorrectAnswer.equalsIgnoreCase(teacherSubmissions.get(i + 6)))) {
                                            JOptionPane.showMessageDialog(null,
                                                    "Invalid input. " +
                                                            "The correct answer must be one of the inputted answer choices",
                                                    "INVALID EDITED CORRECT ANSWER",
                                                    JOptionPane.INFORMATION_MESSAGE);
                                        } else {
                                            teacherSubmissions.set(i + 7, newCorrectAnswer);
                                            invalidInput = true;
                                        }
                                    }
                                }
                                int editMoreAnswers = JOptionPane.showConfirmDialog(null,
                                        "Would you like to edit another answer?", "CONTINUE",
                                        JOptionPane.YES_NO_OPTION);
                                if (editMoreAnswers == JOptionPane.NO_OPTION) {
                                    editMore = true;
                                }
                            }
                        }
                        JOptionPane.showMessageDialog(null, "Quiz Edited!",
                                "QUIZ EDITED MESSAGE",
                                JOptionPane.INFORMATION_MESSAGE);
                        invalidInput = true;

                    }
                }
            }
            exit = JOptionPane.showConfirmDialog(null,
                    "Would you like to edit another quiz", "CONTINUE",
                    JOptionPane.YES_NO_OPTION);
        } while (exit == JOptionPane.YES_OPTION);
        JOptionPane.showMessageDialog(null, "Successfully Edited!");
    }

    // Teachers can delete a quiz. The quiz is removed from the teacherSubmissions ArrayList
    public static void deleteQuiz() {
        String deleteQuizName = JOptionPane.showInputDialog(null,
                "Enter the name of the quiz you want to delete:",
                "DELETE QUIZ NAME", JOptionPane.QUESTION_MESSAGE);
        boolean quizExists = false;
        for (int i = 0; i < teacherSubmissions.size(); i++) {
            if (teacherSubmissions.get(i).equalsIgnoreCase(deleteQuizName)) {
                quizExists = true;
                for (int j = 0; j < 8; j++) {
                    teacherSubmissions.remove(i);
                }
                JOptionPane.showMessageDialog(null, "Quiz Deleted!");
            }
        }
        if (!quizExists) {
            JOptionPane.showMessageDialog(null, "That quiz doesn't exist!");
        }
    }


    // Teachers can view the student responses to each question and manually assign point values for each question.
    // The point values earned on each question are inputted into the pointValues Arraylist
    public static void assignPointValues() throws FileNotFoundException {
        ArrayList<String> studentSubmissions = Student.readFile("src/StudentSubmissions.txt");
        // Student.readFile() returns null if "StudentSubmissions.txt" doesn't exist
        // if "StudentSubmissions.txt" doesn't exist, there aren't any submissions
        if (studentSubmissions == null || studentSubmissions.size() == 0) {
            JOptionPane.showMessageDialog(null,
                    "There are no student submissions.",
                    "No Submissions Message",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            for (int i = 0; i < studentSubmissions.size(); i += 3) {
                String question = "Question: " + studentSubmissions.get(i);
                question += "\nCorrect Answer: " + studentSubmissions.get(i + 1);
                question += "\nStudent Answer: " + studentSubmissions.get(i + 2);
                JOptionPane.showMessageDialog(null,
                        question,
                        "Submissions Message",
                        JOptionPane.INFORMATION_MESSAGE);
                String pointsAssigned = JOptionPane.showInputDialog(null,
                        "How many points would you like to assign for their answer?",
                        "POINTS ASSIGNED", JOptionPane.QUESTION_MESSAGE);
                // allow teacher to manually assign points
                pointValues.add(Integer.parseInt(pointsAssigned));
            }
        }
        JOptionPane.showMessageDialog(null,
                "Points Assigned!",
                "Points Assigned Message",
                JOptionPane.INFORMATION_MESSAGE);
        PrintWriter pw = new PrintWriter("src/pointList.txt");
        for (String pointList : studentSubmissions) {
            pw.println(pointList);
        }
        pw.close();
    }

    public static ArrayList<String> readFile() {
        // if "StudentSubmissions.txt" doesn't exist, return null
        // avoid FileNotFoundException
        if (!new File("src/StudentSubmissions.txt").exists()) {
            return null;
        }

        try (BufferedReader bfr = new BufferedReader(new FileReader("src/StudentSubmissions.txt"))) {
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

    public static void printQuizList() throws FileNotFoundException {
        PrintWriter pw = new PrintWriter("src/quizList.txt");
        for (String teacherSubmission : teacherSubmissions) {
            pw.println(teacherSubmission);
        }
        pw.close();
    }

    public static void createMenu() {
        JFrame jf = new JFrame("Teacher Quiz Tool");
        jf.setVisible(true);
        jf.setSize(600, 400);
        jf.setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE);
        Container content = jf.getContentPane();
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        content.add(panel, BorderLayout.CENTER);
        JLabel instructions = new JLabel("Select an Option Below");
        panel.add(instructions);
        content.add(panel, BorderLayout.LINE_START);

        JButton createButton = new JButton("Create Quiz");
        panel.add(createButton);

        JButton editButton = new JButton("Edit Quiz");
        panel.add(editButton);

        JButton deleteButton = new JButton("Delete Quiz");
        panel.add(deleteButton);

        JButton viewSubmissionsButton = new JButton("View Submissions");
        panel.add(viewSubmissionsButton);

        JButton assignPointValuesButton = new JButton("Assign Point Values");
        panel.add(assignPointValuesButton);

        JButton exitButton = new JButton("Exit");
        panel.add(exitButton);

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createQuiz();
            }
        });


        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editQuiz();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteQuiz();
            }
        });

        viewSubmissionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> studentSubmissions = Student.readFile("src/StudentSubmissions.txt");
                if (studentSubmissions == null || studentSubmissions.size() == 0) {
                    JOptionPane.showMessageDialog(null,
                            "There are no student submissions.",
                            "No Submissions Message",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    for (int i = 0; i < studentSubmissions.size(); i += 3) {
                        String question = "Question: " + studentSubmissions.get(i);
                        question += "\nCorrect Answer: " + studentSubmissions.get(i + 1);
                        question += "\nStudent Answer: " + studentSubmissions.get(i + 2);
                        JOptionPane.showMessageDialog(null,
                                question,
                                "Submissions Message",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });

        assignPointValuesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    assignPointValues();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "Thank you for using the Teacher Quiz Tool!",
                        "Exit Message",
                        JOptionPane.INFORMATION_MESSAGE);
                try {
                    printQuizList();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                System.exit(0);
            }
        });

    }

    public static void updateArrayList() {
        File teacherSubmissionsFile = new File("src/quizList.txt");
        // if file exists, call readFile()
        if (teacherSubmissionsFile.exists()) {
            teacherSubmissions = readFile();
        }
    }

    public static void main(String[] args) throws IOException {
        updateArrayList();
        createMenu();
    }
}
