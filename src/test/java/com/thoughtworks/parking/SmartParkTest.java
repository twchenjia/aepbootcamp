package com.thoughtworks.parking;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SmartParkTest {
    @Test
    void should_return_a_ticket_and_car_parked_in_the_most_available_size_park_lot_when_smart_boy_park_a_car_with_some_parking_lots_has_available_space() {
        Park[] parks = new Park[]{new Park(10), new Park(10)};
        parks[0].parkCar(new Car());
        SmartPark smartPark = new SmartPark(parks);
        Car myCar = new Car();
        Ticket ticket = smartPark.park(myCar);
        assertNotNull(ticket);
        Park carParkedLot = smartPark.getCarParkedLot(myCar);
        Park mostAvailableSizeParkLot = parks[1];
        assertThat(carParkedLot, is(mostAvailableSizeParkLot));
    }

    @Test
    void should_throw_park_lot_exception_when_smart_boy_park_a_car_with_all_parking_lots_has_no_space() {
        Park[] parks = new Park[]{new Park(1), new Park(1)};
        parks[0].parkCar(new Car());
        parks[1].parkCar(new Car());
        SmartPark smartPark = new SmartPark(parks);
        Car myCar = new Car();
        assertThrows(ParklotException.class, () -> smartPark.park(myCar));
    }

    @Test
    void should_return_a_car_when_smart_boy_pick_a_car_with_a_correct_ticket_with_parking_lots_has_some_cars() {
        Park[] parks = new Park[] {new Park(10),new Park(10)};
        parks[1].parkCar(new Car());
        Car myCar = new Car();
        SmartPark smartPark = new SmartPark(parks);
        Ticket ticket = smartPark.park(myCar);
        Car car = smartPark.pick(ticket);
        assertThat(car, is(myCar));
    }


}
