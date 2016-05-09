package com.GoJek.Test;

import com.GoJek.DTO.CommandLineUtility;
import junit.framework.TestCase;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Created by masooda on 09/05/16.
 */
public class CommandLineUtilityTest extends TestCase {


    private TestHelper testHelper = new TestHelper();

    public void testExecuteCommand_create_parking_lot() throws Exception {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        CommandLineUtility cli = testHelper.getCommandLineUtility();
        cli.executeCommand("create_parking_lot 6");

        assertEquals(outContent.toString(), "Created a parking lot with 6 slots\n");

    }

    public void testExecuteCommand_park() throws Exception {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        CommandLineUtility cli = testHelper.getCommandLineUtility();
        cli.executeCommand("create_parking_lot 6");
        cli.executeCommand("park KA-01-HH-1234 White");

        assertEquals(outContent.toString(), "Created a parking lot with 6 slots\n" + "Allocated slot number: 1\n");

    }

    public void testExecuteCommand_leave() throws Exception {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        CommandLineUtility cli = testHelper.getCommandLineUtility();
        cli.executeCommand("create_parking_lot 6");
        cli.executeCommand("park KA-01-HH-1234 White");
        cli.executeCommand("leave 4");

        assertEquals(outContent.toString(), "Created a parking lot with 6 slots\n" + "Allocated slot number: 1\nSlot number 4 is free\n1\n");

    }

    public void testExecuteCommand_search() throws Exception {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        CommandLineUtility cli = testHelper.getCommandLineUtility();
        cli.executeCommand("create_parking_lot 6");
        cli.executeCommand("park KA-01-HH-1234 White");
        cli.executeCommand("slot_numbers_for_cars_with_colour White");

        assertEquals(outContent.toString(), "Created a parking lot with 6 slots\n" + "Allocated slot number: 1\nSlot number 4 is free\n1\n");

    }

}