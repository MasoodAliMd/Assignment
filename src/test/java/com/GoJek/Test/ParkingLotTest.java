package com.GoJek.Test;

import com.GoJek.DTO.*;
import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * Created by masooda on 09/05/16.
 */
public class ParkingLotTest extends TestCase {
    TestHelper testHelper = new TestHelper();

    public void testParkCar() throws Exception {
        //Prepare Data
        ParkingLot parkingLot = testHelper.getParkingLot();
        Car car = new Car();
        car.setRegistrationNumber("AP28J2811J");
        car.setColor("grey");

        //Make call
        Ticket ticket = parkingLot.parkCar(car);

        //Assertions
        assertNotNull(ticket.getSlot());
        assertNotNull(ticket.getSlot().getParkedCar());
        assertTrue(ticket.getSlot().isParked());
    }

    public void testParkCarNegative() throws Exception {
        //Prepare Data
        ParkingLot parkingLot = testHelper.getParkingLot();
        Car car = new Car();
        car.setRegistrationNumber("AP28J2811J");
        car.setColor("grey");

        //Make call
        Ticket ticket = new Ticket();
        for(int i=1;i<=7;i++)
             ticket = parkingLot.parkCar(car);
        ticket = parkingLot.parkCar(car); // Now the car should not allocate, as it is already full

        //Assertions
        assertNull(ticket.getSlot());
    }

    public void testLeaveSlot() throws Exception {
        ParkingLot parkingLot = testHelper.getParkingLot();
        Car car = new Car();
        car.setRegistrationNumber("AP28J2811J");
        car.setColor("grey");

        try {
            parkingLot.leaveSlot(1);
            assertTrue(true);
        }
        catch (Exception e)
        {
            assertTrue(false);
        }

    }

    public void testPrintStatus() throws Exception {
        ParkingLot parkingLot = testHelper.getParkingLot();
        Car car = new Car();
        car.setRegistrationNumber("AP28J2811J");
        car.setColor("grey");
        Ticket ticket = new Ticket();
        for(int i=1;i<=4;i++)
            ticket = parkingLot.parkCar(car);

        String printStr = parkingLot.printStatus();
        assertTrue(printStr.contains("AP28J2811J"));
        assertTrue(printStr.contains("grey"));
    }

    public void testSearch() throws Exception {
        ParkingLot parkingLot = testHelper.getParkingLot();
        Car car = new Car();
        car.setRegistrationNumber("AP28J2811J");
        car.setColor("grey");
        Ticket ticket = new Ticket();
        for(int i=1;i<=4;i++)
            ticket = parkingLot.parkCar(car);

        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setCommand(CommandLineUtility.Command.registration_numbers_for_cars_with_colour);
        searchCriteria.setValue("grey");

        String printStr = parkingLot.search(searchCriteria);
        assertTrue(printStr.contains("AP28J2811J, AP28J2811J"));

        SearchCriteria searchCriteria1 = new SearchCriteria();
        searchCriteria1.setCommand(CommandLineUtility.Command.slot_number_for_registration_number);
        searchCriteria1.setValue("AP28J2811J");

        String printStr1 = parkingLot.search(searchCriteria1);
        assertTrue(printStr1.contains("1, 2"));
    }
}