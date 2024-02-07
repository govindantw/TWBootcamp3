package org.example;

public class SignIsUpAndCarsAreAllowed implements Rule {

    ParkingLot parkingLot;
    ParkingLotSecurity security;

    public SignIsUpAndCarsAreAllowed(ParkingLot parkingLot, ParkingLotSecurity security) {
        this.parkingLot = parkingLot;
        this.security = security;
    }

    @Override
    public boolean isMet() {
        return parkingLot.hasPutUpSign() && ! security.carsAreRedirected();
    }

    @Override
    public void execute() {
        security.redirectCars();
    }
}
