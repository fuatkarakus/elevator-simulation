package org.koucs.service;

import lombok.extern.slf4j.Slf4j;
import org.koucs.domain.Building;
import org.koucs.domain.FloorNumber;
import org.koucs.domain.Person;
import org.koucs.util.Util;

import java.util.List;

@Slf4j
public class ExitThread implements Runnable {

    private boolean isRunning = false;

    private final Building building;

    public ExitThread(Building building) {
        this.building = building;
    }

    @Override
    public void run() {
        try {
            isRunning = true;

            while (true) {
                FloorNumber randomFloorNumber = Util.randomFloor();
                List<Person> personList = Util.randomExitPerson();

                switch (randomFloorNumber) {
                    case FIRST:
                        break;
                    case SECOND:
                        break;
                    case THIRD:
                        break;
                    case FOURTH:
                        break;
                    default:
                        break;
                }

            }

        } catch (Exception e) {
            isRunning = false;
            log.error(e.getMessage());
        }
    }

    public boolean isRunning() {
        return isRunning;
    }
}
