package org.koucs.service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.koucs.domain.Building;
import org.koucs.domain.FloorNumber;
import org.koucs.domain.Person;
import org.koucs.util.Util;

import java.util.List;
import java.util.concurrent.BlockingQueue;

// todo https://stackoverflow.com/questions/54394042/java-how-to-avoid-using-thread-sleep-in-a-loop
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

            isRunning = true;
            log.info("Login Thread Çalışıyor.");
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
            e.printStackTrace();
            log.error("", e);
        }

    }

    private static void addTo(  BlockingQueue<Person> waitingList , List<Person> personList) {
        log.info("0. kata {} kadar insan ekledi.", personList.size());
        waitingList.addAll(personList);
        log.info("0. kattaki insan sayısı {} ", waitingList.size());
    }

    public boolean isRunning() {
        return isRunning;
    }

}
