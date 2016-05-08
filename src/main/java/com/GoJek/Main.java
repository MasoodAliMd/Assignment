package com.GoJek;

import com.GoJek.DTO.CommandLineUtility;

import java.io.InputStream;
import java.io.BufferedInputStream;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        CommandLineUtility commandLineUtility = new CommandLineUtility();

        Scanner terminalInput = new Scanner(System.in);
        String commandLine = "";
        while (true)
        {
            commandLine = terminalInput.nextLine();
            commandLineUtility.executeCommand(commandLine);
        }

    }
}
