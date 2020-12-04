package org.koucs.domain;

public enum FloorNumber {

    GROUND(0),
    FIRST(1),
    SECOND(2),
    THIRD(3),
    FOURTH(4);

    private final Integer num;

    private FloorNumber(Integer num) {
        this.num = num;
    }

    public int num() {
        return this.num;
    }
}
