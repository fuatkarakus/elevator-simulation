package org.koucs.util;

import org.koucs.domain.FloorNumber;
import org.koucs.domain.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Util {

    private Util() {}

    public static Integer randomLogin() {
        return new Random().nextInt(10 - 1 + 1) + 1;
    }

    public static List<Person> randomLoginPerson() {
        List<Person> personList = new ArrayList<>();
        for (int i =0; i < randomLogin(); i++) {
            Person person =  new Person();
            person.setCurrent(FloorNumber.GROUND);
            personList.add(person);
        }
        return personList;
    }

    public static List<Person> randomExitPerson() {
        List<Person> personList = new ArrayList<>();
        for (int i =0; i < randomExit(); i++) {
            Person person =  new Person();
            person.setDestination(FloorNumber.GROUND);
            personList.add(person);
            personList.add(person);
        }
        return personList;
    }

    public static Integer randomExit() {
        int randomNum = ThreadLocalRandom.current().nextInt(0, 4 + 1);
        return randomNum;
    }

    public static FloorNumber randomFloor() {
        int i = FloorNumber.values().length ;

        return FloorNumber.values()[new Random().nextInt(i)];
    }

}
