package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTests {

    @Test
    public void canParkCarInEmptyParkingLot() {
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot(1);
        assertTrue(parkingLot.addCarIfPossible(car));
    }

    @Test
    public void cannotParkCarInFullParkingLot() {
        Car car1 = new Car();
        ParkingLot parkingLot = new ParkingLot(1);
        assertTrue(parkingLot.addCarIfPossible(car1));
        Car car2 = new Car();
        assertFalse(parkingLot.addCarIfPossible(car2));
    }

    @Test
    public void canParkMultipleCarsInParkingLotWithCapacity() {
        Car car1 = new Car();
        ParkingLot parkingLot = new ParkingLot(2);
        assertTrue(parkingLot.addCarIfPossible(car1));
        Car car2 = new Car();
        assertTrue(parkingLot.addCarIfPossible(car2));
    }

    @Test
    public void canUnparkTheirCarFromParkingLot(){
        Car car1 = new Car();
        ParkingLot parkingLot = new ParkingLot(1);
        assertTrue(parkingLot.addCarIfPossible(car1));

        assertTrue(parkingLot.removeCarIfPossible(car1));
    }

    @Test
    public void driverCannotUnparkCarTheyDidnotPark() {
        Driver driver = new Driver();
        Car car1 = new Car();
        ParkingLot parkingLot = new ParkingLot(2);
        assertTrue(driver.parkIfPossible(car1,parkingLot));
        Driver driver2 = new Driver();
        Car car2 = new Car();
        assertTrue(driver2.parkIfPossible(car2,parkingLot));
        assertFalse(driver2.unParkIfPossible(car1,parkingLot));
    }

}
