import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * User.java
 *
 * If user creates an account and then logs out, they are still able to log back in
 * as data is stored in text files.
 *
 * @author Ritu Atreyas and Samhitha Mupharaphu
 * @version 11/14/2021
 */

public class User {

    // variables are private and static
    private static ArrayList<String> studentUsernames = new ArrayList<>();
    private static ArrayList<String> studentPasswords = new ArrayList<>();
    private static ArrayList<String> teacherUsernames = new ArrayList<>();
    private static ArrayList<String> teacherPasswords = new ArrayList<>();

    // calls the appropriate method
    public static void main(String[] args) throws Exception {
        readAllFiles(); // updates contents of ArrayList if there is data that is stored in text file
        Scanner scan = new Scanner(System.in);
        boolean notNum = true;
        boolean exit = false;
        int choice = 0;
        do {
            notNum = true;
            choice = 0;
            // following lines of code are included to make sure user inputs a number
            while (notNum) {
                try {
                    System.out.println("\nWhat would you like to do? \n1. Create an account \n2. Edit an account" +
                            "\n3. Delete an account\n4. Sign in\n5. Exit.");
                    choice = Integer.parseInt(scan.nextLine());
                    notNum = false;
                } catch (NumberFormatException e) {
                    System.out.println("You didn't enter a number! Try again.");
                }
            }
            if (choice == 1) {
                createAccount(scan);
            } else if (choice == 2) {
                editAccount(scan);
            } else if (choice == 3) {
                deleteAccount(scan);
            } else if (choice == 4) {
                signIn(scan);
            } else if (choice == 5) {
                exit = true;
            } else {
                System.out.println("Invalid input. Please try again!"); // handles invalid input
            }
        } while (!exit); // loops until user enters 5
    }

    public static void createAccount(Scanner scan) {
        int teacherOrStudent = 0;
        boolean notNum = true;
        // following lines of code are included to make sure user inputs a number
        while (notNum) {
            try {
                System.out.println("Are you a student or a teacher? Enter (1) for student and (2) for teacher.");
                teacherOrStudent = Integer.parseInt(scan.nextLine());
                notNum = false;
            } catch (NumberFormatException e) {
                System.out.println("You didn't enter a number! Try again.");
            }
        }

        boolean validInput = false;
        while (!validInput) {
            if (teacherOrStudent == 1) {
                validInput = true;
                boolean addSuccess = false;
                while (true) {
                    System.out.print("Enter a username: ");
                    String user = scan.nextLine();
                    // check whether ArrayList is null to avoid NullPointerException
                    if (studentUsernames != null && studentUsernames.size() > 0) {
                        for (String username : studentUsernames) {
                            if (username.equals(user)) {
                                System.out.println("\nUsername already exists, please try again.");
                            } else {
                                studentUsernames.add(user);
                                addSuccess = true;
                                break;
                            }
                        }
                        if (addSuccess) {
                            break;
                        }
                    } else {
                        // can add without checking whether username already exists
                        // if size of studentUsernames ArrayList is 0
                        if (studentUsernames != null) {
                            studentUsernames.add(user);
                        }
                        break;
                    }
                }

                System.out.print("\nEnter a password: ");
                String pass = scan.nextLine();
                studentPasswords.add(pass);

                System.out.println("\nYou have successfully created an account!");

            } else if (teacherOrStudent == 2) {
                validInput = true;
                boolean addSuccess = false;
                while (true) {
                    System.out.println("Enter a username: ");
                    String user = scan.nextLine();
                    if (teacherUsernames != null && teacherUsernames.size() > 0) {
                        for (String username : teacherUsernames) {
                            if (username.equals(user)) {
                                System.out.println("Username already exists, please try again.");
                            } else {
                                teacherUsernames.add(user);
                                addSuccess = true;
                                break;
                            }
                        }
                        if (addSuccess) {
                            break;
                        }
                    } else {
                        if (teacherUsernames != null) {
                            teacherUsernames.add(user);
                        }
                        break;
                    }
                }

                System.out.print("Enter a password: ");
                String pass = scan.nextLine();
                teacherPasswords.add(pass);

                System.out.println("\nYou have successfully created an account!");
            } else {
                System.out.println("Invalid input! Try again.");
                notNum = true;
                // following lines of code are included to make sure user inputs a number
                while (notNum) {
                    try {
                        System.out.println("Are you a student or a teacher? Enter (1) for student and (2) for teacher.");
                        teacherOrStudent = Integer.parseInt(scan.nextLine());
                        notNum = false;
                    } catch (NumberFormatException e) {
                        System.out.println("You didn't enter a number! Try again.");
                    }
                }
            }
        }
        // update files
        writeFile(teacherUsernames, "src/TeacherUsernames.txt");
        writeFile(studentUsernames, "src/StudentUsernames.txt");
        writeFile(studentPasswords, "src/StudentPasswords.txt");
        writeFile(teacherPasswords, "src/TeacherPasswords.txt");
    }

