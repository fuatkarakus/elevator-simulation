package org.koucs.service;

import lombok.SneakyThrows;
import org.koucs.domain.Building;
import org.koucs.domain.FloorNumber;
import org.koucs.domain.Person;
import org.koucs.util.Util;

import java.util.List;
import java.util.concurrent.BlockingQueue;

public class LoginThread implements Runnable {

    Integer work = 500;

    Building building;

    public LoginThread(Building building) {
        this.building = building;
    }

    @SneakyThrows
    @Override
    public void run() {

        while (true) {

            FloorNumber randomFloorNumber = Util.randomFloor();
            List<Person> personList = Util.randomLoginPerson();

            switch (randomFloorNumber) {
                case FIRST:
                    addTo(building.getFirst().getWaitingList(), personList);
                    break;
                case SECOND:
                    addTo(building.getSecond().getWaitingList(), personList);
                    break;
                case THIRD:
                    addTo(building.getThird().getWaitingList(), personList);
                    break;
                case FOURTH:
                    addTo(building.getFourth().getWaitingList(), personList);
                    break;
                default:
                    break;
            }

            Thread.sleep(work);
        }

    }

    private static void addTo(  BlockingQueue<Person> waitingList , List<Person> personList) {
        waitingList.addAll(personList);
    }

}
