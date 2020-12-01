package org.koucs.service;

import lombok.extern.slf4j.Slf4j;
import org.koucs.domain.Building;
import org.koucs.domain.Floor;
import org.koucs.domain.FloorNumber;
import org.koucs.domain.Person;
import org.koucs.util.Util;

import java.util.List;
import java.util.concurrent.BlockingQueue;

@Slf4j
public class ExitThread implements Runnable {

    private boolean isRunning = false;

    Integer work = 1000;

    private final Building building;

    public ExitThread(Building building) {
        this.building = building;
    }

    @Override
    public void run() {
        try {
            isRunning = true;

            while (true) {
                // todo rasgele kattan adam alıp o kattaki adamları asansör kuyruğuna koyar.

                // 1. random insan sayısı
                Integer size =  Util.randomExit();
                // random kat
                FloorNumber randomFloorNumber = Util.randomFloor();
                // binadan al
                Floor floor = building.getFLoor(randomFloorNumber);
                // insan kadar dön
                for (int i = 0; i < size; i++ ) {
                    // kattaki insandan bir tane al
                    Person person = floor.getPeople().take();
                    // gitmek istediği yeri 0. kat
                    person.setDestination(FloorNumber.GROUND);
                    // asasnsör kuyruğuna ekle
                    floor.getElevatorQueue().put(person);
                }

                Thread.sleep(work);

            }

        } catch (Exception e) {
            isRunning = false;
            log.error(e.getMessage());
        }
    }

    private static void addTo(BlockingQueue<Person> waitingList , List<Person> personList) {
        waitingList.addAll(personList);
    }

    public boolean isRunning() {
        return isRunning;
    }
}
