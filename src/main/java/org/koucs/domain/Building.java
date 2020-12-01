package org.koucs.domain;

import lombok.Data;

@Data
public class Building {

    private Floor ground;
    private Floor first;
    private Floor second;
    private Floor third;
    private Floor fourth;

    private Elevator eFirst;
    private Elevator eSecond;
    private Elevator eThird;
    private Elevator eFourth;
    private Elevator eFifth;

    public Building() {
        ground = new Floor(FloorNumber.GROUND);
        first = new Floor(FloorNumber.FIRST);
        second = new Floor(FloorNumber.SECOND);
        third = new Floor(FloorNumber.THIRD);
        fourth = new Floor(FloorNumber.FOURTH);

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

}
