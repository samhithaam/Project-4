import java.io.*;
import java.util.ArrayList;

public class Quiz {

    private static ArrayList<Quiz> quizzes = new ArrayList<>();
    private String quizName;
    private boolean randomized;
    private ArrayList<String> questions;
    private ArrayList<String> answerChoices;
    private ArrayList<String> correctAnswers;

    public static void main (String [] args) {
        quizzes = readFile("src/quizInput.txt");
        if (quizzes == null || quizzes.size() == 0) {
            System.out.println("Error reading from file.");
        } else {
            if (!writeFile(quizzes, "src/quizOutput.txt")) {
                System.out.println("Error writing to file.");
            } else {
                System.out.println("File was written to!");
            }
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
        quizzes.add(new Quiz(quizName, randomized, questions, answerChoices, correctAnswers));
    }

    public static ArrayList<Quiz> readFile(String fileName) {
        try (BufferedReader bfr = new BufferedReader(new FileReader(fileName))) {
            ArrayList<String> fileContents = new ArrayList<>();
            String line = new String("");
            while ((line = bfr.readLine()) != null) {
                fileContents.add(line);
            }

            ArrayList<Integer> indexOfY_N = new ArrayList<>();
            for (int i = 0; i < fileContents.size(); i++) {
                if (fileContents.get(i).equalsIgnoreCase("y") || fileContents.get(i).equalsIgnoreCase("n")) {
                    indexOfY_N.add(i);
                }
            }

            ArrayList<Quiz> quizzesLocal = new ArrayList<>();
            int indexValue;
            boolean randomizedLocal;
            for (int i = 0; i < indexOfY_N.size(); i++) {
                while ((i + 1) < indexOfY_N.size()) {
                    indexValue = indexOfY_N.get(i);
                    ArrayList<String> answerChoicesLocal = new ArrayList<>();
                    ArrayList<String> questionsLocal = new ArrayList<>();
                    ArrayList<String> correctAnswersLocal = new ArrayList<>();

                    randomizedLocal = fileContents.get(indexValue).equalsIgnoreCase("y");

                    int questionIndex = indexValue + 1;
                    while (questionIndex < indexOfY_N.get(i + 1)) {
                        questionsLocal.add(fileContents.get(questionIndex));
                        questionIndex += 6;
                    }

                    int answerChoicesIndex = indexValue + 2;
                    while ((answerChoicesIndex + 3) < indexOfY_N.get(i + 1)) {
                        answerChoicesLocal.add(fileContents.get(answerChoicesIndex));
                        answerChoicesLocal.add(fileContents.get(answerChoicesIndex + 1));
                        answerChoicesLocal.add(fileContents.get(answerChoicesIndex + 2));
                        answerChoicesLocal.add(fileContents.get(answerChoicesIndex + 3));
                        answerChoicesIndex += 3;
                    }

                    int correctAnswerIndex = indexValue + 6;
                    while (correctAnswerIndex < indexOfY_N.get(i + 1)) {
                        correctAnswersLocal.add(fileContents.get(correctAnswerIndex));
                        correctAnswerIndex += 6;
                    }
                    quizzesLocal.add(new Quiz(fileContents.get(indexValue - 1), randomizedLocal, questionsLocal, answerChoicesLocal, correctAnswersLocal));
                }
            }

            indexValue = indexOfY_N.get(indexOfY_N.size() - 1);
            ArrayList<String> answerChoicesLocal = new ArrayList<>();
            ArrayList<String> questionsLocal = new ArrayList<>();
            ArrayList<String> correctAnswersLocal = new ArrayList<>();

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
                answerChoicesIndex += 3;
            }

            int correctAnswerIndex = indexValue + 6;
            while (correctAnswerIndex < fileContents.size()) {
                correctAnswersLocal.add(fileContents.get(correctAnswerIndex));
                correctAnswerIndex += 6;
            }

            quizzesLocal.add(new Quiz(fileContents.get(indexValue - 1), randomizedLocal, questionsLocal, answerChoicesLocal, correctAnswersLocal));
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

    private static boolean writeFile(ArrayList<Quiz> quizzes, String fileName) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))) {
            for (Quiz quiz : quizzes) {
                pw.println(quiz.toString());
            }
            pw.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
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
            sb.append("\nQuestion ").append(questionCounter).append(": ").append(question);
            questionCounter++;
            sb.append("\nAnswer Choice 1: ").append(this.getAnswerChoices().get(answerChoiceCounter));
            sb.append("\nAnswer Choice 2: ").append(this.getAnswerChoices().get(answerChoiceCounter + 1));
            sb.append("\nAnswer Choice 3: ").append(this.getAnswerChoices().get(answerChoiceCounter + 2));
            sb.append("\nAnswer Choice 4: ").append(this.getAnswerChoices().get(answerChoiceCounter + 3));
            answerChoiceCounter += 4;
            sb.append("\nCorrect Answer: ").append(this.getCorrectAnswers().get(questionCounter)).append("\n");
        }
        return "" + sb;
    }
}