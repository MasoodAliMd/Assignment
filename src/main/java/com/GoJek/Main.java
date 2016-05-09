package com.GoJek;

import com.GoJek.DTO.CommandLineUtility;
import com.GoJek.DTO.ErrorHandler;
import com.GoJek.DTO.OutputHandler;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //Lets use our main program as an injection program, though we haven't implemented Ninject
        ErrorHandler errorHandler = new ErrorHandler();
        OutputHandler outputHandler = new OutputHandler();

        //ParkingLot is NULL as it will be initiated through command line - lazy load.
        CommandLineUtility commandLineUtility = new CommandLineUtility(null, errorHandler, outputHandler);

        Scanner terminalInput = new Scanner(System.in);
        String commandLine;
        while (true)
        {
            commandLine = terminalInput.nextLine();
            commandLineUtility.executeCommand(commandLine);
        }
    }
}
