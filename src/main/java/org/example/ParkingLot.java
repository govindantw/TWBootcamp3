package org.example;

public interface ParkingLot {

    public boolean isAtMaxCapacity();

    public boolean addCarIfPossible(Car car);

    public boolean removeCarIfPossible(Car car);

    public void putUpSign();

    public void putDownSign();

    public boolean hasPutUpSign();

    public boolean hasCar(Car car);

}
