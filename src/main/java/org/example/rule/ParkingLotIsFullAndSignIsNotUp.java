package org.example.rule;

import org.example.ParkingLot;
import org.example.ParkingLotOwner;

public class ParkingLotIsFullAndSignIsNotUp implements Rule {

    ParkingLot parkingLot;
    ParkingLotOwner owner;

    public ParkingLotIsFullAndSignIsNotUp(ParkingLot parkingLot, ParkingLotOwner owner) {
        this.parkingLot = parkingLot;
        this.owner = owner;
    }

    @Override
    public boolean isMet() {
        return parkingLot.isAtMaxCapacity() && ! parkingLot.hasPutUpSign();
    }

    @Override
    public void execute() {
        owner.putUpSign();
    }
}
