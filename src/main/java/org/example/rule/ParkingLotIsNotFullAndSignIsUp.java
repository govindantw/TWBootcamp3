package org.example.rule;

import org.example.ParkingLot;
import org.example.ParkingLotOwner;

public class ParkingLotIsNotFullAndSignIsUp implements Rule {

    ParkingLot parkingLot;
    ParkingLotOwner owner;

    public ParkingLotIsNotFullAndSignIsUp(ParkingLot parkingLot, ParkingLotOwner owner) {
        this.parkingLot = parkingLot;
        this.owner = owner;
    }

    @Override
    public boolean isMet() {
        return !parkingLot.isAtMaxCapacity() && parkingLot.hasPutUpSign();
    }

    @Override
    public void execute() {
        owner.putDownSign();
    }
}
