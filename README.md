# Project-4

/////////////who submitted what on vocareum

There are 4 classes, Student, Teacher, Quiz and User. 

STUDENT CLASS: The Student class handles everything that a Student account should able to do, mainly taking a quiz or viewing their grades on a quiz. In this class, there is also a method that handles randomizing the order of the questions and then printing the questions in that random order for each student. 

TEACHER CLASS: The Teacher class handles the creation of all the quizzes. In this class, you can also edit and delete questions of the quiz or entire quizzes. The grading of the quizzes is also handled in this class by assigning a certain number of points for each correct answer and then adding all the points together. 

QUIZ CLASS: The Quiz class handles storing all the quizzes and the questions in each of the quizzes. 

USER CLASS: The User class handles creating, editing, and deleting account usernames/passwords, as well as signing into the account. There are also readFile and writeFile methods in this class in order to store all the information about the usernames and passwords for the accounts. The main method to run our program is also in this class. From this class, the Student and Teacher main methods are called, based on what the user inputs as their identification for their account. 

Each of these classes works with each other in order create a working code that can create, edit and delete different accounts for students and teachers. The student and teacher accounts that are created have limitations on what they can do based on if it is a teacher or student account. Teacher accounts can create and assign quizzes, whereas the student account can only access and take the quizzes. There is also a randomization method in the student class, which gives the quiz to the student in a randomized order.

COMPILING AND RUNNING: In order to run our program, you need to run the User Class. This will start the program by prompting you to create/edit/delete an account or sign in to an account. After choosing one of the above options, you would be prompted to answer if you are a student or teacher. If the user chose to create/edit/delete an account that would prompt the code to store the update/inputted information about the account in the arraylist. If the user chose to sign into an account, the code would take the user to the Student or Teacher options list based on which account the user said they were. If the user is a student account, the user would be prompted to take a quiz or view their grade. If the user is a teacher account, then they would be prompted to create/edit/delete a quiz or grade a quiz. 

TESTING: We tested the code by trying various different inputs/answers to our prompt messages and seeing if all of our loops and messages properly printed. We also created a test case class for User in order to make sure that it properly works with a certain basic input. 
