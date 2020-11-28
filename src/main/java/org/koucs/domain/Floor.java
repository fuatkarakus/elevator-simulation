package org.koucs.domain;

import lombok.Data;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Data
public class Floor {

    private FloorNumber floorNumber;
    private BlockingQueue<Person> waitingList;
    private BlockingQueue<Person> elevatorList;

    public Floor(FloorNumber floorNumber) {
        this.floorNumber = floorNumber;
        this.waitingList =  new LinkedBlockingQueue<>();
        this.elevatorList =  new LinkedBlockingQueue<>();
    }

}
