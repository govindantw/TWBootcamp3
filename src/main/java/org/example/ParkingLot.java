package org.example;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {

    private final int capacity;
    private final List<Car> parkedCars;
    public ParkingLot(int capacity) {
        this.capacity = capacity;
        this.parkedCars = new ArrayList<>();
    }
    public boolean addCarIfPossible(Car car) {
        if(parkedCars.size() < capacity) {
            parkedCars.add(car);
            return true;
        }
        return false;
    }

    public boolean removeCarIfPossible(Car car){
       return parkedCars.remove(car);
    }
}
