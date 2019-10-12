package com.thoughtworks.parking;

import java.util.*;
import java.util.stream.Stream;

public class SmartParkingBoy {
    private ParkingLot[] parkingLots;
    private Map<Car, ParkingLot> carParkMap;

    public SmartParkingBoy(ParkingLot[] parkingLots) {
        this.parkingLots = parkingLots;
        carParkMap = new HashMap<>();
    }

    public Ticket park(Car myCar) {
        ParkingLot parkingLot = getMaxAvailableSizePark(parkingLots);
        carParkMap.put(myCar, parkingLot);
        return parkingLot.parkCar(myCar);
    }

    public ParkingLot getCarParkedLot(Car myCar) {
        return carParkMap.get(myCar);
    }

    private ParkingLot getMaxAvailableSizePark(ParkingLot[] parkingLots) {
        return Stream.of(parkingLots).max(Comparator.comparing(park -> park.getSize() - park.getParkCarSize())).get();
    }

    public Car pick(Ticket ticket) {
        ParkingLot parkingLot = getParkByTicket(ticket);
        if (Objects.isNull(parkingLot)) {
            return null;
        }
        return parkingLot.pickCar(ticket);
    }

    private ParkingLot getParkByTicket(Ticket ticket) {
        return Stream.of(this.parkingLots).filter(park -> park.getTicketCarMap().containsKey(ticket)).findFirst().orElse(null);
    }
}
