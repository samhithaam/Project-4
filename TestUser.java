package com.project04;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import java.io.*;

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

    @Test(timeout = 100000000)
    public void testExitCondition() {
        String input = "5\n";
        receiveInput(input);
        try {
            User.main(new String[0]);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Error!!!");
        }
        String out = getOutput();
        System.out.println(out);

        String expectedFull = "What would you like to do? \n1. Create an account \n2. Edit an account" +
                "\n3. Delete an account\n4. Sign in\n5. Exit.\nExiting!!!";
        assertEquals("Checking exit condition", expectedFull, out);
    }
}