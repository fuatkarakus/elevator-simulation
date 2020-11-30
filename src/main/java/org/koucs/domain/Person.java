package org.koucs.domain;

public class Person {

    private final Integer id;
    private FloorNumber current;
    private FloorNumber destination;

    public Person(FloorNumber current, FloorNumber destination) {
        this.id = 1;
        this.current = current;
        this.destination = destination;
    }

    public Person(){
        this.id = 1;
    }

    public Integer get() {
        return id;
    }

    public FloorNumber getCurrent() {
        return current;
    }

    public void setCurrent(FloorNumber current) {
        this.current = current;
    }

    public FloorNumber getDestination() {
        return destination;
    }

    public void setDestination(FloorNumber destination) {
        this.destination = destination;
    }
}
