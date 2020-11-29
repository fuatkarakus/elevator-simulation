package org.koucs.domain;

import lombok.Data;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Data
public class Floor {

    private FloorNumber floorNumber;
    private BlockingQueue<Person> people;
    private BlockingQueue<Person> elevatorQueue;

    public Floor(FloorNumber floorNumber) {
        this.floorNumber = floorNumber;
        this.people =  new LinkedBlockingQueue<>();
        this.elevatorQueue =  new LinkedBlockingQueue<>();
    }

}
