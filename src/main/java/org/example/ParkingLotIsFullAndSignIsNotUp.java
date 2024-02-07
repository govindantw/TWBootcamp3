package org.example;

public class ParkingLotIsFullAndSignIsNotUp implements Rule {

    ParkingLot parkingLot;
    ParkingLotOwner owner;

    public ParkingLotIsFullAndSignIsNotUp(ParkingLot parkingLot, ParkingLotOwner owner) {
        this.parkingLot = parkingLot;
        this.owner = owner;
    }

    @Override
    public boolean isMet() {
        return parkingLot.isAtMaxCapacity() && ! owner.hasPutUpSign();
    }

    @Override
    public void execute() {
        owner.putUpSign();
    }
}