    // if a specific username is stored at index 0, the corresponding password is stored at index 0
    // the method stores index value of username being removed, and removes the element at that index value from
    // the passwords ArrayList
    public static void editAccount(Scanner scan) {
        boolean notNum = true;
        int teacherOrStudent = 0;
        // following lines of code are included to make sure user inputs a number
        while (notNum) {
            try {
                System.out.println("Are you a student or a teacher? Enter (1) for student and (2) for teacher.");
                teacherOrStudent = Integer.parseInt(scan.nextLine());
                notNum = false;
            } catch (NumberFormatException e) {
                System.out.println("You didn't enter a number! Try again.");
            }
        }

        boolean usernameExists;
        String oldUser;
        int counter;
        int index = 0;
        if (teacherOrStudent == 1) {

            usernameExists = false;
            System.out.print("Enter the username of the account you would like to edit: ");
            oldUser = scan.nextLine();
            if (studentUsernames != null && studentUsernames.size() > 0) {
                counter = 0;
                for (String username : studentUsernames) {
                    if (username.equals(oldUser)) {
                        usernameExists = true;
                        index = counter; // index of oldUser
                        break;
                    }
                    counter++;
                }
                if (!usernameExists) {
                    System.out.println("An account with the username you entered does not exist.");
                    return;
                }
            } else { // if the ArrayList is null or the length is 0
                System.out.println("An account with the username you entered does not exist.");
                return;
            }

            System.out.print("Enter the new username you would to like to replace the old username with: ");
            String newUser = scan.nextLine();

            studentUsernames.remove(oldUser);
            studentPasswords.remove(index);
            studentUsernames.add(newUser);
            System.out.print("\nEnter the password you would to like to replace the old password with: ");
            String newPass = scan.nextLine();

            studentPasswords.add(newPass);

            System.out.println("You have successfully edited the account!");
        } else if (teacherOrStudent == 2) {
                usernameExists = false;
                System.out.print("Enter the username of the account you would like to edit: ");
                oldUser = scan.nextLine();
                if (teacherUsernames != null && teacherUsernames.size() > 0) {
                    counter = 0;
                    index = 0;
                    for (String username : teacherUsernames) {
                        if (username.equals(oldUser)) {
                            usernameExists = true;
                            index = counter; // index of oldUser
                            break;
                        }
                        counter++;
                    }
                    if (!usernameExists) {
                        System.out.println("An account with the username you entered does not exist.");
                        return;
                    }
                } else {
                    System.out.println("An account with the username you entered does not exist.");
                    return;
                }

            System.out.println("Enter the new username you would to like to replace the old username with: ");
            String newUser = scan.nextLine();

            teacherUsernames.remove(oldUser);
            teacherPasswords.remove(index);
            teacherUsernames.add(newUser);
            System.out.println("Enter the password you would to like to replace the old password with: ");
            String newPass = scan.nextLine();

            teacherPasswords.add(newPass);

            System.out.println("You have successfully edited the account!");
        } else {
            System.out.println("Invalid input! Try again.");
            notNum = true;
            while (notNum) {
                try {
                    System.out.println("Are you a student or a teacher? Enter (1) for student and (2) for teacher.");
                    teacherOrStudent = Integer.parseInt(scan.nextLine());
                    notNum = false;
                } catch (NumberFormatException e) {
                    System.out.println("You didn't enter a number! Try again.");
                }
            }
        }
        writeFile(teacherUsernames, "src/TeacherUsernames.txt");
        writeFile(studentUsernames, "src/StudentUsernames.txt");
        writeFile(studentPasswords, "src/StudentPasswords.txt");
        writeFile(teacherPasswords, "src/TeacherPasswords.txt");
    }

