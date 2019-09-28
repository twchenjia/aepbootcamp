package com.thoughtworks.parking;

public class SmartPark {
    private Park[] parks;

    public SmartPark(Park[] parks) {
        this.parks = parks;
    }

    public Ticket park(Car myCar) {
        return new Ticket();
    }

    public Park getCarParkedLot(Car myCar) {
        return new Park(10);
    }
}
