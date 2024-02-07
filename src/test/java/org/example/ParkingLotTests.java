package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTests {

    @Test
    public void canParkCarInEmptyParkingLot() {
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot(1);
        assertTrue(parkingLot.addCarIfPossible(car));
    }

    @Test
    public void cannotParkCarInFullParkingLot() {
        Car car1 = new Car();
        ParkingLot parkingLot = new ParkingLot(1);
        assertTrue(parkingLot.addCarIfPossible(car1));
        Car car2 = new Car();
        assertFalse(parkingLot.addCarIfPossible(car2));
    }

    @Test
    public void canParkMultipleCarsInParkingLotWithCapacity() {
        Car car1 = new Car();
        ParkingLot parkingLot = new ParkingLot(2);
        assertTrue(parkingLot.addCarIfPossible(car1));
        Car car2 = new Car();
        assertTrue(parkingLot.addCarIfPossible(car2));
    }

    @Test
    public void canUnparkTheirCarFromParkingLot(){
        Car car1 = new Car();
        ParkingLot parkingLot = new ParkingLot(1);
        assertTrue(parkingLot.addCarIfPossible(car1));
        assertTrue(parkingLot.removeCarIfPossible(car1));
    }

    @Test
    public void driverCanParkTheirCarInEmptyParkingLot(){
        Driver driver = new Driver();
        Car car1 = new Car();
        ParkingLot parkingLot = new ParkingLot(1);
        assertTrue(driver.parkIfPossible(car1,parkingLot));
    }

    @Test
    public void driverCannotUnparkCarTheyDidNotPark() {
        Driver driver = new Driver();
        Car car1 = new Car();
        ParkingLot parkingLot = new ParkingLot(2);
        assertTrue(driver.parkIfPossible(car1,parkingLot));
        Driver driver2 = new Driver();
        Car car2 = new Car();
        assertTrue(driver2.parkIfPossible(car2,parkingLot));
        assertFalse(driver2.unParkIfPossible(car1,parkingLot));
    }

//    @Test
    public void parkingLotOwnerPutsUpSignOnceLotIsFull() {
        ParkingLotOwner owner = new ParkingLotOwner();
        ParkingLot parkingLot = new ParkingLot(1,
                owner::putUpSign,
                owner::putDownSign);
        owner.setPutDownSign(parkingLot::putDownSign);
        owner.setPutUpSign(parkingLot::putUpSign);
        Car car = new Car();
        assertFalse(parkingLot.hasPutUpSign());
        parkingLot.addCarIfPossible(car);
        assertTrue(parkingLot.hasPutUpSign());
    }

//    @Test
    public void parkingLotOwnerPutsDownSignOnceLotIsNoLongerFull() {
        ParkingLotOwner owner = new ParkingLotOwner();
        ParkingLot parkingLot = new ParkingLot(1,
                owner::putUpSign,
                owner::putDownSign);
        owner.setPutDownSign(parkingLot::putDownSign);
        owner.setPutUpSign(parkingLot::putUpSign);
        Car car = new Car();
        owner.setPutDownSign(parkingLot::putDownSign);
        assertFalse(parkingLot.hasPutUpSign());
        parkingLot.addCarIfPossible(car);
        assertTrue(parkingLot.hasPutUpSign());
        parkingLot.removeCarIfPossible(car);
        assertFalse(parkingLot.hasPutUpSign());
    }

    @Test
    public void parkingLotOwnerPutsDownSignOnceLotIsNoLongerFullWithRuleEngine() {
        RuleEngine ruleEngine = new RuleEngine();
        ParkingLot parkingLot = new ParkingLot(1,ruleEngine::evaluate);
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


}
