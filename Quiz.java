import java.io.*;
import java.util.ArrayList;

/**
 * Quiz.java
 * @author Ritu Atreyas
 * @version 11/14/2021
 */

public class Quiz {

    // quizzes ArrayList stores contents of teacherSubmissions in a more accessible format
    // each element is a Quiz object and each Quiz object stores the quizName, whether or not that quiz is randomized,
    // an ArrayList of all the questions, an ArrayList of all the answer choices,
    // and an ArrayList of all the correct answers
    private static ArrayList<Quiz> quizzes = new ArrayList<>();
    // non static field variables, initialized in default constructor
    private String quizName;
    private boolean randomized;
    private ArrayList<String> questions;
    private ArrayList<String> answerChoices;
    private ArrayList<String> correctAnswers;

    public static void main (String [] args) {
        // can either load contents of quizzes ArrayList using teacherSubmissions ArrayList
        // ArrayList<String> teacherSubmissions = Teacher.getTeacherSubmissions();
        // quizzes = formatTeacherSubmissions(teacherSubmissions);
        updateArrayList();
        // or can load contents of quizzes ArrayList using quizList text file
        if (quizzes == null || quizzes.size() == 0) { // nothing in the text file
            System.out.println("Error reading from file.");
        }
    }

    public Quiz() {
        quizName = new String("");
        randomized = false;
        questions = new ArrayList<>();
        answerChoices = new ArrayList<>();
        correctAnswers = new ArrayList<>();
    }

    public Quiz(String quizName, boolean randomized, ArrayList<String> questions,
                ArrayList<String> answerChoices, ArrayList<String> correctAnswers) {
        this.quizName = quizName;
        this.randomized = randomized;
        this.questions = questions;
        this.answerChoices = answerChoices;
        this.correctAnswers = correctAnswers;
    }

