package org.koucs.util;

import org.koucs.domain.Floor;

import java.util.Random;

public class Util {

    private Util() {}

    public static Integer randomLogin() {
        return new Random().nextInt(10) + 1;
    }

    public static Integer randomExit() {
        return new Random().nextInt(5) + 1;
    }

    public static Floor randomFloor() {
        return Floor.values()[new Random().nextInt(Floor.values().length)];
    }


}
