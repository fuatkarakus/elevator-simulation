package org.koucs.domain;

import lombok.Data;

import java.util.Comparator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Data
public class Floor implements Comparable<Floor>, Comparator<Floor> {

    private FloorNumber floorNumber;
    private BlockingQueue<Person> people;
    private BlockingQueue<Person> elevatorQueue;

    public Floor(FloorNumber floorNumber) {
        this.floorNumber = floorNumber;
        this.people =  new LinkedBlockingQueue<>();
        this.elevatorQueue =  new LinkedBlockingQueue<>();
    }

    @Override
    public int compareTo(Floor floor) {
        return Integer.compare(getElevatorQueue().size(), floor.getElevatorQueue().size());
    }

    @Override
    public int compare(Floor o1, Floor o2) {
        return Integer.compare(o1.getFloorNumber().num(), o2.getFloorNumber().num());
    }
}
