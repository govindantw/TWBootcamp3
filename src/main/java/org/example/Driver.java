package org.example;

public class Driver {

    public boolean parkIfPossible(Car car, ParkingLot parkingLot) {
        return parkingLot.addCarIfPossible(car);
    }

}