    public static ArrayList<Quiz> readFile(String fileName) {
        try (BufferedReader bfr = new BufferedReader(new FileReader(fileName))) {
            ArrayList<String> fileContents = new ArrayList<>();
            String line = new String("");
            while ((line = bfr.readLine()) != null) {
                fileContents.add(line);
            }

            if (fileContents.size() == 0) {
                return null;
            }

            // the index value of the next occurrence of 'y' and 'n' is how we're making sense of teacher input
            // the number of times 'y' or 'n' shows up in the text file = the number of quizzes there are
            // what the user inputs for question about randomization is consistent among every quiz (either 'y' or 'n')
            ArrayList<Integer> indexOfY_N = new ArrayList<>();
            for (int i = 0; i < fileContents.size(); i++) {
                if (fileContents.get(i).equalsIgnoreCase("y") ||
                        fileContents.get(i).equalsIgnoreCase("n")) {
                    indexOfY_N.add(i);
                }
            }

            ArrayList<Quiz> quizzesLocal = new ArrayList<>();
            ArrayList<String> answerChoicesLocal = new ArrayList<>();
            ArrayList<String> questionsLocal = new ArrayList<>();
            ArrayList<String> correctAnswersLocal = new ArrayList<>();
            int indexValue;
            boolean randomizedLocal;
            for (int i = 0; i < indexOfY_N.size(); i++) {
                if ((i + 1) < indexOfY_N.size()) {
                    answerChoicesLocal.clear();
                    questionsLocal.clear();
                    correctAnswersLocal.clear();
                    indexValue = indexOfY_N.get(i);

                    randomizedLocal = fileContents.get(indexValue).equalsIgnoreCase("y");

                    int questionIndex = indexValue + 1;
                    while (questionIndex < indexOfY_N.get(i + 1)) {
                        questionsLocal.add(fileContents.get(questionIndex));
                        if (questionIndex + 7 == indexOfY_N.get(i + 1)) {
                            break;
                        } else {
                            questionIndex += 6;
                        }
                    }

                    int answerChoicesIndex = indexValue + 2;
                    while ((answerChoicesIndex + 3) < indexOfY_N.get(i + 1)) {
                           answerChoicesLocal.add(fileContents.get(answerChoicesIndex));
                        answerChoicesLocal.add(fileContents.get(answerChoicesIndex + 1));
                        answerChoicesLocal.add(fileContents.get(answerChoicesIndex + 2));
                        answerChoicesLocal.add(fileContents.get(answerChoicesIndex + 3));
                        if (answerChoicesIndex + 6 == indexOfY_N.get(i + 1)) {
                            break;
                        } else {
                            answerChoicesIndex += 6;
                        }
                    }

                    int correctAnswerIndex = indexValue + 6;
                    while (correctAnswerIndex < indexOfY_N.get(i + 1)) {
                        correctAnswersLocal.add(fileContents.get(correctAnswerIndex));
                        if (correctAnswerIndex + 2 == indexOfY_N.get(i + 1)) {
                            break;
                        } else {
                            correctAnswerIndex += 6;
                        }
                    }
                    quizzesLocal.add(new Quiz(fileContents.get(indexValue - 1), randomizedLocal,
                            questionsLocal, answerChoicesLocal, correctAnswersLocal));
                }
            }
            // following lines of code are to handle when i + 1 = indexOfY_N.size()
            // when i + 1 = indexOfY_N.size(), indexOfY_N.get(i + 1) will result in IndexOutOfBoundsException
            // use fileContents.size() to loop through entire ArrayList as opposed to the next occurrence of 'y' or 'n'

            indexValue = indexOfY_N.get(indexOfY_N.size() - 1);
            randomizedLocal = fileContents.get(indexValue).equalsIgnoreCase("y");

            int questionIndex = indexValue + 1;
            while (questionIndex < fileContents.size()) {
                questionsLocal.add(fileContents.get(questionIndex));
                questionIndex += 6;
            }

            int answerChoicesIndex = indexValue + 2;
            while ((answerChoicesIndex + 3) < fileContents.size()) {
                answerChoicesLocal.add(fileContents.get(answerChoicesIndex));
                answerChoicesLocal.add(fileContents.get(answerChoicesIndex + 1));
                answerChoicesLocal.add(fileContents.get(answerChoicesIndex + 2));
                answerChoicesLocal.add(fileContents.get(answerChoicesIndex + 3));
                answerChoicesIndex += 6;
            }

            int correctAnswerIndex = indexValue + 6;
            while (correctAnswerIndex < fileContents.size()) {
                correctAnswersLocal.add(fileContents.get(correctAnswerIndex));
                correctAnswerIndex += 6;
            }

            quizzesLocal.add(new Quiz(fileContents.get(indexValue - 1), randomizedLocal,
                    questionsLocal, answerChoicesLocal, correctAnswersLocal));

            // update quizOutput.txt with contents of quizzesLocal
            try (PrintWriter pw = new PrintWriter(new FileWriter("src/quizOutput.txt"))) {
                for (Quiz quiz : quizzesLocal) {
                    pw.println(quiz.toString()); // call overridden toString method
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return quizzesLocal;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Quiz> getQuizzes() {
        return quizzes;
    }

    public String getQuizName() {
        return quizName;
    }

    public boolean isRandomized() {
        return randomized;
    }

    public ArrayList<String> getQuestions() {
        return questions;
    }

    public ArrayList<String> getAnswerChoices() {
        return answerChoices;
    }

    public ArrayList<String> getCorrectAnswers() {
        return correctAnswers;
    }

    public static void setQuizzes(ArrayList<Quiz> quizzes) {
        Quiz.quizzes = quizzes;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public void setRandomized(boolean randomized) {
        this.randomized = randomized;
    }

    public void setQuestions(ArrayList<String> questions) {
        this.questions = questions;
    }

    public void setAnswerChoices(ArrayList<String> answerChoices) {
        this.answerChoices = answerChoices;
    }

    public void setCorrectAnswers(ArrayList<String> correctAnswers) {
        this.correctAnswers = correctAnswers;
    }


    public static void updateArrayList() {
        File quizListFile = new File("src/quizList.txt");
        if (quizListFile.exists()) {
            quizzes = readFile("src/quizList.txt");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Quiz Name: ").append(this.getQuizName());
        sb.append("\nRandomized (true or false): ").append(this.isRandomized());
        int questionCounter = 0;
        int answerChoiceCounter = 0;
        for (String question : this.getQuestions()) {
            sb.append("\nQuestion ").append(questionCounter + 1).append(": ").append(question);
            sb.append("\nAnswer Choice 1: ").append(this.getAnswerChoices().get(answerChoiceCounter));
            sb.append("\nAnswer Choice 2: ").append(this.getAnswerChoices().get(answerChoiceCounter + 1));
            sb.append("\nAnswer Choice 3: ").append(this.getAnswerChoices().get(answerChoiceCounter + 2));
            sb.append("\nAnswer Choice 4: ").append(this.getAnswerChoices().get(answerChoiceCounter + 3));
            answerChoiceCounter += 4;
            sb.append("\nCorrect Answer: ").append(this.getCorrectAnswers().get(questionCounter)).append("\n");
            questionCounter++;
        }
        return "" + sb;
    }
}
