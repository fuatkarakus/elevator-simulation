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

}
