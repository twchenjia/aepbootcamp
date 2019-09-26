package com.thoughtworks.parking;

public class Park {

    public Park() {

    }

    public boolean parkCar(int car, int position) throws ParkException {
        if (car < position) {
            return true;
        } else if (car == position) {
            return false;
        } else {
            throw new ParkException();
        }
    }

    public boolean pickCar(int leftCar) {
        if (leftCar == 0) {
            return false;
        }
        return true;
    }

    static class ParkException extends Exception {

        public ParkException() {
            super();
        }
    }
}
