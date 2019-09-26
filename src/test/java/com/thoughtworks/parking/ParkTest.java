package com.thoughtworks.parking;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParkTest {
    @Test
    void should_park_in_when_parking_if_parking_car_count_is_less_than_parking_position() throws Park.ParkException {
        Park park = new Park();
        boolean isPermit = park.parkCar(1, 2);
        assertThat(isPermit, is(true));
    }

    @Test
    void should_not_park_in_when_parking_if_parking_car_count_is_equal_parking_position() throws Park.ParkException {
        Park park = new Park();
        boolean isPermit = park.parkCar(10, 10);
        assertThat(isPermit, is(false));
    }

    @Test
    void should_throw_pack_exception_when_parking_if_parking_car_count_is_more_than_parking_position() throws Park.ParkException {
        Park park = new Park();
        assertThrows(Park.ParkException.class, () -> park.parkCar(2, 1));
    }
}
