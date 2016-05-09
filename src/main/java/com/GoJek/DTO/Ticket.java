package com.GoJek.DTO;

/**
 * Created by masooda on 07/05/16.
 */
public class Ticket {
    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }

    //Currently Ticket do not have much of functionality but creating it to extend
    private Slot slot;
}
