package org.example;

public class ParkingLotOwner {

    private Runnable putUpSign;
    private Runnable putDownSign;

    public ParkingLotOwner() {
    }
    public ParkingLotOwner(Runnable putUpSign, Runnable putDownSign) {
        this.putUpSign = putUpSign;
        this.putDownSign = putDownSign;
    }

    public void putUpSign() {
        putUpSign.run();
    }

    public void putDownSign() {
        putDownSign.run();
    }

}
