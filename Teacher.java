import java.util.Scanner;
import java.util.ArrayList;
public class Teacher {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean start = true;
        if (start) {
            System.out.println("[1]Create Quiz, [2]Edit Quiz, [3]Delete Quiz, [4]View Submissions, [5]Exit");
            int choice = scan.nextInt();
            scan.nextLine();
            ArrayList <String> currentQuiz = new ArrayList<String>();
            if (choice == 1) {
                System.out.println("Enter quiz title");
                String quizTitle = scan.nextLine();
            }
            if (choice == 1) {
                System.out.println("Enter quiz title");
                String quizTitle = scan.nextLine();
                currentQuiz.add(0,quizTitle);
                boolean anotherQ = true;
                if (anotherQ) {
                    System.out.println("Enter Question");
                    String question = scan.nextLine();
                    currentQuiz.add(question);
                    boolean anotherA = true;
                    if (anotherA) {
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
                        anotherA = false;
                    }
                    System.out.println("Do you want to enter another question?");
                    String keepGoing = scan.nextLine();
                    if (keepGoing.equalsIgnoreCase("No")) {
                        anotherQ = false;
                    }
                }


            }
        }

    }

}
