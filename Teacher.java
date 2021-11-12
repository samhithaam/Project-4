import java.util.Scanner;
import java.util.ArrayList;
public class Teacher {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean start = true;
        while (start) {
            System.out.println("[1]Create Quiz, [2]Edit Quiz, [3]Delete Quiz, [4]View Submissions, [5]Exit");
            int choice = scan.nextInt();
            scan.nextLine();
            ArrayList<String> currentQuiz = new ArrayList<String>();
            if (choice == 1) {
                boolean anotherQ = true;
                while (anotherQ) {
                    System.out.println("Enter quiz title");
                    String quizTitle = scan.nextLine();
                    currentQuiz.add(0, quizTitle);
                    System.out.println("Randomize Questions?");
                    String random = scan.nextLine();
                    currentQuiz.add(random);
                    System.out.println("Enter Question");
                    String question = scan.nextLine();
                    currentQuiz.add(question);
                    System.out.println("Enter Answer Choice 1");
                    String answer1 = scan.nextLine();
                    currentQuiz.add(answer1);
                    System.out.println("Enter Answer Choice 2");
                    String answer2 = scan.nextLine();
                    currentQuiz.add(answer2);
                    System.out.println("Enter Answer Choice 3");
                    String answer3 = scan.nextLine();
                    currentQuiz.add(answer3);
                    System.out.println("Enter Answer Choice 4");
                    String answer4 = scan.nextLine();
                    currentQuiz.add(answer4);
                    System.out.println("Enter Correct Answer Choice");
                    String answer = scan.nextLine();
                    currentQuiz.add(answer);
                    System.out.println("Do you want to enter another question?");
                    String keepGoing = scan.nextLine();
                    if (keepGoing.equalsIgnoreCase("No")) {
                        anotherQ = false;
                    }
                }
            }
            if (choice == 2) {
                System.out.println("Enter Quiz Title");
                String title = scan.nextLine();
                for (int i = 0; i < currentQuiz.size(); i++) {
                    if (currentQuiz.get(i).equals(title)) {
                        System.out.println("Do you want to edit the question?");
                        String editQuestion = scan.nextLine();
                        if (editQuestion.equalsIgnoreCase("Yes")) {
                            System.out.println("What is the new question?");
                            String editedQuestion = scan.nextLine();
                            currentQuiz.set(i + 2, editedQuestion);
                        }
                        System.out.println("Do you want to edit the randomization?");
                        String editRandom = scan.nextLine();
                        if (editRandom.equalsIgnoreCase("Y")) {
                            System.out.println("What is the new randomization option?");
                            String editedRandom = scan.nextLine();
                            currentQuiz.set(i + 1, editedRandom);
                        }
                        System.out.println("Do you want to edit the answer choices?");
                        String editAnswer = scan.nextLine();
                        if (editAnswer.equalsIgnoreCase("Yes")) {
                            System.out.println("Do you want to edit answer choice 1?");
                            String editAnswer1 = scan.nextLine();
                            if (editAnswer1.equalsIgnoreCase("Yes")) {
                                System.out.println("Enter new answer choice");
                                String newAnswer1 = scan.nextLine();
                                currentQuiz.set(i + 3, newAnswer1);
                            }
                            System.out.println("Do you want to edit answer choice 2?");
                            String editAnswer2 = scan.nextLine();
                            if (editAnswer2.equalsIgnoreCase("Yes")) {
                                System.out.println("Enter new answer choice");
                                String newAnswer2 = scan.nextLine();
                                currentQuiz.set(i + 4, newAnswer2);
                            }
                            System.out.println("Do you want to edit answer choice 3?");
                            String editAnswer3 = scan.nextLine();
                            if (editAnswer3.equalsIgnoreCase("Yes")) {
                                System.out.println("Enter new answer choice");
                                String newAnswer3 = scan.nextLine();
                                currentQuiz.set(i + 5, newAnswer3);
                            }
                            System.out.println("Do you want to edit answer choice 4?");
                            String editAnswer4 = scan.nextLine();
                            if (editAnswer4.equalsIgnoreCase("Yes")) {
                                System.out.println("Enter new answer choice");
                                String newAnswer4 = scan.nextLine();
                                currentQuiz.set(i + 6, newAnswer4);
                            }
                        }
                        System.out.println("Do you want to edit the correct answer choice?");
                        String editCorrectAnswer = scan.nextLine();
                        if (editCorrectAnswer.equalsIgnoreCase("Yes")) {
                            System.out.println("Enter new answer choice");
                            String newCorrectAnswer = scan.nextLine();
                            currentQuiz.set(i + 7, newCorrectAnswer);
                        }
                    }
                }
            }
            if (choice == 3) {
                System.out.println("Enter Quiz Title");
                String title = scan.nextLine();
                for (int i = 0; i < currentQuiz.size(); i++) {
                    if (currentQuiz.get(i).equalsIgnoreCase(title)) {
                        currentQuiz.remove(i);
                        currentQuiz.remove(i + 1);
                        currentQuiz.remove(i + 2);
                        currentQuiz.remove(i + 3);
                        currentQuiz.remove(i + 4);
                        currentQuiz.remove(i + 5);
                        currentQuiz.remove(i + 6);
                        currentQuiz.remove(i + 7);
                    }
                }
            }
            if (choice == 4) {
                for (int i = 0; i < Student.getStudentSubmissions().size(); i++) {
            }
            if (choice == 5) {
                start = false;
            }
        }
    }

}


