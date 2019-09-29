package com.thoughtworks.parking;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GraduateParkTest {
    @Test
    void should_return_a_ticket_and_car_parked_in_the_sequenced_park_lot_when_graduate_boy_park_a_car_with_some_parking_lots_has_available_space() {
        Park[] parks = new Park[]{new Park(10), new Park(10)};
        parks[0].parkCar(new Car());
        GraduatePark graduatePark = new GraduatePark(parks);
        Car myCar = new Car();
        Ticket ticket = graduatePark.park(myCar);
        assertNotNull(ticket);
        Park carParkedLot = graduatePark.getCarParkedLot(myCar);
        Park sequencedPark = parks[0];
        assertThat(carParkedLot, is(sequencedPark));
    }

    @Test
    void should_throw_park_lot_exception_when_graduate_boy_park_a_car_with_all_parking_lots_has_no_space() {
        Park[] parks = new Park[]{new Park(1), new Park(1)};
        GraduatePark graduatePark = new GraduatePark(parks);
        graduatePark.park(new Car());
        graduatePark.park(new Car());
        Car myCar = new Car();
        assertThrows(ParklotException.class, () -> graduatePark.park(myCar));
    }
}
