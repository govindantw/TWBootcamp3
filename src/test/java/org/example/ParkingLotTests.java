package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTests {

    @Test
    public void canParkCarInEmptyParkingLot() {
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLotImpl(1);
        assertTrue(parkingLot.addCarIfPossible(car));
    }

    @Test
    public void cannotParkCarInFullParkingLot() {
        Car car1 = new Car();
        ParkingLot parkingLot = new ParkingLotImpl(1);
        assertTrue(parkingLot.addCarIfPossible(car1));
        Car car2 = new Car();
        assertFalse(parkingLot.addCarIfPossible(car2));
    }

    @Test
    public void canParkMultipleCarsInParkingLotWithCapacity() {
        Car car1 = new Car();
        ParkingLot parkingLot = new ParkingLotImpl(2);
        assertTrue(parkingLot.addCarIfPossible(car1));
        Car car2 = new Car();
        assertTrue(parkingLot.addCarIfPossible(car2));
    }

    @Test
    public void canUnparkTheirCarFromParkingLot(){
        Car car1 = new Car();
        ParkingLot parkingLot = new ParkingLotImpl(1);
        assertTrue(parkingLot.addCarIfPossible(car1));
        assertTrue(parkingLot.removeCarIfPossible(car1));
    }

    @Test
    public void driverCanParkTheirCarInEmptyParkingLot(){
        Driver driver = new Driver();
        Car car1 = new Car();
        ParkingLot parkingLot = new ParkingLotImpl(1);
        assertTrue(driver.parkIfPossible(car1, parkingLot));
    }

    @Test
    public void driverCannotUnparkCarTheyDidNotPark() {
        Driver driver = new Driver();
        Car car1 = new Car();
        ParkingLot parkingLot = new ParkingLotImpl(2);
        assertTrue(driver.parkIfPossible(car1, parkingLot));
        Driver driver2 = new Driver();
        Car car2 = new Car();
        assertTrue(driver2.parkIfPossible(car2, parkingLot));
        assertFalse(driver2.unParkIfPossible(car1, parkingLot));
    }

    @Test
    public void parkingLotOwnerPutsDownSignOnceLotIsNoLongerFullWithRuleEngine() {
        RuleEngine ruleEngine = new RuleEngine();
        ParkingLot parkingLot = new ParkingLotImpl(1,ruleEngine::evaluate);
        ParkingLotSecurity security = new ParkingLotSecurity();
        ParkingLotOwner owner = new ParkingLotOwner(parkingLot::putUpSign, parkingLot::putDownSign);
        Car car = new Car();

        ruleEngine.addRule(new SignIsUpAndCarsAreAllowed(parkingLot,security));
        ruleEngine.addRule(new SignIsDownAndCarsAreNotAllowed(parkingLot,security));

        ruleEngine.addRule(new ParkingLotIsNotFullAndSignIsUp(parkingLot,owner));
        ruleEngine.addRule(new ParkingLotIsFullAndSignIsNotUp(parkingLot,owner));
        assertFalse(parkingLot.hasPutUpSign());
        assertFalse(security.carsAreRedirected());
        parkingLot.addCarIfPossible(car);
        assertTrue(parkingLot.hasPutUpSign());
        assertTrue(security.carsAreRedirected());
        parkingLot.removeCarIfPossible(car);
        assertFalse(parkingLot.hasPutUpSign());
        assertFalse(security.carsAreRedirected());
    }

    @Test
    public void valetParksCarInto1SingleCapacityEmptyParkingLot() {
        ParkingLot parkingLot = new ParkingLotImpl(1);
        Car car = new Car();
        Valet valet = new Valet();
        assertTrue(valet.parkIfPossible(car, parkingLot));
        assertTrue(parkingLot.isAtMaxCapacity());
    }

    @Test
    public void valetParksCarInto1SingleCapacityEmptyParkingLotAndThenUnparks() {
        ParkingLot parkingLot = new ParkingLotImpl(1);
        Car car = new Car();
        Valet valet = new Valet();
        assertTrue(valet.parkIfPossible(car, parkingLot));
        assertTrue(parkingLot.isAtMaxCapacity());
        assertTrue(valet.unParkIfPossible(car,parkingLot));
        assertFalse(parkingLot.isAtMaxCapacity());
    }

    @Test
    public void valetParks2CarsInto2SingleCapacityEmptyParkingLots() {
        ParkingLot parkingLot1 = new ParkingLotImpl(1);
        ParkingLot parkingLot2 = new ParkingLotImpl(1);
        Car car1 = new Car();
        Car car2 = new Car();
        Valet valet = new Valet();
        assertTrue(valet.parkIfPossible(car1, parkingLot1, parkingLot2));
        assertTrue(parkingLot1.isAtMaxCapacity());
        assertFalse(parkingLot2.isAtMaxCapacity());
        assertTrue(valet.parkIfPossible(car2, parkingLot1, parkingLot2));
        assertTrue(parkingLot1.isAtMaxCapacity());
        assertTrue(parkingLot2.isAtMaxCapacity());
    }

    @Test
    public void valetParks2CarsInto2SingleCapacityEmptyParkingLotsAndThenUnpark() {
        ParkingLot parkingLot1 = new ParkingLotImpl(1);
        ParkingLot parkingLot2 = new ParkingLotImpl(1);
        Car car1 = new Car();
        Car car2 = new Car();
        Valet valet = new Valet();
        assertTrue(valet.parkIfPossible(car1, parkingLot1, parkingLot2));
        assertTrue(parkingLot1.isAtMaxCapacity());
        assertFalse(parkingLot2.isAtMaxCapacity());
        assertTrue(valet.parkIfPossible(car2, parkingLot1, parkingLot2));
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
        ParkingLot parkingLot1 = new ParkingLotImpl(1);
        ParkingLot parkingLot2 = new ParkingLotImpl(1);
        ParkingLot parkingLot1WithOmnipotence
                = new ParkingLotWithAwarenessOfOtherParkingLots(parkingLot1,parkingLot2);
        ParkingLot parkingLot2WithOmnipotence
                = new ParkingLotWithAwarenessOfOtherParkingLots(parkingLot2,parkingLot1);
        Car car = new Car();
        Valet valet = new Valet();
        assertTrue(valet.parkIfPossible(car, parkingLot1WithOmnipotence));
        assertFalse(valet.parkIfPossible(car, parkingLot2WithOmnipotence));
        assertTrue(parkingLot1WithOmnipotence.isAtMaxCapacity());
        assertFalse(parkingLot2WithOmnipotence.isAtMaxCapacity());
    }


}
