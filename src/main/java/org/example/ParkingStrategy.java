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
                return parkingLot;
            }
        }
        return null;
    })),
    NEAREST(((car, parkingLots) -> {
        for (ParkingLot parkingLot : parkingLots) {
            if (!parkingLot.hasPutUpSign()) {
                return parkingLot;
            }
        }
        return null;
    }));

    private final BiFunction<Car, ParkingLot[], ParkingLot> parkingLotSelectionStrategy;

    public ParkingLot parkIfPossible(Car car, ParkingLot... parkingLots) {
        return parkingLotSelectionStrategy.apply(car, parkingLots);
    }

    ParkingStrategy(BiFunction<Car, ParkingLot[], ParkingLot> parkingLotSelectionStrategy) {
        this.parkingLotSelectionStrategy = parkingLotSelectionStrategy;
    }
}
