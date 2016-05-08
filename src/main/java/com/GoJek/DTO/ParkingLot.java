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
            availableSlots = new ArrayList<Slot>(n);
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
        return new Ticket();
    }

    public void leaveSlot(int n)
    {
         //get nth Slot
        Slot slot = availableSlots.get(n-1);
        // Release slot such that it can be used for the next car
        slot.setParked(false);
        slot.setParkedCar(null);
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