    public static void deleteAccount(Scanner scan) {
        boolean notNum = true;
        int teacherOrStudent = 0;
        while (notNum) {
            try {
                System.out.println("Are you a student or a teacher? Enter (1) for student and (2) for teacher.");
                teacherOrStudent = Integer.parseInt(scan.nextLine());
                notNum = false;
            } catch (NumberFormatException e) {
                System.out.println("You didn't enter a number! Try again.");
            }
        }

        boolean usernameExists = false;
        String deletedUser;
        int counter;
        int index = 0;
        if (teacherOrStudent == 1) {
            System.out.print("Enter the username of the account you would like to delete: ");
            deletedUser = scan.nextLine();

            if (studentUsernames != null && studentUsernames.size() > 0) {
                counter = 0;
                for (String username : studentUsernames) {
                    if (username.equals(deletedUser)) {
                        usernameExists = true;
                        index = counter;
                        break;
                    }
                    counter++;
                }
                if (!usernameExists) {
                    System.out.println("An account with the username you entered does not exist.");
                    return;
                }
            } else {
                System.out.println("An account with the username you entered does not exist.");
                return;
            }

            studentUsernames.remove(deletedUser);
            studentPasswords.remove(index);

            System.out.println("You have successfully deleted the account!");
        } else if (teacherOrStudent == 2) {
            System.out.print("Enter the username of the account you would like to edit: ");
            deletedUser = scan.nextLine();

            if (teacherUsernames != null && teacherUsernames.size() > 0) {
                counter = 0;
                for (String username : teacherUsernames) {
                    if (username.equals(deletedUser)) {
                        usernameExists = true;
                        index = counter;
                        break;
                    }
                    counter++;
                }
                if (!usernameExists) {
                    System.out.println("An account with the username you entered does not exist.");
                    return;
                }
            } else {
                System.out.println("An account with the username you entered does not exist.");
                return;
            }

            teacherUsernames.remove(deletedUser);
            teacherPasswords.remove(index);

            System.out.println("You have successfully deleted the account!");
        } else {
            System.out.println("Invalid input! Try again.");
            notNum = true;
            while (notNum) {
                try {
                    System.out.println("Are you a student or a teacher? Enter (1) for student and (2) for teacher.");
                    teacherOrStudent = Integer.parseInt(scan.nextLine());
                    notNum = false;
                } catch (NumberFormatException e) {
                    System.out.println("You didn't enter a number! Try again.");
                }
            }
        }
        writeFile(teacherUsernames, "src/TeacherUsernames.txt");
        writeFile(studentUsernames, "src/StudentUsernames.txt");
        writeFile(studentPasswords, "src/StudentPasswords.txt");
        writeFile(teacherPasswords, "src/TeacherPasswords.txt");
    }


