package com.GoJek.DTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by masooda on 07/05/16.
 */
public class ParkingLot {

    public List<Slot> getAvailableSlots() {
        return availableSlots;
    }

    public void setAvailableSlots(List<Slot> availableSlots) {
        this.availableSlots = availableSlots;
    }

    public ErrorHandler getErrorHandler() {
        return errorHandler;
    }

    public void setErrorHandler(ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }

    private List<Slot> availableSlots;
    private ErrorHandler errorHandler = new ErrorHandler(); // This is dummy right now and needs to be extended

    // constructor
    public ParkingLot(int n)
    {
        if(n>=0)
        {
            availableSlots = new ArrayList<Slot>(n);
            for(int i=1;i<=n;i++) {
                Slot slot = new Slot();
                slot.setSlotNumber(i);
                slot.setParked(false);
                availableSlots.add(slot);
            }
        }
        else
            errorHandler.log("No of slots has to be more than Zero");
    }

    public Ticket parkCar(Car car)
    {
        Slot slot = nextAvailableSlot();
        if(slot != null){
            slot.setParked(true);
            slot.setParkedCar(car);
        }
        Ticket ticket = new Ticket();
        ticket.setSlot(slot);
        return ticket;
    }

    public void leaveSlot(int n)
    {
         //get nth Slot
        for(Slot slot:availableSlots){
            if(slot.getSlotNumber()==n){
                // Release slot such that it can be used for the next car
                slot.setParked(false);
                slot.setParkedCar(null);
            }
        }
    }

    public String printStatus()
    {
        String str = String.format("%10s%20s%15s", "Slot No.", "Registration No", "Colour");
        for(Slot slot:availableSlots)
        {
            if(slot.isParked())
                str += "\n" + String.format("%10s%20s%15s", slot.getSlotNumber(), slot.getParkedCar().getRegistrationNumber(), slot.getParkedCar().getColor());
        }
        return str;
    }

    public String search(SearchCriteria searchCriteria)
    {
        String printString="";
        switch (searchCriteria.getCommand())
        {
            case slot_numbers_for_cars_with_colour :
                for(Slot slot:availableSlots){
                    if(slot.isParked())
                        if(slot.getParkedCar().getColor().equalsIgnoreCase(searchCriteria.getValue()))
                            printString = concatString(printString,String.valueOf(slot.getSlotNumber()));
                }
                if(printString.equalsIgnoreCase(""))
                    printString="Not found";
                break;

            case slot_number_for_registration_number :
                for(Slot slot:availableSlots){
                    if(slot.isParked())
                        if(slot.getParkedCar().getRegistrationNumber().equalsIgnoreCase(searchCriteria.getValue()))
                            printString = concatString(printString,String.valueOf(slot.getSlotNumber()));
                }
                if(printString.equalsIgnoreCase(""))
                    printString="Not found";
                break;

            case registration_numbers_for_cars_with_colour :
                for(Slot slot:availableSlots){
                    if(slot.isParked())
                        if(slot.getParkedCar().getColor().equalsIgnoreCase(searchCriteria.getValue()))
                            printString = concatString(printString,slot.getParkedCar().getRegistrationNumber());
                }
                if(printString.equalsIgnoreCase(""))
                    printString="Not found";
                break;

        }
        return printString;
    }

    private String concatString(String src, String value)
    {
        if(src.equalsIgnoreCase(""))
            src = value;
        else
            src = src + ", " + value;

        return src;
    }

    private Slot nextAvailableSlot()
    {
        if(availableSlots!=null)
        {
            for(Slot slot:availableSlots)
            {
                if(!slot.isParked())
                {
                    return slot;
                }
            }
            return null;
        }
        else
            errorHandler.log("No slots are allocated");

        return null;

    }

}
