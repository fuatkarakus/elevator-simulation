package org.koucs.domain;

public class Elevator implements Runnable{

    private final Integer max_capacity = 10;
    private final Integer work = 200;

    private boolean isRunning = false;

    private Direction direction;

    private Floor current;

    private Building building;

    public Elevator(Building building) {
        this.building = building;
    }

    @Override
    public void run() {

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
