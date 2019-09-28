package com.thoughtworks.parking;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;

public class ParkTest {
    @Test
    void should_return_a_ticket_when_park_a_car_with_parking_lot_has_available_space() {
        Park park = new Park(4);
        Car car = new Car();
        Ticket ticket = park.parkCar(car);
        assertNotNull(ticket);
    }

    @Test
    void should_throw_park_lot_exception_when_park_a_car_with_parking_lot_has_no_space() {
        Car car = new Car();
        int fullSize = 1;
        Park park = new Park(fullSize);
        park.parkCar(new Car());
        assertThrows(ParklotException.class, () -> park.parkCar(car));
    }

    @Test
    void should_return_a_car_when_pick_a_car_with_a_correct_ticket_with_parking_lot_has_some_cars() {
        Park park = new Park(4);
        Car myCar = new Car();
        Ticket ticket = park.parkCar(myCar);
        Car car = park.pickCar(ticket);
        assertThat(car, is(myCar));
    }

    @Test
    void should_return_no_car_when_pick_a_car_with_a_incorrect_ticket() {
        Park park = new Park(4);
        Car myCar = new Car();
        park.parkCar(myCar);
        Car car = park.pickCar(new Ticket());
        assertNull(car);
    }

    @Test
    void should_throw_park_lot_exception_when_pick_a_car_with_parking_lot_has_no_car() {
        Ticket ticket = new Ticket();
        int fullSize = 1;
        Park park = new Park(fullSize);
        assertThrows(ParklotException.class, () -> park.pickCar(ticket));
    }

    @Test
    void should_return_no_car_when_pick_a_car_with_a_same_ticket_twice() {
        Park park = new Park(4);
        Car myCar = new Car();
        park.parkCar(new Car());
        Ticket ticket = park.parkCar(myCar);
        park.pickCar(ticket);
        Car car = park.pickCar(ticket);
        assertNull(car);
    }
}
