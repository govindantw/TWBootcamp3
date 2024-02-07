package org.example;

import java.util.HashSet;
import java.util.Set;

public class ParkingLot {

    private boolean signIsUp;
    private final int capacity;
    private final Set<Car> parkedCars;

    Runnable parkingLotNotifier;
    Runnable parkingLotIsNoLongerFullNotifier;

    public ParkingLot(int capacity,
                      Runnable parkingLotNotifier,
                      Runnable parkingLotIsNoLongerFullNotifier) {
        this.capacity = capacity;
        this.parkedCars = new HashSet<>();
        this.parkingLotNotifier = parkingLotNotifier;
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

}
