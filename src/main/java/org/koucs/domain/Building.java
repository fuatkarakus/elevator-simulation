package org.koucs.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

        consist = new Elevator(this, "1");
        Elevator sec =  new Elevator(this, "2");
        Elevator sec1 =  new Elevator(this, "3");
        Elevator sec2=  new Elevator(this, "4");
        Elevator sec3 =  new Elevator(this, "5");
        elevators.add(sec1);
        elevators.add(sec);
        elevators.add(sec2);
        elevators.add(sec3);
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

    // asansörün sıralı çalışması yukarı
    public List<Floor> upFloors() {
        List<Floor> floors = Arrays.asList(ground, first, second, third, fourth);
        Collections.sort(floors);
        return floors;
    }

    // asansörün sıları çalışması aşağı
    public List<Floor> downFloors() {
        List<Floor> floors = Arrays.asList(ground, first, second, third, fourth);
        Collections.reverse(floors);
        return floors;
    }

    @Override
    public String toString() {
        return "Building{" +
                "ground=" + ground +
                ", first=" + first +
                ", second=" + second +
                ", third=" + third +
                ", fourth=" + fourth +
                ", consist=" + consist +
                ", elevators=" + elevators +
                '}';
    }
}
