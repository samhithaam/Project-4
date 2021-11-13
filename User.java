import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

// TODO: Handle exceptions. Before adding anything to ArrayList, make sure it's not null.

public class User {

    // variables are private and static
    private static ArrayList<String> studentUsernames = new ArrayList<>();
    private static ArrayList<String> studentPasswords = new ArrayList<>();
    private static ArrayList<String> teacherUsernames = new ArrayList<>();
    private static ArrayList<String> teacherPasswords = new ArrayList<>();

    public static void main (String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        System.out.println("What would you like to do? \n1. Create an account \n2. Edit an account" +
                "\n3. Delete an account\n4. Sign in\n5. Exit.");
        int choice = scan.nextInt();
        scan.nextLine();
        // studentUsernames = readFile("src/StudentUsernames.txt");
        // studentPasswords = readFile("src/StudentPasswords.txt");
        // teacherUsernames = readFile("src/TeacherUsernames.txt");
        // teacherPasswords = readFile("src/TeacherPasswords.txt");

        do {
            if (choice == 1) {
                createAccount(scan);
            } else if (choice == 2) {
                editAccount(scan);
            } else if (choice == 3) {
                deleteAccount(scan);
            } else if (choice == 4) {
                signIn(scan);
            } else if (choice == 5) {
                break;
            } else {
                System.out.println("Input was invalid. Please try again!");
            }

            System.out.println("What would you like to do? \n1. Create an account \n2. Edit an account" +
                    "\n3. Delete an account\n4. Sign in\n5. Exit.");
            choice = scan.nextInt();
            scan.nextLine();
        } while (true);
    }

