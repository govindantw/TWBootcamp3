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
}
