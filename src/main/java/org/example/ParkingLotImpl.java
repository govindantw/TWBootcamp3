package org.example;

import java.util.HashSet;
import java.util.Set;

public class ParkingLotImpl implements ParkingLot{

    private boolean signIsUp;
    private final int capacity;
    private final Set<Car> parkedCars;

    private final int pricePerPark;
    Runnable parkingLotNotifier;

    public ParkingLotImpl(int capacity) {
        this(capacity,() -> {});
    }

    public ParkingLotImpl(int capacity, Runnable notifier, int pricePerPark) {
        this.capacity = capacity;
        this.parkedCars = new HashSet<>();
        this.parkingLotNotifier = notifier;
        this.signIsUp = false;
        this.pricePerPark = pricePerPark;
    }
    public ParkingLotImpl(int capacity, Runnable notifier){
        this(capacity,notifier,0);
    }

    @Override
    public int getPricePerPark(){
        return pricePerPark;
    }
    public boolean isAtMaxCapacity() {
        return parkedCars.size() >= capacity;
    }
    private void checkParkedCarsAndNotify() {
        parkingLotNotifier.run();
    }
    public boolean addCarIfPossible(Car car) {
        if(hasPutUpSign()) {
            return false;
        }
        if(isAtMaxCapacity()) {
            return false;
        }
        parkedCars.add(car);
        checkParkedCarsAndNotify();
        return true;
    }

    public boolean removeCarIfPossible(Car car){
       if(!parkedCars.remove(car)) {
           return false;
       }
       checkParkedCarsAndNotify();
       return true;
    }

    public void putUpSign() {
        this.signIsUp = true;
        parkingLotNotifier.run();
    }

    public void putDownSign() {
        this.signIsUp = false;
        parkingLotNotifier.run();
    }

    public boolean hasPutUpSign() {
        return signIsUp;
    }

    @Override
    public boolean hasCar(Car car) {
        return parkedCars.contains(car);
    }

}
