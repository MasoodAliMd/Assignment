package com.GoJek.Test;

import com.GoJek.DTO.CommandLineUtility;
import com.GoJek.DTO.ErrorHandler;
import com.GoJek.DTO.OutputHandler;
import com.GoJek.DTO.ParkingLot;

/**
 * Created by masooda on 09/05/16.
 */
public class TestHelper {
    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    private ParkingLot parkingLot;

    public CommandLineUtility getCommandLineUtility() {
        return commandLineUtility;
    }

    public void setCommandLineUtility(CommandLineUtility commandLineUtility) {
        this.commandLineUtility = commandLineUtility;
    }

    private CommandLineUtility commandLineUtility;
    public TestHelper(){

        parkingLot = new ParkingLot(7);
        ErrorHandler errorHandler = new ErrorHandler();
        OutputHandler outputHandler = new OutputHandler();
        commandLineUtility = new CommandLineUtility(null,errorHandler,outputHandler);
    }

}
