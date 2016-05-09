package com.GoJek.DTO;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by masooda on 08/05/16.
 */
public class CommandLineUtility {
    //Load Commands

    public enum Command {create_parking_lot, park, leave, status, exit, registration_numbers_for_cars_with_colour, slot_numbers_for_cars_with_colour, slot_number_for_registration_number, batch_file}
    private HashMap<Command, Integer> hashMap;
    private ParkingLot _parkingLot;
    private ErrorHandler _errorHandler;
    private OutputHandler _outputHandler;


    public CommandLineUtility(ParkingLot parkingLot, ErrorHandler errorHandler, OutputHandler outputHandler)
    {
        hashMap = loadHashMap();
        _parkingLot = parkingLot;

        // initialize Error Handler
        if(errorHandler!=null)
             _errorHandler = errorHandler;
        else
            _errorHandler = new ErrorHandler();

        // Initialize Output handler
        if(outputHandler!=null)
            _outputHandler = outputHandler;
        else
            _outputHandler = new OutputHandler();

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
        hashMap.put(Command.batch_file,3);
        hashMap.put(Command.exit, 0);
        return hashMap;
    }

    private String[] parseCommand(String expression)
    {
        if(expression.contains(">"))
            expression = "batch_file " + expression; // preparing for batch_file command - TODO Dirty Code - need to be rewritten
        String[] args = expression.split(" ");
        String command = args[0];
        // Check if it is a valid command
        try{
           Command.valueOf(command);
        }
        catch (IllegalArgumentException e)
        {
            //Command Does not exist
            _errorHandler.log("Cannot convert command with ");
            return null;
        }

        // Check if arguments passed are correct
        int value = hashMap.get(Command.valueOf(command));
        if(value!=args.length-1)
        {
            //No of arguments differ
            _errorHandler.log("Arguments supplied is not according to manual");
            return null;
        }
        return args;
    }

    public void executeCommand(String expression)
    {
        String[] commandLine = parseCommand(expression);
        if(commandLine == null)
        {
            // We got error while parsing
            return;
        }

        switch (Command.valueOf(commandLine[0]))
        {
            case create_parking_lot:
                try {
                    if(_parkingLot==null) {
                        _parkingLot = new ParkingLot(Integer.parseInt(commandLine[1]));
                        _outputHandler.output("Created a parking lot with "+ commandLine[1] +" slots");
                    }
                    else
                        _outputHandler.output("Parking lot already created");

                }
                catch(Exception e)
                {
                    _errorHandler.log("Error: Cannot create parking lot");
                }

                break;

            case park:
                try {
                    if(_parkingLot!=null) {
                        Car car = new Car();
                        car.setColor(commandLine[2]);
                        car.setRegistrationNumber(commandLine[1]);

                        Ticket ticket = _parkingLot.parkCar(car);
                        if(ticket.getSlot() == null)
                            _outputHandler.output("Sorry, parking lot is full");
                        else
                            _outputHandler.output("Allocated slot number: "+ticket.getSlot().getSlotNumber());
                    }
                    else
                        _outputHandler.output("Parking lot not created");

                }
                catch(Exception e)
                {
                    _errorHandler.log("Error: Cannot create parking Ticket");
                }
                break;

            case leave:
                try {
                    if(_parkingLot!=null) {
                        int slotNumber = Integer.parseInt(commandLine[1]);
                        _parkingLot.leaveSlot(slotNumber);
                        _outputHandler.output("Slot number "+slotNumber+" is free");
                    }
                    else
                        _outputHandler.output("Parking lot seem to have some problem");

                }
                catch(Exception e)
                {
                    _errorHandler.log("Error: Cannot create leave slot");
                }
                break;

            case status:
                try {
                    if(_parkingLot!=null) {
                        _outputHandler.output(_parkingLot.printStatus());
                    }
                    else
                        _outputHandler.output("Parking lot not created");
                }
                catch(Exception e)
                {
                    _errorHandler.log("Error: Parking Lot not created");
                }
                break;

            case registration_numbers_for_cars_with_colour:
                SearchCriteria searchCriteria = new SearchCriteria();
                searchCriteria.setCommand(Command.registration_numbers_for_cars_with_colour);
                searchCriteria.setValue(commandLine[1]);
                _outputHandler.output(_parkingLot.search(searchCriteria));
                break;

            case slot_number_for_registration_number:
                SearchCriteria searchCriteria1 = new SearchCriteria();
                searchCriteria1.setCommand(Command.slot_number_for_registration_number);
                searchCriteria1.setValue(commandLine[1]);
                _outputHandler.output(_parkingLot.search(searchCriteria1));
                break;

            case slot_numbers_for_cars_with_colour:
                SearchCriteria searchCriteria2 = new SearchCriteria();
                searchCriteria2.setCommand(Command.slot_numbers_for_cars_with_colour);
                searchCriteria2.setValue(commandLine[1]);
                _outputHandler.output(_parkingLot.search(searchCriteria2));
                break;

            case batch_file:
                String input = commandLine[1].trim();
                String output = commandLine[3].trim();
                executeFileBatchCommand(input, output);
                break;

            case exit:
                System.exit(0);
                break;
        }
    }

    public void executeFileBatchCommand(String input, String output)
    {
        try {
            FileInputStream fis = new FileInputStream(input);
            Scanner scanner = new Scanner(fis);
            _outputHandler.setOutputToFile(true);
            while(scanner.hasNextLine()){
                executeCommand(scanner.nextLine());
                String lineSeparator = System.getProperty("line.separator");
                _outputHandler.output(lineSeparator+lineSeparator);
            }
            String out = _outputHandler.getOutputStr();
            System.out.println(out);
        }
        catch (FileNotFoundException ex)
        {
            _errorHandler.log("File Not Found");
            _outputHandler.output("File Not Found");
        }
        finally {
            _outputHandler.setOutputToFile(false);
            _outputHandler.setOutputStr("");
        }

    }

}
