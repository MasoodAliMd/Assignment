package com.GoJek.DTO;

/**
 * Created by masooda on 07/05/16.
 */
public class Slot {
    private boolean parked;

    public Car getParkedCar() {
        return parkedCar;
    }

    public void setParkedCar(Car parkedCar) {
        this.parkedCar = parkedCar;
    }

    public boolean isParked() {
        return parked;
    }

    public void setParked(boolean parked) {
        this.parked = parked;
    }

    private Car parkedCar;
}
