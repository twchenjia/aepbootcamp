package com.thoughtworks.parking;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class SmartPark {
    private Park[] parks;
    private Map<Car, Park> carParkMap;

    public SmartPark(Park[] parks) {
        this.parks = parks;
        carParkMap = new HashMap<>();
    }

    public Ticket park(Car myCar) {
        Park park = getMaxAvailableSizePark(parks);
        carParkMap.put(myCar, park);
        return park.parkCar(myCar);
    }

    public Park getCarParkedLot(Car myCar) {
        return carParkMap.get(myCar);
    }

    private Park getMaxAvailableSizePark(Park[] parks) {
        return Stream.of(parks).max(Comparator.comparing(park -> park.getSize() - park.getParkCarSize())).get();
    }

    public Car pick(Ticket ticket) {
        Park park = getParkByTicket(ticket);
        return park.pickCar(ticket);
    }

    private Park getParkByTicket(Ticket ticket) {
        return Stream.of(this.parks).filter(park -> park.getTicketCarMap().containsKey(ticket)).findFirst().get();
    }
}
