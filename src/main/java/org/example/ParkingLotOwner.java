package org.example;

public class ParkingLotOwner {

    private Runnable putUpSign;
    private Runnable putDownSign;

    public ParkingLotOwner() {
    }

    public void setPutUpSign(Runnable putUpSign) {
        this.putUpSign = putUpSign;
    }

    public void setPutDownSign(Runnable putDownSign) {
        this.putDownSign = putDownSign;
    }

    protected void putUpSign() {
        putUpSign.run();
    }

    protected void putDownSign() {
        putDownSign.run();
    }

}
