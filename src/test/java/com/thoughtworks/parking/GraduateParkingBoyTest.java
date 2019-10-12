package com.thoughtworks.parking;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;

public class GraduateParkingBoyTest {
    @Test
    void should_return_a_ticket_and_car_parked_in_the_sequenced_park_lot_when_graduate_boy_park_a_car_with_some_parking_lots_has_available_space() {
        ParkingLot firstParkingLot = new ParkingLot(10);
        ParkingLot secondParkingLot = new ParkingLot(10);
        ParkingLot[] parkingLots = new ParkingLot[]{firstParkingLot, secondParkingLot};
        firstParkingLot.parkCar(new Car());
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);
        Car myCar = new Car();
        Ticket ticket = graduateParkingBoy.park(myCar);
        Car car = firstParkingLot.pickCar(ticket);
        assertNotNull(ticket);
        assertThat(car, is(myCar));
    }

    @Test
    void should_throw_park_lot_exception_when_graduate_boy_park_a_car_with_all_parking_lots_have_no_space() {
        ParkingLot[] parkingLots = new ParkingLot[]{new ParkingLot(1), new ParkingLot(1)};
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);
        graduateParkingBoy.park(new Car());
        graduateParkingBoy.park(new Car());
        Car myCar = new Car();
        assertThrows(ParklotException.class, () -> graduateParkingBoy.park(myCar));
    }

    @Test
    void should_return_a_car_when_graduate_boy_pick_a_car_with_a_correct_ticket_with_parking_lots_has_some_cars() {
        ParkingLot[] parkingLots = new ParkingLot[]{new ParkingLot(10), new ParkingLot(10)};
        Car myCar = new Car();
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);
        Ticket ticket = graduateParkingBoy.park(myCar);
        Car car = graduateParkingBoy.pick(ticket);
        assertThat(car, is(myCar));
    }

    @Test
    void should_return_no_car_when_graduate_boy_pick_a_car_with_a_incorrect_ticket() {
        ParkingLot[] parkingLots = new ParkingLot[]{new ParkingLot(10), new ParkingLot(10)};
        Car myCar = new Car();
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);
        graduateParkingBoy.park(myCar);
        Car car = graduateParkingBoy.pick(new Ticket());
        assertNull(car);
    }

    @Test
    void should_return_no_car_when_a_graduate_boy_pick_a_car_with_all_parking_lots_has_no_car() {
        ParkingLot[] parkingLots = new ParkingLot[]{new ParkingLot(1), new ParkingLot(1)};
        Ticket ticket = new Ticket();
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);
        Car car = graduateParkingBoy.pick(ticket);
        assertNull(car);
    }

    @Test
    void should_return_no_car_when_a_graduate_boy_pick_a_car_with_a_same_ticket_twice() {
        ParkingLot[] parkingLots = new ParkingLot[]{new ParkingLot(10), new ParkingLot(10)};
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);
        Car myCar = new Car();
        Ticket ticket = graduateParkingBoy.park(myCar);
        graduateParkingBoy.pick(ticket);
        Car car = graduateParkingBoy.pick(ticket);
        assertNull(car);
    }
}
