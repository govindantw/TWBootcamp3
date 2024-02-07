package org.example;

import java.util.HashSet;
import java.util.Set;

public class Driver {
    Set<Car> myCars;
    public Driver(){
        this.myCars = new HashSet<>();
    }

    public boolean parkIfPossible(Car car, ParkingLot parkingLot) {
        if(!parkingLot.addCarIfPossible(car)){
            return false;
        }
        myCars.add(car);
        return true;
    }

    public boolean unParkIfPossible(Car car, ParkingLot parkingLot){
        if(!myCars.contains(car)){
            return false;
        }
        myCars.remove(car);
        return parkingLot.removeCarIfPossible(car);
    }

}
