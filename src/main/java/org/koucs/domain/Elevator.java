package org.koucs.domain;

import java.util.Collections;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Elevator implements Runnable{

    private final Integer max_capacity = 10;

    private final Integer work = 200;

    private boolean isRunning = false;

    private Direction direction;

    private Floor current;

    private Building building;

    private BlockingQueue<Person> people;

    public Elevator(Building building) {
        this.building = building;
        this.current = building.getGround();
        this.people = new ArrayBlockingQueue<>(max_capacity);
    }

    @Override
    public void run() {
        try {
            while (true) {
                Collections.sort(building.floors());
                for (Floor floor : building.floors()) {
                    if (floor == current) {
                        current = floor;
                        while (current.getElevatorQueue().size() > 0 ) {
                            if (people.size() == 10) {
                                break;
                            }
                            people.put(current.getElevatorQueue().take());
                        }
                    }
                }
            }
        } catch (Exception e) {

        }

    }

    public boolean isRunning() {
        return isRunning;
    }

    public Direction getDirection() {
        return direction;
    }

    public Floor getCurrent() {
        return current;
    }


}
