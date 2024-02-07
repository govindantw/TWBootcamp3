package org.example;

public class ParkingLotOwner {

    private boolean signIsUp;

    public ParkingLotOwner() {
        this.signIsUp = false;
    }

    public boolean hasPutUpSign() {
        return signIsUp;
    }

    protected void parkingLotBecameFull() {
        this.signIsUp = true;
    }

    protected void parkingLotNoLongerFull() {
        this.signIsUp = false;
    }

}
