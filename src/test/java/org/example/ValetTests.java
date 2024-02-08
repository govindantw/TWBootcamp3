package org.example;

import org.example.rule.ParkingLotIsFullAndSignIsNotUp;
import org.example.rule.ParkingLotIsNotFullAndSignIsUp;
import org.example.rule.RuleEngine;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValetTests {

    @Test
    public void valetParksCarInto1SingleCapacityEmptyParkingLot() {
        ParkingLot parkingLot = new ParkingLotImpl(1);
        Car car = new Car();
        Valet valet = new Valet();
        assertTrue(valet.parkIfPossible(car,ParkingStrategy.NEAREST, parkingLot));
        assertTrue(parkingLot.isAtMaxCapacity());
    }

    @Test
    public void valetParksCarInto1SingleCapacityEmptyParkingLotAndThenUnparks() {
        ParkingLot parkingLot = new ParkingLotImpl(1);
        Car car = new Car();
        Valet valet = new Valet();
        assertTrue(valet.parkIfPossible(car,ParkingStrategy.NEAREST, parkingLot));
        assertTrue(parkingLot.isAtMaxCapacity());
        assertTrue(valet.unParkIfPossible(car,parkingLot));
        assertFalse(parkingLot.isAtMaxCapacity());
    }

    @Test
    public void valetParks2CarsInto2SingleCapacityEmptyParkingLots() {
        RuleEngine ruleEngine = new RuleEngine();
        ParkingLot parkingLot1 = new ParkingLotImpl(1,ruleEngine::evaluate);
        ParkingLot parkingLot2 = new ParkingLotImpl(1,ruleEngine::evaluate);
        ParkingLotOwner owner1 = new ParkingLotOwner(parkingLot1::putUpSign, parkingLot1::putDownSign);
        ParkingLotOwner owner2 = new ParkingLotOwner(parkingLot2::putUpSign, parkingLot2::putDownSign);
        ruleEngine.addRule(new ParkingLotIsNotFullAndSignIsUp(parkingLot1,owner1));
        ruleEngine.addRule(new ParkingLotIsFullAndSignIsNotUp(parkingLot1,owner1));
        ruleEngine.addRule(new ParkingLotIsNotFullAndSignIsUp(parkingLot2,owner2));
        ruleEngine.addRule(new ParkingLotIsFullAndSignIsNotUp(parkingLot2,owner2));
        Car car1 = new Car();
        Car car2 = new Car();
        Valet valet = new Valet();
        assertTrue(valet.parkIfPossible(car1,ParkingStrategy.NEAREST, parkingLot1, parkingLot2));
        assertTrue(parkingLot1.isAtMaxCapacity());
        assertFalse(parkingLot2.isAtMaxCapacity());
        assertTrue(valet.parkIfPossible(car2, ParkingStrategy.NEAREST,parkingLot1, parkingLot2));
        assertTrue(parkingLot1.isAtMaxCapacity());
        assertTrue(parkingLot2.isAtMaxCapacity());
    }

    @Test
    public void valetParks2CarsInto2SingleCapacityEmptyParkingLotsAndThenUnpark() {
        RuleEngine ruleEngine = new RuleEngine();
        ParkingLot parkingLot1 = new ParkingLotImpl(1,ruleEngine::evaluate);
        ParkingLot parkingLot2 = new ParkingLotImpl(1,ruleEngine::evaluate);
        ParkingLotOwner owner1 = new ParkingLotOwner(parkingLot1::putUpSign, parkingLot1::putDownSign);
        ParkingLotOwner owner2 = new ParkingLotOwner(parkingLot2::putUpSign, parkingLot2::putDownSign);
        ruleEngine.addRule(new ParkingLotIsNotFullAndSignIsUp(parkingLot1,owner1));
        ruleEngine.addRule(new ParkingLotIsFullAndSignIsNotUp(parkingLot1,owner1));
        ruleEngine.addRule(new ParkingLotIsNotFullAndSignIsUp(parkingLot2,owner2));
        ruleEngine.addRule(new ParkingLotIsFullAndSignIsNotUp(parkingLot2,owner2));
        Car car1 = new Car();
        Car car2 = new Car();
        Valet valet = new Valet();
        assertTrue(valet.parkIfPossible(car1,ParkingStrategy.NEAREST, parkingLot1, parkingLot2));
        assertTrue(parkingLot1.isAtMaxCapacity());
        assertFalse(parkingLot2.isAtMaxCapacity());
        assertTrue(valet.parkIfPossible(car2,ParkingStrategy.NEAREST, parkingLot1, parkingLot2));
        assertTrue(parkingLot1.isAtMaxCapacity());
        assertTrue(parkingLot2.isAtMaxCapacity());
        assertTrue(valet.unParkIfPossible(car1,parkingLot1,parkingLot2));
        assertFalse(parkingLot1.isAtMaxCapacity());
        assertTrue(parkingLot2.isAtMaxCapacity());
        assertTrue(valet.unParkIfPossible(car2,parkingLot1,parkingLot2));
        assertFalse(parkingLot1.isAtMaxCapacity());
        assertFalse(parkingLot2.isAtMaxCapacity());
    }

    @Test
    public void valetParksSameCarInto2SingleCapacityEmptyParkingLots() {
        RuleEngine ruleEngine = new RuleEngine();
        ParkingLot parkingLot1 = new ParkingLotImpl(1,ruleEngine::evaluate);
        ParkingLot parkingLot2 = new ParkingLotImpl(1,ruleEngine::evaluate);
        ParkingLotOwner owner1 = new ParkingLotOwner(parkingLot1::putUpSign, parkingLot1::putDownSign);
        ParkingLotOwner owner2 = new ParkingLotOwner(parkingLot2::putUpSign, parkingLot2::putDownSign);
        ruleEngine.addRule(new ParkingLotIsNotFullAndSignIsUp(parkingLot1,owner1));
        ruleEngine.addRule(new ParkingLotIsFullAndSignIsNotUp(parkingLot1,owner1));
        ruleEngine.addRule(new ParkingLotIsNotFullAndSignIsUp(parkingLot2,owner2));
        ruleEngine.addRule(new ParkingLotIsFullAndSignIsNotUp(parkingLot2,owner2));
        ParkingLot parkingLot1WithAwarenessOfOtherParkingLots
                = new ParkingLotWithAwarenessOfOtherParkingLots(parkingLot1,parkingLot2);
        ParkingLot parkingLot2WithAwarenessOfOtherParkingLots
                = new ParkingLotWithAwarenessOfOtherParkingLots(parkingLot2,parkingLot1);
        Car car = new Car();
        Valet valet = new Valet();
        assertTrue(valet.parkIfPossible(car,ParkingStrategy.NEAREST, parkingLot1WithAwarenessOfOtherParkingLots));
        assertFalse(valet.parkIfPossible(car,ParkingStrategy.NEAREST, parkingLot2WithAwarenessOfOtherParkingLots));
        assertTrue(parkingLot1WithAwarenessOfOtherParkingLots.isAtMaxCapacity());
        assertFalse(parkingLot2WithAwarenessOfOtherParkingLots.isAtMaxCapacity());
    }

    @Test
    public void valetParks2CarsInto2SingleCapacityEmptyParkingLotsOnCheapestCost() {
        RuleEngine ruleEngine = new RuleEngine();
        ParkingLot parkingLot1 = new ParkingLotImpl(1,ruleEngine::evaluate,20);
        ParkingLot parkingLot2 = new ParkingLotImpl(1,ruleEngine::evaluate,10);
        ParkingLotOwner owner1 = new ParkingLotOwner(parkingLot1::putUpSign, parkingLot1::putDownSign);
        ParkingLotOwner owner2 = new ParkingLotOwner(parkingLot2::putUpSign, parkingLot2::putDownSign);
        ruleEngine.addRule(new ParkingLotIsNotFullAndSignIsUp(parkingLot1,owner1));
        ruleEngine.addRule(new ParkingLotIsFullAndSignIsNotUp(parkingLot1,owner1));
        ruleEngine.addRule(new ParkingLotIsNotFullAndSignIsUp(parkingLot2,owner2));
        ruleEngine.addRule(new ParkingLotIsFullAndSignIsNotUp(parkingLot2,owner2));
        Car car1 = new Car();
        Car car2 = new Car();
        Valet valet = new Valet();
        assertTrue(valet.parkIfPossible(car1,ParkingStrategy.CHEAPEST, parkingLot1, parkingLot2));
        assertTrue(parkingLot2.isAtMaxCapacity());
        assertFalse(parkingLot1.isAtMaxCapacity());
        assertTrue(valet.parkIfPossible(car2, ParkingStrategy.CHEAPEST,parkingLot1, parkingLot2));
        assertTrue(parkingLot1.isAtMaxCapacity());
        assertTrue(parkingLot2.isAtMaxCapacity());
    }

}
