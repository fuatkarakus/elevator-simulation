package org.koucs.domain;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static org.koucs.domain.FloorNumber.FOURTH;
import static org.koucs.domain.FloorNumber.GROUND;

@Slf4j
public class Elevator implements Runnable{

    private static final Integer MAX_CAPACITY = 10;

    private static final Integer WORK = 200;

    private boolean isRunning = false;

    private Direction direction;

    private Floor current;

    private final Building building;

    private final BlockingQueue<Person> people;

    public Elevator(Building building) {
        this.building = building;
        this.people = new ArrayBlockingQueue<>(MAX_CAPACITY);
        this.direction = Direction.UP;
    }

    @Override
    public void run() {
        try {
            while (true) {
                isRunning = true;

                if (direction.equals(Direction.UP)) {

                    for (Floor floor : building.upFloors()) {
                        current = floor;
                        // asansörden insan indir
                        for ( Person person : people) {
                            if (person.getDestination() == current.getFloorNumber()) {
                                people.remove(person);
                                current.getPeople().put(person);
                            }
                        }

                        // asansöre insan alma işlemi
                        while (current.getElevatorQueue().isEmpty()) {
                            if (people.size() == MAX_CAPACITY) {
                                continue;
                            }
                            people.put(current.getElevatorQueue().take());
                        }

                        if (current.getFloorNumber() == FOURTH) {
                            direction = Direction.DOWN;
                        }
                        Thread.sleep(WORK);
                    }
                }
                if (direction.equals(Direction.DOWN)) {
                    for (Floor floor : building.downFloors()) {
                        current = floor;
                        // asansörden insan indir
                        for ( Person person : people) {
                            if (person.getDestination() == current.getFloorNumber()) {
                                current.getPeople().put(person);
                            }
                        }

                        // asansöre insan alma işlemi
                        while (current.getElevatorQueue().isEmpty()) {
                            if (people.size() == MAX_CAPACITY) {
                                continue;
                            }
                            people.put(current.getElevatorQueue().take());
                        }

                        if (current.getFloorNumber() == GROUND) {
                            direction = Direction.UP;
                        }
                        Thread.sleep(WORK);
                    }
                }
            }
        } catch (Exception e) {
            isRunning = false;
            log.error(e.getMessage());
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
