package org.koucs.service;

import lombok.SneakyThrows;
import org.koucs.domain.Building;
import org.koucs.domain.Floor;
import org.koucs.util.Util;

import static org.koucs.domain.Floor.FIRST;

public class LoginThread implements Runnable{

    Integer work_time = 500;

    Building building;

    public LoginThread(Building building) {
        this.building = building;
    }

    @SneakyThrows
    @Override
    public void run() {

        while (true) {

            Floor randomFloor = Util.randomFloor();
            // Integer random
            switch (randomFloor) {
                case FIRST:
                    building.getUpToFloorFirst().get(FIRST).add(1);
                case SECOND:
                    continue;
                case THIRD:
                    continue;
                case FOURTH:
                    continue;

            }
            Thread.sleep(work_time);
        }

    }

}
