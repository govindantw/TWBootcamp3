package org.example;

public class ParkingLotOwner {

    private boolean signIsUp;

    public ParkingLotOwner() {
        this.signIsUp = false;
    }

    public boolean hasPutUpSign() {
        return signIsUp;
    }

    protected void putUpSign() {
        this.signIsUp = true;
    }

    protected void putDownSign() {
        this.signIsUp = false;
    }

}
