package com.thoughtworks.parking;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ParkTest {
    @Test
    void should_park_in__when_parking_if_park_lot_has_position() {
        Park park = new Park("");
        boolean isPermit = park.parkCar("111");
        assertThat(isPermit, is(true));
    }
}
