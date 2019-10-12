package com.thoughtworks.parking;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;

public class SmartParkingBoyTest {
    @Test
    void should_return_a_ticket_and_car_parked_in_the_most_available_size_park_lot_when_smart_boy_park_a_car_with_some_parking_lots_has_available_space() {
        ParkingLot[] parkingLots = new ParkingLot[]{new ParkingLot(10), new ParkingLot(10)};
        parkingLots[0].parkCar(new Car());
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Car myCar = new Car();
        Ticket ticket = smartParkingBoy.park(myCar);
        assertNotNull(ticket);
        ParkingLot carParkedLot = smartParkingBoy.getCarParkedLot(myCar);
        ParkingLot mostAvailableSizeParkingLotLot = parkingLots[1];
        assertThat(carParkedLot, is(mostAvailableSizeParkingLotLot));
    }

    @Test
    void should_throw_park_lot_exception_when_smart_boy_park_a_car_with_all_parking_lots_has_no_space() {
        ParkingLot[] parkingLots = new ParkingLot[]{new ParkingLot(1), new ParkingLot(1)};
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        smartParkingBoy.park(new Car());
        smartParkingBoy.park(new Car());
        Car myCar = new Car();
        assertThrows(ParklotException.class, () -> smartParkingBoy.park(myCar));
    }

    @Test
    void should_return_a_car_when_smart_boy_pick_a_car_with_a_correct_ticket_with_parking_lots_has_some_cars() {
        ParkingLot[] parkingLots = new ParkingLot[]{new ParkingLot(10), new ParkingLot(10)};
        Car myCar = new Car();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Ticket ticket = smartParkingBoy.park(myCar);
        Car car = smartParkingBoy.pick(ticket);
        assertThat(car, is(myCar));
    }

    @Test
    void should_return_no_car_when_smart_boy_pick_a_car_with_a_incorrect_ticket() {
        ParkingLot[] parkingLots = new ParkingLot[]{new ParkingLot(10), new ParkingLot(10)};
        Car myCar = new Car();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        smartParkingBoy.park(myCar);
        Car car = smartParkingBoy.pick(new Ticket());
        assertNull(car);
    }

    @Test
    void should_return_no_car_when_a_smart_boy_pick_a_car_with_all_parking_lots_has_no_car() {
        ParkingLot[] parkingLots = new ParkingLot[]{new ParkingLot(1), new ParkingLot(1)};
        Ticket ticket = new Ticket();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Car car = smartParkingBoy.pick(ticket);
        assertNull(car);
    }

    @Test
    void should_return_no_car_when_a_smart_boy_pick_a_car_with_a_same_ticket_twice() {
        ParkingLot[] parkingLots = new ParkingLot[]{new ParkingLot(10), new ParkingLot(10)};
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Car myCar = new Car();
        Ticket ticket = smartParkingBoy.park(myCar);
        smartParkingBoy.pick(ticket);
        Car car = smartParkingBoy.pick(ticket);
        assertNull(car);
    }

}
