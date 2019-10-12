package com.thoughtworks.parking;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {
    @Test
    void should_return_a_ticket_when_park_a_car_with_parking_lot_has_available_space() {
        ParkingLot parkingLot = new ParkingLot(4);
        Car car = new Car();
        Ticket ticket = parkingLot.parkCar(car);
        assertNotNull(ticket);
    }

    @Test
    void should_throw_park_lot_exception_when_park_a_car_with_parking_lot_has_no_space() {
        Car car = new Car();
        int fullSize = 1;
        ParkingLot parkingLot = new ParkingLot(fullSize);
        parkingLot.parkCar(new Car());
        assertThrows(ParklotException.class, () -> parkingLot.parkCar(car));
    }

    @Test
    void should_return_a_car_when_pick_a_car_with_a_correct_ticket_with_parking_lot_has_some_cars() {
        ParkingLot parkingLot = new ParkingLot(4);
        Car myCar = new Car();
        Ticket ticket = parkingLot.parkCar(myCar);
        Car car = parkingLot.pickCar(ticket);
        assertThat(car, is(myCar));
    }

    @Test
    void should_return_no_car_when_pick_a_car_with_a_incorrect_ticket() {
        ParkingLot parkingLot = new ParkingLot(4);
        Car myCar = new Car();
        parkingLot.parkCar(myCar);
        Car car = parkingLot.pickCar(new Ticket());
        assertNull(car);
    }

    @Test
    void should_throw_park_lot_exception_when_pick_a_car_with_parking_lot_has_no_car() {
        Ticket ticket = new Ticket();
        int fullSize = 1;
        ParkingLot parkingLot = new ParkingLot(fullSize);
        assertThrows(ParklotException.class, () -> parkingLot.pickCar(ticket));
    }

    @Test
    void should_return_no_car_when_pick_a_car_with_a_same_ticket_twice() {
        ParkingLot parkingLot = new ParkingLot(4);
        Car myCar = new Car();
        parkingLot.parkCar(new Car());
        Ticket ticket = parkingLot.parkCar(myCar);
        parkingLot.pickCar(ticket);
        Car car = parkingLot.pickCar(ticket);
        assertNull(car);
    }
}
