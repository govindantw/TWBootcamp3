package org.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ParkingLot {

    private final int capacity;
    private final Set<Car> parkedCars;

    private boolean notifiedFull;

    Runnable parkingLotIsFull;
    Runnable parkingLotIsNoLongerFull;

    public ParkingLot(int capacity,
                      Runnable parkingLotIsFull,
                      Runnable parkingLotIsNoLongerFull) {
        this.capacity = capacity;
        this.parkedCars = new HashSet<>();
        this.parkingLotIsFull = parkingLotIsFull;
        this.parkingLotIsNoLongerFull = parkingLotIsNoLongerFull;
        this.notifiedFull = false;

    }

    public ParkingLot(int capacity) {
        this(capacity,() -> {},() -> {});
    }

    private boolean isAtMaxCapacity() {
        return parkedCars.size() >= capacity;
    }
    private void checkParkedCarsAndNotify() {
        if(notifiedFull && !isAtMaxCapacity()) {
            parkingLotIsNoLongerFull.run();
            notifiedFull = false;
        }
        if(!notifiedFull && isAtMaxCapacity()) {
            parkingLotIsFull.run();
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
