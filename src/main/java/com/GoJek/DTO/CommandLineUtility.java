package com.GoJek.DTO;

import java.util.HashMap;

/**
 * Created by masooda on 08/05/16.
 */
public class CommandLineUtility {
    //Load Commands
    private ErrorHandler errorHandler = new ErrorHandler();
    public enum Command {create_parking_lot, park, leave, status, exit, registration_numbers_for_cars_with_colour, slot_numbers_for_cars_with_colour, slot_number_for_registration_number}
    HashMap<Command, Integer> hashMap;

    public CommandLineUtility()
    {
        hashMap = loadHashMap();
    }

    private HashMap<Command, Integer> loadHashMap()
    {
        HashMap<Command, Integer> hashMap = new HashMap<Command, Integer>();
        hashMap.put(Command.create_parking_lot, 1);
        hashMap.put(Command.park, 2);
        hashMap.put(Command.leave, 1);
        hashMap.put(Command.status, 0);
        hashMap.put(Command.registration_numbers_for_cars_with_colour, 1);
        hashMap.put(Command.slot_numbers_for_cars_with_colour, 1);
        hashMap.put(Command.slot_number_for_registration_number, 1);
        hashMap.put(Command.exit, 0);
        return hashMap;

    }

    private String[] parseCommand(String expression)
    {
        String[] args = expression.split(" ");
        String command = args[0];
        // Check if it is a valid command
        try{
           Command.valueOf(command);
        }
        catch (IllegalArgumentException e)
        {
            //Command Does not exist
            errorHandler.log("Cannot convert command with ");
            return null;
        }

        // Check if arguments passed are correct
        int value = hashMap.get(Command.valueOf(command));
        if(value!=args.length-1)
        {
            //No of arguments differ
            errorHandler.log("Arguments supplied is not according to manual");
            return null;
        }


        return args;
    }

    public void executeCommand(String expression)
    {
        String[] commandLine = parseCommand(expression);
        if(commandLine == null)
        {
            // We get error while parsing
            return;
        }


        switch (Command.valueOf(commandLine[0]))
        {
            case create_parking_lot:
                System.out.println("create_parking_lot");
                break;
            case park:
                System.out.println("park");
                break;
            case leave:
                System.out.println("leave");
                break;
            case status:
                System.out.println("status");
                break;
            case registration_numbers_for_cars_with_colour:
                System.out.println("registration_numbers_for_cars_with_colour");
                break;
            case slot_number_for_registration_number:
                System.out.println("slot_number_for_registration_number");
                break;
            case slot_numbers_for_cars_with_colour:
                System.out.println("slot_numbers_for_cars_with_colour");
                break;
            case exit:
                System.out.println("exit");
                System.exit(0);
                break;
        }
    }

}
