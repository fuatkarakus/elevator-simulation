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

                List<Person> personList = Util.randomLoginPerson();

                for (Person person : personList) {

                    FloorNumber randomFloorNumber = Util.randomFloor();
                    person.setDestination(randomFloorNumber);

                }

                addTo( building.getGround().getPeople(), personList);

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
