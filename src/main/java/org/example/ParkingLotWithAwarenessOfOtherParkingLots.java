package org.example;

public class ParkingLotWithAwarenessOfOtherParkingLots implements ParkingLot {

    ParkingLot parkingLot;
    ParkingLot[] otherParkingLots;

    public ParkingLotWithAwarenessOfOtherParkingLots(ParkingLot parkingLot, ParkingLot... otherParkingLots) {
        this.parkingLot = parkingLot;
        this.otherParkingLots = otherParkingLots;
    }

    @Override
    public boolean isAtMaxCapacity() {
        return parkingLot.isAtMaxCapacity();
    }

    @Override
    public boolean addCarIfPossible(Car car) {
        for(ParkingLot otherParkingLot : otherParkingLots) {
            if(otherParkingLot.hasCar(car)) {
                return false;
            }
        }
        return parkingLot.addCarIfPossible(car);
    }

    @Override
    public boolean removeCarIfPossible(Car car) {
        return parkingLot.removeCarIfPossible(car);
    }

    @Override
    public void putUpSign() {
        parkingLot.putUpSign();
    }

    @Override
    public void putDownSign() {
        parkingLot.putDownSign();
    }

    @Override
    public boolean hasPutUpSign() {
        return parkingLot.hasPutUpSign();
    }

    @Override
    public boolean hasCar(Car car) {
        return parkingLot.hasCar(car);
    }
}
