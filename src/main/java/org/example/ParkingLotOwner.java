package org.example;

public class ParkingLotOwner {

    private final Runnable putUpSign;
    private final Runnable putDownSign;

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