    public static void createAccount(Scanner scan) {
        System.out.println("Are you a student or a teacher? Enter (1) for student and (2) for teacher.");
        int teacherOrStudent = scan.nextInt();
        scan.nextLine();
        boolean validInput = false;

        while (!validInput) {
            if (teacherOrStudent == 1) {
                validInput = true;
                while (true) {
                    System.out.print("Enter a username: ");
                    String user = scan.nextLine();
                    if (studentUsernames != null && studentUsernames.size() > 0) {
                        for (String username : studentUsernames) {
                            if (username.equals(user)) {
                                System.out.println("\nUsername already exists, please try again.");
                            } else {
                                studentUsernames.add(user);
                                break;
                            }
                        }
                    } else {
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
                while (true) {
                    System.out.println("Enter a username: ");
                    String user = scan.nextLine();
                    if (teacherUsernames != null && teacherUsernames.size() > 0) {
                        for (String username : teacherUsernames) {
                            if (username.equals(user)) {
                                System.out.println("Username already exists, please try again.");
                            } else {
                                teacherUsernames.add(user);
                                break;
                            }
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
                teacherUsernames.add(pass);

                System.out.println("\nYou have successfully created an account!");
            } else {
                System.out.println("Invalid input! Please try again.\nAre you a student or a teacher? Enter (1) for student and (2) for teacher.");
                teacherOrStudent = scan.nextInt();
                scan.nextLine();
            }
        }
        writeFile(teacherUsernames, "src/TeacherUsernames.txt");
        writeFile(studentUsernames, "src/StudentUsernames.txt");
        writeFile(studentPasswords, "src/StudentPasswords.txt");
        writeFile(teacherPasswords, "src/TeacherPasswords.txt");

    } // DONE

    public static void editAccount(Scanner scan) {
        System.out.println("Are you a student or a teacher? Enter (1) for student and (2) for teacher.");
        int teacherOrStudent = scan.nextInt();
        scan.nextLine();

        boolean usernameExists;
        String oldUser;
        int counter;
        int index = 0;
        if (teacherOrStudent == 1) {
            while (true) {
                usernameExists = false;
                System.out.print(studentUsernames.toString());
                System.out.print("Enter the username of the account you would like to edit: ");
                oldUser = scan.nextLine();
                System.out.println(oldUser);
                if (studentUsernames != null && studentUsernames.size() > 0) {
                    counter = 0;
                    for (String username : studentUsernames) {
                        if (username.equals(oldUser)) {
                            usernameExists = true;
                            System.out.println(counter);
                            index = counter; // index of oldUser
                            System.out.println(index);
                            break;
                        }
                        counter++;
                    }
                    if (!usernameExists) {
                        System.out.println("An account with the username you entered does not exist, please try again.");
                    } else {
                        break;
                    }
                } else { // if the ArrayList is null or the length is 0
                    System.out.println("An account with the username you entered does not exist.");
                    break;
                }
            }

            System.out.println("Enter the new username you would to like to replace the old username with: ");
            String newUser = scan.nextLine();

            studentUsernames.remove(oldUser);
            studentPasswords.remove(index);
            studentUsernames.add(newUser);
            System.out.println("Enter the password you would to like to replace the old password with: ");
            String newPass = scan.nextLine();

            studentPasswords.add(newPass);

            System.out.println("You have successfully edited the account!");
        } else if (teacherOrStudent == 2) {
            while (true) {
                usernameExists = false;
                System.out.print("Enter the username of the account you would like to edit: ");
                oldUser = scan.nextLine();
                if (teacherUsernames != null && teacherUsernames.size() > 0) {
                    counter = 0;
                    index = 0;
                    for (String username : teacherUsernames) {
                        counter++;
                        if (username.equals(oldUser)) {
                            usernameExists = true;
                            index = counter; // index of oldUser
                            break;
                        }
                    }
                    if (!usernameExists) {
                        System.out.println("An account with the username you entered does not exist, please try again.");
                    } else {
                        break;
                    }
                } else {
                    System.out.println("An account with the username you entered does not exist.");
                }
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
        }
        writeFile(teacherUsernames, "src/TeacherUsernames.txt");
        writeFile(studentUsernames, "src/StudentUsernames.txt");
        writeFile(studentPasswords, "src/StudentPasswords.txt");
        writeFile(teacherPasswords, "src/TeacherPasswords.txt");
    }

    public static void deleteAccount (Scanner scan) {
        System.out.println("Are you a student or a teacher? Enter (1) for student and (2) for teacher.");
        int teacherOrStudent = scan.nextInt();
        scan.nextLine();

        boolean usernameExists = false;
        String deletedUser;
        int counter;
        int index = 0;
        if (teacherOrStudent == 1) {
            do {
                System.out.print("Enter the username of the account you would like to edit: ");
                deletedUser = scan.nextLine();

                if (studentUsernames != null && studentUsernames.size() > 0) {
                    counter = 0;
                    for (String username : studentUsernames) {
                        counter++;
                        if (username.equals(deletedUser)) {
                            usernameExists = true;
                            index = counter;
                            break;
                        }
                    }
                    if (!usernameExists) {
                        System.out.println("An account with the username you entered does not exist, please try again.");
                    } else {
                        break;
                    }
                } else {
                    System.out.println("An account with the username you entered does not exist.");
                    break;
                }
            } while (true);

            studentUsernames.remove(deletedUser);
            studentPasswords.remove(index);

            System.out.println("You have successfully deleted the account!");
        } else if (teacherOrStudent == 2) {
            do {
                System.out.print("Enter the username of the account you would like to edit: ");
                deletedUser = scan.nextLine();

                if (teacherUsernames != null && teacherUsernames.size() > 0) {
                    counter = 0;
                    for (String username : teacherUsernames) {
                        counter++;
                        if (username.equals(deletedUser)) {
                            usernameExists = true;
                            index = counter;
                            break;
                        }
                    }
                    if (!usernameExists) {
                        System.out.println("An account with the username you entered does not exist, please try again.");
                    } else {
                        break;
                    }
                } else {
                    System.out.println("An account with the username you entered does not exist.");
                    break;
                }
            } while (true);

            teacherUsernames.remove(deletedUser);
            teacherPasswords.remove(index);

            System.out.println("You have successfully deleted the account!");
        }
        writeFile(teacherUsernames, "src/TeacherUsernames.txt");
        writeFile(studentUsernames, "src/StudentUsernames.txt");
        writeFile(studentPasswords, "src/StudentPasswords.txt");
        writeFile(teacherPasswords, "src/TeacherPasswords.txt");
    }

    // TODO
    public static void signIn(Scanner scan) throws IOException {
        System.out.println("Are you a student or a teacher? Enter (1) for student and (2) for teacher.");
        int teacherOrStudent = scan.nextInt();
        scan.nextLine();

        boolean validInput = false;

        int counter;
        int indexUsername = 0;
        int indexPassword = 0;
        int tryAgain = 0;
        boolean usernameExists;
        while (!validInput) {
            if (teacherOrStudent == 1) {
                while (true) {
                    validInput = true;
                    usernameExists = false;
                    while (true) {
                        System.out.println("Enter a username: ");
                        String user = scan.nextLine();
                        if (studentUsernames != null && studentUsernames.size() > 0) {
                            counter = 0;
                            for (String username : studentUsernames) {
                                counter++;
                                if (username.equals(user)) {
                                    usernameExists = true;
                                    indexUsername = counter;
                                    break;
                                }
                            }
                            if (!usernameExists) {
                                System.out.println("Username doesn't exist. Please try again!");
                            } else {
                                break;
                            }
                        } else {
                            System.out.println("Username doesn't exist.");
                            break;
                        }
                    }

                    while (true) {
                        System.out.println("Enter your password: ");
                        String pass = scan.nextLine();
                        if (studentPasswords != null && studentPasswords.size() > 0) {
                            counter = 0;
                            for (String password : studentPasswords) {
                                counter++;
                                if (pass.equals(password)) {
                                    usernameExists = true;
                                    indexPassword = counter;
                                    break;
                                }
                            }
                            if (!usernameExists) {
                                System.out.println("Password doesn't exist. Please try again!");
                            } else {
                                break;
                            }
                        } else {
                            System.out.println("Username doesn't exist.");
                            break;
                        }
                    }

                    if (indexUsername == indexPassword) {
                        System.out.println("You are signed in!");
                        Student.main(null);
                        break;
                    } else {
                        System.out.println("Error! Unable to sign in. Press (1) to try again.");
                        tryAgain = scan.nextInt();
                        scan.nextLine();
                        if (tryAgain != 1) {
                            break;
                        }
                    }
                }
            } else if (teacherOrStudent == 2) {
                while (true) {
                    validInput = true;
                    usernameExists = false;
                    while (true) {
                        System.out.println("Enter a username: ");
                        String user = scan.nextLine();
                        if (teacherUsernames != null && teacherUsernames.size() > 0) {
                            counter = 0;
                            for (String username : teacherUsernames) {
                                counter++;
                                if (username.equals(user)) {
                                    usernameExists = true;
                                    indexUsername = counter;
                                    break;
                                }
                            }
                            if (!usernameExists) {
                                System.out.println("Username doesn't exist. Please try again!");
                            } else {
                                break;
                            }
                        } else {
                            System.out.println("Username doesn't exist.");
                            break;
                        }
                    }

                    while (true) {
                        System.out.println("Enter your password: ");
                        String pass = scan.nextLine();
                        if (teacherPasswords != null && teacherPasswords.size() > 0) {
                            counter = 0;
                            for (String password : teacherPasswords) {
                                counter++;
                                if (pass.equals(password)) {
                                    usernameExists = true;
                                    indexPassword = counter;
                                    break;
                                }
                            }
                            if (!usernameExists) {
                                System.out.println("Password doesn't exist. Please try again!");
                            }
                        } else {
                            System.out.println("Username doesn't exist.");
                            break;
                        }
                    }

                    if (indexUsername == indexPassword) {
                        System.out.println("You are signed in!");
                        Teacher.main(null);
                        break;
                    } else {
                        System.out.println("Error! Unable to sign in. Press (1) to try again.");
                        tryAgain = scan.nextInt();
                        scan.nextLine();
                        if (tryAgain != 1) {
                            break;
                        }
                    }
                }
            } else {
                System.out.println("Invalid input! Please try again.\nAre you a student or a teacher? Enter (1) for student and (2) for teacher.");
                teacherOrStudent = scan.nextInt();
                scan.nextLine();
            }
        }
    }

    private static boolean writeFile(ArrayList<String> list, String fileName) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))) {
            for (String line : list) {
                pw.println(line);
            }
            pw.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
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
}
