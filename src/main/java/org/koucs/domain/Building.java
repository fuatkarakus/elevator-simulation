package org.koucs.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class Building {

    private Floor ground;
    private Floor first;
    private Floor second;
    private Floor third;
    private Floor fourth;

    private Elevator consist;
    private List<Elevator> elevators;

    public Building() {
        ground = new Floor(FloorNumber.GROUND);
        first = new Floor(FloorNumber.FIRST);
        second = new Floor(FloorNumber.SECOND);
        third = new Floor(FloorNumber.THIRD);
        fourth = new Floor(FloorNumber.FOURTH);

        elevators = new ArrayList<>(4);
    }

    public Floor getFLoor( FloorNumber floorNumber ) {
        Floor floor;
        switch (floorNumber) {
            case GROUND:
                floor = getGround();
                break;
            case FIRST:
                floor = getFirst();
                break;
            case SECOND:
                floor = getSecond();
                break;
            case THIRD:
                floor = getThird();
                break;
            case FOURTH:
                floor = getFourth();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + floorNumber);
        }
        return floor;
    }

    public List<Floor> floors() {
        return Arrays.asList(ground, first, second, third, fourth);
    }

    public List<Elevator> elevators() {
        List<Elevator> elevators1 = new ArrayList<>();
        elevators1.add(consist);
        elevators1.addAll(this.elevators);
        return elevators1;
    }

}
