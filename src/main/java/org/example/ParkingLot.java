package org.example;

import java.util.HashSet;
import java.util.Set;

public class ParkingLot {

    private final int capacity;
    private final Set<Car> parkedCars;

    private boolean notifiedFull;

    Runnable parkingLotIsFullNotifier;
    Runnable parkingLotIsNoLongerFullNotifier;

    public ParkingLot(int capacity,
                      Runnable parkingLotIsFullNotifier,
                      Runnable parkingLotIsNoLongerFullNotifier) {
        this.capacity = capacity;
        this.parkedCars = new HashSet<>();
        this.parkingLotIsFullNotifier = parkingLotIsFullNotifier;
        this.parkingLotIsNoLongerFullNotifier = parkingLotIsNoLongerFullNotifier;
        this.notifiedFull = false;

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
        if(notifiedFull && !isAtMaxCapacity()) {
            parkingLotIsNoLongerFullNotifier.run();
            notifiedFull = false;
        }
        if(!notifiedFull && isAtMaxCapacity()) {
            parkingLotIsFullNotifier.run();
            notifiedFull = true;
        }
    }
    public boolean addCarIfPossible(Car car) {
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
}