    // first checks if username exists in ArrayList, if yes, stores index
    // checks if element at that index in passwords ArrayList matches what the user entered as their password
    // if yes, sign in is successful
    public static void signIn(Scanner scan) throws IOException {
        boolean notNum = true;
        int teacherOrStudent = 0;
        while (notNum) {
            try {
                System.out.println("Are you a student or a teacher? Enter (1) for student and (2) for teacher.");
                teacherOrStudent = Integer.parseInt(scan.nextLine());
                notNum = false;
            } catch (NumberFormatException e) {
                System.out.println("You didn't enter a number! Try again.");
            }
        }

        boolean validInput = false;
        int counter;
        int indexUsername = 0;
        int indexPassword = 0;
        boolean usernameExists;
        while (!validInput) {
            if (teacherOrStudent == 1) {
                boolean signedIn;

                validInput = true;
                usernameExists = false;
                while (true) {
                    System.out.println("Enter a username: ");
                    String user = scan.nextLine();
                    if (studentUsernames != null && studentUsernames.size() > 0) {
                        counter = 0;
                        for (String username : studentUsernames) {
                            if (username.equals(user)) {
                                usernameExists = true;
                                indexUsername = counter;
                                break;
                            }
                            counter++;
                        }
                        if (!usernameExists) {
                            System.out.println("Username doesn't exist. Please try again!");
                            // that username doesn't exist but there are other usernames, therefore, give user
                            // the chance to try again
                        } else {
                            break;
                        }
                    } else {
                        System.out.println("No student users exist in the system!.");
                        // no usernames exist, no need to prompt user again
                        return;
                    }
                }
                signedIn = false;
                while (!signedIn) {
                    System.out.println("Enter your password: ");
                    String pass = scan.nextLine();
                    if (studentPasswords != null && studentPasswords.size() > 0) {
                        counter = 0;
                        for (String password : studentPasswords) {
                            if (pass.equals(password)) {
                                indexPassword = counter;
                                if (indexUsername == indexPassword) {
                                    System.out.println("You are signed in!");
                                    signedIn = true;
                                    Student.main(null);
                                } else {
                                    System.out.println("Error! Password doesn't match. Unable to sign in.");
                                }
                                break; // break out of for loop after calling main method in Student.java or
                                // printing error message
                            }
                            counter++;
                        }
                    }
                }
            } else if (teacherOrStudent == 2) {
                boolean signedIn;

                validInput = true;
                usernameExists = false;
                while (true) {
                    System.out.println("Enter a username: ");
                    String user = scan.nextLine();
                    if (teacherUsernames != null && teacherUsernames.size() > 0) {
                        counter = 0;
                        for (String username : teacherUsernames) {
                            if (username.equals(user)) {
                                usernameExists = true;
                                indexUsername = counter;
                                break;
                            }
                            counter++;
                        }
                        if (!usernameExists) {
                            System.out.println("Username doesn't exist. Please try again!");
                        } else {
                            break;
                        }
                    } else {
                        System.out.println("No teacher users exist in the system!");
                        return;
                    }
                }

                signedIn = false;
                while (!signedIn) {
                    System.out.println("Enter your password: ");
                    String pass = scan.nextLine();
                    if (teacherPasswords != null && teacherPasswords.size() > 0) {
                        counter = 0;
                        for (String password : teacherPasswords) {
                            if (pass.equals(password)) {
                                indexPassword = counter;
                                if (indexUsername == indexPassword) {
                                    System.out.println("You are signed in!");
                                    signedIn = true;
                                    Teacher.main(null);
                                } else {
                                    System.out.println("Error! Password doesn't match. Unable to sign in.");
                                }
                                break;
                            }
                            counter++;
                        }
                    }
                }
            } else {
                System.out.println("Invalid input! Try again.");
                notNum = true;
                while (notNum) {
                    try {
                        System.out.println("Are you a student or a teacher? Enter (1) for student and (2) for teacher.");
                        teacherOrStudent = Integer.parseInt(scan.nextLine());
                        notNum = false;
                    } catch (NumberFormatException e) {
                        System.out.println("You didn't enter a number! Try again.");
                    }
                }
            }
        }
    }

    private static void writeFile(ArrayList<String> list, String fileName) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))) {
            for (String line : list) {
                pw.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> readFile(String fileName) {
        try (BufferedReader bfr = new BufferedReader(new FileReader(fileName))) {
            ArrayList<String> fileContents = new ArrayList<>();
            String line = new String("");
            while ((line = bfr.readLine()) != null) {
                fileContents.add(line);
            }
            return fileContents;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList<String> getStudentUsernames() {
        return studentUsernames;
    }

    public static ArrayList<String> getStudentPasswords() {
        return studentPasswords;
    }

    public static ArrayList<String> getTeacherUsernames() {
        return teacherUsernames;
    }

    public static ArrayList<String> getTeacherPasswords() {
        return teacherPasswords;
    }

    private static void readAllFiles() {
        File studentUsernamesFile = new File("src/StudentUsernames.txt");
        File studentPasswordsFile = new File("src/StudentPasswords.txt");
        File teacherUsernamesFile = new File("src/TeacherUsernames.txt");
        File teacherPasswordsFile = new File("src/TeacherPasswords.txt");

        // ensure that files exist before calling readFile to avoid FileNotFoundException or other exceptions
        if (studentUsernamesFile.exists() && studentPasswordsFile.exists()) {
            studentUsernames = readFile("src/StudentUsernames.txt");
            studentPasswords = readFile("src/StudentPasswords.txt");
        }

        if (teacherUsernamesFile.exists() && teacherPasswordsFile.exists()) {
            teacherUsernames = readFile("src/TeacherUsernames.txt");
            teacherPasswords = readFile("src/TeacherPasswords.txt");
        }
    }
}
