import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import java.io.*;
import java.sql.Timestamp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TestUser {
    private final InputStream originalInput = System.in;
    private final PrintStream originalOutput = System.out;
    @SuppressWarnings("FieldCanBeLocal")
    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;

    @Before
    public void outputStart() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    private void receiveInput(String str) {
        testIn = new ByteArrayInputStream(str.getBytes());
        System.setIn(testIn);
    }

    private String getOutput() {
        return testOut.toString();
    }

    @After
    public void restoreInputAndOutput() {
        System.setIn(originalInput);
        System.setOut(originalOutput);
    }

    @Test(timeout = 1000000)
    public void testCreateUsers() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String uniqueId = String.valueOf(timestamp.getTime());
        String userName = "sam" + uniqueId;

        String input = "1\n1\n" + userName + "\nabc\n5\n";

        receiveInput(input);
        try {
            User.main(new String[0]);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Error!");
        }
        String out = getOutput();
        System.out.println(out);

        String expectedFull = "What would you like to do? \n1. Create an account \n2. Edit an account" +
                "\n3. Delete an account\n4. Sign in\n5. Exit.\n" +
                "Are you a student or a teacher? Enter (1) for student and (2) for teacher.\n" +
                "Enter a username: \n" + "Enter a password: \n" +
                "You have successfully created an account!\n" +
                "What would you like to do? \n1. Create an account \n2. Edit an account" +
                "\n3. Delete an account\n4. Sign in\n5. Exit.\n";
        assertEquals("Checking create condition", expectedFull, out);
    }

    @Test(timeout = 1000000)
    public void testEditUsers() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String uniqueId = String.valueOf(timestamp.getTime());
        String userName = "sam" + uniqueId;

        String input = "1\n2\n" + userName + "\nabc\n5\n";

        receiveInput(input);
        try {
            User.main(new String[0]);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Error!");
        }
        String out = getOutput();
        System.out.println(out);

        String expectedFull = "What would you like to do? \n1. Create an account \n2. Edit an account" +
                "\n3. Delete an account\n4. Sign in\n5. Exit.\n" +
                "Are you a student or a teacher? Enter (1) for student and (2) for teacher.\n" +
                "Enter a username: \n" + "Enter a password: \n" +
                "You have successfully created an account!\n" +
                "What would you like to do? \n1. Create an account \n2. Edit an account" +
                "\n3. Delete an account\n4. Sign in\n5. Exit.\n";
        assertEquals("Checking create condition", expectedFull, out);
    }

    @Test(timeout = 1000000)
    public void testDeleteUsers() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String uniqueId = String.valueOf(timestamp.getTime());
        String userName = "sam" + uniqueId;

        String input = "1\n3\n" + userName + "\nabc\n5\n";

        receiveInput(input);
        try {
            User.main(new String[0]);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Error!");
        }
        String out = getOutput();
        System.out.println(out);

        String expectedFull = "What would you like to do? \n1. Create an account \n2. Edit an account" +
                "\n3. Delete an account\n4. Sign in\n5. Exit.\n" +
                "Are you a student or a teacher? Enter (1) for student and (2) for teacher.\n" +
                "Enter a username: \n" + "Enter a password: \n" +
                "You have successfully created an account!\n" +
                "What would you like to do? \n1. Create an account \n2. Edit an account" +
                "\n3. Delete an account\n4. Sign in\n5. Exit.\n";
        assertEquals("Checking create condition", expectedFull, out);
    }

    @Test(timeout = 1000000)
    public void testSignIn() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String uniqueId = String.valueOf(timestamp.getTime());
        String userName = "sam" + uniqueId;

        String input = "1\n4\n" + userName + "\nabc\n5\n";

        receiveInput(input);
        try {
            User.main(new String[0]);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Error!");
        }
        String out = getOutput();
        System.out.println(out);

        String expectedFull = "What would you like to do? \n1. Create an account \n2. Edit an account" +
                "\n3. Delete an account\n4. Sign in\n5. Exit.\n" +
                "Are you a student or a teacher? Enter (1) for student and (2) for teacher.\n" +
                "Enter a username: \n" + "Enter a password: \n" +
                "You have successfully created an account!\n" +
                "What would you like to do? \n1. Create an account \n2. Edit an account" +
                "\n3. Delete an account\n4. Sign in\n5. Exit.\n";
        assertEquals("Checking create condition", expectedFull, out);
    }

    @Test(timeout = 1000000)
    public void testExitCondition() {
        String input = "5\n";
        receiveInput(input);
        try {
            User.main(new String[0]);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Error!");
        }
        String out = getOutput();
        System.out.println(out);

        String expectedFull = "What would you like to do? \n1. Create an account \n2. Edit an account" +
                "\n3. Delete an account\n4. Sign in\n5. Exit.\n";
        assertEquals("Checking exit condition", expectedFull, out);
    }
}
