package org.koucs.util;

import org.koucs.domain.FloorNumber;
import org.koucs.domain.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Util {

    private Util() {}

    public static Integer randomLogin() {
        return new Random().nextInt(10 - 1 + 1) + 1;
    }

    public static List<Person> randomLoginPerson() {
        List<Person> personList = new ArrayList<>();
        for (int i =0; i < randomLogin(); i++) {
            personList.add(new Person());
        }
        return personList;
    }

    public static List<Person> randomExitPerson() {
        List<Person> personList = new ArrayList<>();
        for (int i =0; i < randomExit(); i++) {
            personList.add(new Person());
        }
        return personList;
    }

    public static Integer randomExit() {
        return new Random().nextInt(5 - 1 + 1) + 1;
    }

    public static FloorNumber randomFloor() {
        return FloorNumber.values()[new Random().nextInt(FloorNumber.values().length) + 1];
    }


}
