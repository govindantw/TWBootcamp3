package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTests {

    @Test
    public void driverCanParkCarInEmptyParkingLot() {
        Driver driver = new Driver();
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot(1);
        assertTrue(driver.parkIfPossible(car,parkingLot));
    }

    @Test
    public void driverCannotParkCarInFullParkingLot() {
        Driver driver = new Driver();
        Car car1 = new Car();
        ParkingLot parkingLot = new ParkingLot(1);
        assertTrue(driver.parkIfPossible(car1,parkingLot));
        Car car2 = new Car();
        assertFalse(driver.parkIfPossible(car2,parkingLot));
    }

    @Test
    public void driverCanParkMultipleCarsInParkingLotWithCapacity() {
        Driver driver = new Driver();
        Car car1 = new Car();
        ParkingLot parkingLot = new ParkingLot(2);
        assertTrue(driver.parkIfPossible(car1,parkingLot));
        Car car2 = new Car();
        assertTrue(driver.parkIfPossible(car2,parkingLot));
    }

}
