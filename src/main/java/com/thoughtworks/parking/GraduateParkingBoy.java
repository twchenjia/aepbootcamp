package com.thoughtworks.parking;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

public class GraduateParkingBoy {
    private Map<Car, ParkingLot> carParkMap;
    private ParkingLot[] parkingLots;

    public GraduateParkingBoy(ParkingLot[] parkingLots) {
        carParkMap = new HashMap<>();
        this.parkingLots = parkingLots;
    }

    public Ticket park(Car myCar) {
        ParkingLot sequencedParkingLot = getSequencedPark(parkingLots);
        if (Objects.isNull(sequencedParkingLot)) {
            throw new ParklotException();
        }
        Ticket ticket = sequencedParkingLot.parkCar(myCar);
        carParkMap.put(myCar, sequencedParkingLot);
        return ticket;
    }

    private ParkingLot getSequencedPark(ParkingLot[] parkingLots) {
        return Stream.of(parkingLots).filter((park) -> park.getSize() - park.getParkCarSize() > 0).findFirst().orElse(null);
    }

    public ParkingLot getCarParkedLot(Car myCar) {
        return carParkMap.get(myCar);
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
