package com.GoJek.DTO;

/**
 * Created by masooda on 07/05/16.
 */
public class Car {
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    private String registrationNumber;
    private String color;

}
