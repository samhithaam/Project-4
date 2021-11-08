package com.project04;

import java.util.ArrayList;
import java.util.Scanner;

public class User {
    public static ArrayList<String> studentUsername = new ArrayList<>();
    public static ArrayList<String> studentPassword = new ArrayList<>();
    public static ArrayList<String> teacherUsername = new ArrayList<>();
    public static ArrayList<String> teacherPassword = new ArrayList<>();

    public static Scanner scan = new Scanner(System.in);

    public static void createAccount() throws Exception {
        System.out.println("Are you a student or teacher? (Enter 1 for Student and 2 for Teacher) ");
        int teacherOrStudent = scan.nextInt();
        scan.nextLine();

        if (teacherOrStudent == 1) {
            System.out.println("Enter a username: ");
            String user = scan.nextLine();
            try {
                if (user.equals(studentUsername)) {

                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Username already exists, please try again. ");
            }
            studentUsername.add(user);

            System.out.println("Enter a password: ");
            String pass = scan.nextLine();
            studentPassword.add(pass);

            System.out.println("You have successfully made an account!");

        } else if (teacherOrStudent == 2) {
            System.out.println("Enter a username: ");
            String user = scan.nextLine();
            try {
                if (user.equals(teacherUsername)) {

                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Username already exists, please try again. ");
            }
            teacherUsername.add(user);

            System.out.println("Enter a password: ");
            String pass = scan.nextLine();
            teacherPassword.add(pass);

            System.out.println("You have successfully made an account!");
        }
    }

    public static void editAccount() {
        System.out.println("Are you a student or teacher? (Enter 1 for Student and 2 for Teacher) ");
        int teacherOrStudent = scan.nextInt();
        scan.nextLine();

        if (teacherOrStudent == 1) {
            System.out.println("Enter the username of the account you would like to edit: ");
            String oldUser = scan.nextLine();
            System.out.println("Enter the new username of the account you would like to edit: ");
            String newUser = scan.nextLine();

            studentUsername.remove(oldUser);
            studentUsername.remove(newUser);

            System.out.println("Enter the password of the account you would like to edit: ");
            String oldPass = scan.nextLine();
            System.out.println("Enter the new password of the account you would like to edit: ");
            String newPass = scan.nextLine();

            studentUsername.remove(oldPass);
            studentUsername.remove(newPass);

            System.out.println("You have successfully edited the account!");

        } else if (teacherOrStudent == 2) {
            System.out.println("Enter the username of the account you would like to delete: ");
            String deletedTeacherUser = scan.nextLine();

            studentUsername.remove(deletedTeacherUser);

            System.out.println("Enter the password of the account you would like to delete: ");
            String deletedTeacherPass = scan.nextLine();
            studentPassword.add(deletedTeacherPass);

            System.out.println("You have successfully edited the account!");
        }
    }

    public static void deleteAccount () {
        System.out.println("Are you a student or teacher? (Enter 1 for Student and 2 for Teacher) ");
        int teacherOrStudent = scan.nextInt();
        scan.nextLine();

        if (teacherOrStudent == 1) {
            System.out.println("Enter the username of the account you would like to delete: ");
            String deletedStudentUser = scan.nextLine();

            studentUsername.remove(deletedStudentUser);

            System.out.println("Enter the username of the account you would like to delete: ");
            String deletedStudentPass = scan.nextLine();
            studentPassword.add(deletedStudentPass);

            System.out.println("You have successfully deleted the account!");

        } else if (teacherOrStudent == 2) {
            System.out.println("Enter the username of the account you would like to delete: ");
            String deletedTeacherUser = scan.nextLine();

            studentUsername.remove(deletedTeacherUser);

            System.out.println("Enter the password of the account you would like to delete: ");
            String deletedTeacherPass = scan.nextLine();
            studentPassword.add(deletedTeacherPass);

            System.out.println("You have successfully deleted the account!");
        }
    }

    public static void main (String[] args) throws Exception {
        System.out.println("What would you like to do? \n1. Create an account \n2. Edit an account\n3. Delete an account");
        int choice = scan.nextInt();
        scan.nextLine();

        do {
            if (choice == 1) {
                createAccount();
                break;
            } else if (choice == 2) {
                editAccount();
                break;
            } else if (choice == 3) {
                deleteAccount();
                break;
            } else {
                System.out.println("Input was invalid. Please try again!");
                choice = scan.nextInt();
                scan.nextLine();
            }
        } while (true);
    }
}
