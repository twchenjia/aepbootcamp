package com.thoughtworks.parking;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class GraduatePark {
    private Map<Car, Park> carParkMap;
    private Park[] parks;

    public GraduatePark(Park[] parks) {
        carParkMap = new HashMap<>();
        this.parks = parks;
    }

    public Ticket park(Car myCar) {
        Park sequencedPark = getSequencedPark(parks);
        Ticket ticket = sequencedPark.parkCar(myCar);
        carParkMap.put(myCar, sequencedPark);
        return ticket;
    }

    private Park getSequencedPark(Park[] parks) {
        return Stream.of(parks).filter((park) -> park.getSize() - park.getParkCarSize() > 0).findFirst().orElse(null);
    }

    public Park getCarParkedLot(Car myCar) {
        return carParkMap.get(myCar);
    }
}