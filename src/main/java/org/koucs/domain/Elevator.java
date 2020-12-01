package org.koucs.domain;

public class Elevator implements Runnable{

    private final Integer max_capacity = 10;
    private final Integer work = 200;

    private Direction direction;

    private Building building;

    public Elevator(Building building) {
        this.building = building;
    }

    @Override
    public void run() {

    }
}
