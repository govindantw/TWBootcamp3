package org.example;

import java.util.HashSet;
import java.util.Set;

public class ParkingLot {

    private boolean signIsUp;
    private final int capacity;
    private final Set<Car> parkedCars;

    Runnable parkingLotIsFullNotifier;
    Runnable parkingLotIsNoLongerFullNotifier;

    public ParkingLot(int capacity,
                      Runnable parkingLotIsFullNotifier,
                      Runnable parkingLotIsNoLongerFullNotifier) {
        this.capacity = capacity;
        this.parkedCars = new HashSet<>();
        this.parkingLotIsFullNotifier = parkingLotIsFullNotifier;
        this.parkingLotIsNoLongerFullNotifier = parkingLotIsNoLongerFullNotifier;
        this.signIsUp = false;

    }

    public ParkingLot(int capacity) {
        this(capacity,() -> {},() -> {});
    }

    public ParkingLot(int capacity, Runnable notifier) {
        this(capacity,notifier,notifier);
    }

    public boolean isAtMaxCapacity() {
        return parkedCars.size() >= capacity;
    }
    private void checkParkedCarsAndNotify() {
        if(signIsUp && !isAtMaxCapacity()) {
            parkingLotIsNoLongerFullNotifier.run();
        }
        if(!signIsUp && isAtMaxCapacity()) {
            parkingLotIsFullNotifier.run();
        }
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
    }

    public void putDownSign() {
        this.signIsUp = false;
    }

    public boolean hasPutUpSign() {
        return signIsUp;
    }

}
