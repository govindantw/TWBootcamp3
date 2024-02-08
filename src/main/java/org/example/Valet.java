package org.example;

public class Valet {


    public boolean parkIfPossible(Car car, ParkingStrategy parkingStrategy, ParkingLot... parkingLots) {

        return parkingStrategy.parkIfPossible(car, parkingLots);

    }


    public boolean unParkIfPossible(Car car, ParkingLot... parkingLots) {
        for (ParkingLot parkingLot : parkingLots) {
            if (parkingLot.hasCar(car)) {
                return parkingLot.removeCarIfPossible(car);
            }
        }
        return false;
    }


}
