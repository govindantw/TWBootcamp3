package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public enum ParkingStrategy {
    CHEAPEST(((car, parkingLots) -> {
        List<ParkingLot> sortedParkingLots = Arrays.stream(parkingLots).sorted((a, b) -> Integer.compare(a.getPricePerPark(), b.getPricePerPark())).collect(Collectors.toList());
        for (ParkingLot parkingLot : sortedParkingLots) {
            if (!parkingLot.hasPutUpSign()) {
                return parkingLot.addCarIfPossible(car);
            }
        }
        return false;
    })),
    NEAREST(((car, parkingLots) -> {
        for (ParkingLot parkingLot : parkingLots) {
            if (!parkingLot.hasPutUpSign()) {
                return parkingLot.addCarIfPossible(car);
            }
        }
        return false;
    }));

    private final BiFunction<Car, ParkingLot[], Boolean> sortStrategy;

    public boolean parkIfPossible(Car car, ParkingLot... parkingLots) {
        return sortStrategy.apply(car, parkingLots);
    }

    ParkingStrategy(BiFunction<Car, ParkingLot[], Boolean> sortStrategy) {
        this.sortStrategy = sortStrategy;
    }
}
