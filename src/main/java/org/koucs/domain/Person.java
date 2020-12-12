package org.koucs.domain;

import java.util.Objects;
import java.util.UUID;

public class Person {

    private final Integer id;
    private FloorNumber current;
    private FloorNumber destination;
    private final UUID uuid;

    public Person(FloorNumber current, FloorNumber destination) {
        this.id = 1;
        this.current = current;
        this.destination = destination;
        this.uuid = UUID.randomUUID();
    }

    public Person(){
        this.id = 1;
        this.uuid = UUID.randomUUID();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return uuid.equals(person.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }
}
