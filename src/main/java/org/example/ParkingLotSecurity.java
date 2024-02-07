package org.example;

public class ParkingLotSecurity {
    
    private  boolean carsRedirected;

    public ParkingLotSecurity() {
        this.carsRedirected = false;
    }
    
    public boolean carsAreRedirected(){
        return carsRedirected;
    }
    
    public void allowCarsIntoParkingLot(){
        carsRedirected = false;
    }
    
    public void redirectCars(){
        carsRedirected = true;
    }
    
    
}
