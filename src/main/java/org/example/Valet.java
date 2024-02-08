package org.example;

public class Valet {

    public boolean parkIfPossible(Car car, ParkingStrategy parkingStrategy, ParkingLot... parkingLots) {
        ParkingLot selectedParkingLot =  parkingStrategy.parkIfPossible(car, parkingLots);
        if(selectedParkingLot == null) {
            return false;
        }
        return selectedParkingLot.addCarIfPossible(car);
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
