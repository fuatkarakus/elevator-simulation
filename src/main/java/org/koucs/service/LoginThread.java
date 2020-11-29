package org.koucs.service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.koucs.domain.Building;
import org.koucs.domain.FloorNumber;
import org.koucs.domain.Person;
import org.koucs.util.Util;

import java.util.List;
import java.util.concurrent.BlockingQueue;

@Slf4j
public class LoginThread implements Runnable {

    Integer work = 500;

    private boolean isRunning = false;

    private final Building building;

    public LoginThread(Building building) {
        this.building = building;
    }

    @SneakyThrows
    @Override
    public void run() {
        try {
            while (true) {

                FloorNumber randomFloorNumber = Util.randomFloor();
                List<Person> personList = Util.randomLoginPerson();

                switch (randomFloorNumber) {
                    case FIRST:
                        addTo(building.getFirst().getPeople(), personList);
                        break;
                    case SECOND:
                        addTo(building.getSecond().getPeople(), personList);
                        break;
                    case THIRD:
                        addTo(building.getThird().getPeople(), personList);
                        break;
                    case FOURTH:
                        addTo(building.getFourth().getPeople(), personList);
                        break;
                    default:
                        break;
                }

                Thread.sleep(work);
            }

        } catch (Exception e) {
            isRunning = false;
            log.error(e.getMessage());
        }
    }

    private static void addTo(  BlockingQueue<Person> waitingList , List<Person> personList) {
        waitingList.addAll(personList);
    }

    public boolean isRunning() {
        return isRunning;
    }

}
