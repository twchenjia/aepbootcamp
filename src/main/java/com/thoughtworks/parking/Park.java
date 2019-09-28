package com.thoughtworks.parking;

import java.util.HashMap;
import java.util.Map;

public class Park {
    private Map<Ticket, Car> ticketCarMap;
    private int parkCarSize;
    private int size;

    public Map<Ticket, Car> getTicketCarMap() {
        return ticketCarMap;
    }

    public void setTicketCarMap(Map<Ticket, Car> ticketCarMap) {
        this.ticketCarMap = ticketCarMap;
    }

    public int getParkCarSize() {
        return parkCarSize;
    }

    public void setParkCarSize(int parkCarSize) {
        this.parkCarSize = parkCarSize;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Park(int size) {
        this.size = size;
        ticketCarMap = new HashMap<>();
    }

    public Ticket parkCar(Car car) {
        this.parkCarSize++;

        if (parkCarSize > size) {
            throw new ParklotException();
        }
        Ticket ticket = new Ticket();
        ticketCarMap.put(ticket, car);
        return ticket;
    }

    public Car pickCar(Ticket ticket) {
        this.parkCarSize--;
        if (parkCarSize < 0) {
            throw new ParklotException();
        }
        Car car = ticketCarMap.get(ticket);
        ticketCarMap.remove(ticket);
        return car;
    }
}
