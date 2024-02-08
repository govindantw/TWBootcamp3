package org.example;

public class Valet {

    public boolean parkIfPossible(Car car, ParkingLot... parkingLots) {
        for(ParkingLot parkingLot : parkingLots) {
            if(parkingLot.addCarIfPossible(car)) {
                return true;
            }
        }
        return false;
    }

    public boolean unParkIfPossible(Car car, ParkingLot... parkingLots) {
        for(ParkingLot parkingLot : parkingLots) {
            if(parkingLot.hasCar(car)) {
                return parkingLot.removeCarIfPossible(car);
            }
        }
        return false;
    }
}
